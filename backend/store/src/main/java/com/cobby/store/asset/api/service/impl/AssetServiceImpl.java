package com.cobby.store.asset.api.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.cobby.store.asset.api.dto.response.AssetGetResponse;
import com.cobby.store.asset.api.service.AssetService;
import com.cobby.store.asset.db.Asset;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AssetServiceImpl implements AssetService {

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	private final MongoTemplate mongoTemplate;
	private final AmazonS3 amazonS3Client;
	private final StringBuilder stringBuilder = new StringBuilder();

	@Override
	public AssetGetResponse selectAsset(String assetId) throws IOException {
		// mongodb 에서 assetId 기준으로 document를 불러온다.

		var asset = Optional
			.ofNullable(mongoTemplate.findOne(
				new Query(Criteria.where("assetId").is(assetId)),
				Asset.class,
				"assets"))
			.orElseThrow(() -> new IllegalArgumentException("해당 이미지가 없습니다. (ID=" + assetId + ")"));

		var fileExtension = asset.getExtensionType();
		System.out.println("assetid : " + asset.getAssetId());
		System.out.println("extension : " + asset.getExtensionType());
		System.out.println("url : " + asset.getUrl());

		String contentType = null;
		if (fileExtension.equalsIgnoreCase("png")) {
			contentType = "image/png";
		} else if (fileExtension.equalsIgnoreCase("gif")) {
			contentType = "image/gif";
		}

		// asset 의 content-type 과 파일을 담은 AssetGetResponse 타입으로 빌드해서 반환한다.
		return AssetGetResponse.builder()
			.contentType(contentType)
			.resource(new UrlResource(asset.getUrl()))
			.build();
	}

	@Override
	public String insertAsset(MultipartFile multipartFile) {
		/**
		 * TODO: asset을 s3 저장소에 저장한 뒤 get url을 가져온다.
		 */
		String originFilename = Normalizer.normalize(multipartFile.getOriginalFilename(), Normalizer.Form.NFC);
		String uploadFileUrl = "";

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(multipartFile.getSize());
		objectMetadata.setContentType(multipartFile.getContentType());

		try (InputStream inputStream = multipartFile.getInputStream()) {

			// S3에 폴더 및 파일 업로드
			amazonS3Client.putObject(
				new PutObjectRequest(bucketName, "body/" + originFilename, inputStream, objectMetadata));
			// amazonS3Client.putObject(
			// 	new PutObjectRequest(bucketName, "character/" + originFilename, inputStream, objectMetadata));
			// amazonS3Client.putObject(
			// 	new PutObjectRequest(bucketName, "head/" + originFilename, inputStream, objectMetadata));

			// S3에 업로드한 폴더 및 파일 URL

			uploadFileUrl = amazonS3Client.getUrl(bucketName, "body/" + originFilename).toString();
			System.out.println("bucketName : " + bucketName);
			System.out.println("originFilename : " + originFilename);
			System.out.println("uploadFileUrl : " + uploadFileUrl);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Filed upload failed", e);
		}

		// 파일 확장자(.png, .gif, ...) 판별
		var fileExtension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());

		// assetId 지정 -> {UUID}_{밀리세컨드}.png (다른 좋은 명명규칙이 있으면 대체해줘잉
		String assetId = stringBuilder
			.append(UUID.randomUUID())
			.append("_")
			.append(System.currentTimeMillis())
			.append("." + fileExtension)
			.toString();

		// stringBuilder 초기화
		stringBuilder.setLength(0);

		// 가져온 url을 document로 빌드해서 저장한다.
		var asset = Asset.builder()
			.assetId(assetId)
			.extensionType(fileExtension)
			.url(uploadFileUrl)
			.build();

		return mongoTemplate.save(asset).getAssetId();
	}

}
