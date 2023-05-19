package com.cobby.main.costume.api.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cobby.main.common.exception.BaseRuntimeException;
import com.cobby.main.costume.api.dto.request.CostumePostRequest;
import com.cobby.main.costume.api.dto.response.CostumeGetResponse;
import com.cobby.main.costume.api.service.CostumeService;
import com.cobby.main.costume.api.util.ImageUrlProvider;
import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.costume.db.entity.enumtype.CostumeCategory;
import com.cobby.main.costume.db.repository.CostumeRepository;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import com.cobby.main.quest.db.repository.QuestRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CostumeServiceImpl implements CostumeService {

	private final CostumeRepository costumeRepository;

	private final QuestRepository questRepository;

	private final ImageUrlProvider imageUrlProvider;

	private final RedisTemplate<String, CostumeGetResponse> redisCostumeTemplate;

	@Override
	public List<CostumeGetResponse> selectAllCostumes() {
		if (Boolean.TRUE.equals(redisCostumeTemplate.hasKey("costume_ALL"))) {
			return redisCostumeTemplate.opsForList().range("costume_ALL", 0, -1);
		} else {
			var costumes = costumeRepository.findAll();

			if(costumes.isEmpty()) {
				throw new BaseRuntimeException(HttpStatus.NOT_FOUND, "조회할 수 있는 코스튬이 없습니다.");
			}

			var costumeList = costumes.stream()
				.map(costume -> CostumeGetResponse.builder()
					.costume(costume)
					.build())
				.toList();

			redisCostumeTemplate.opsForList().rightPushAll("costume_ALL", costumeList.toArray(CostumeGetResponse[]::new));

			return costumeList;
		}
	}

	@Override
	public List<CostumeGetResponse> selectAllCostumesByCategoryType(CostumeCategory category) {
		if (Boolean.TRUE.equals(redisCostumeTemplate.hasKey("costume_" + category))) {
			return redisCostumeTemplate.opsForList().range("costume_" + category, 0, -1);
		} else {
			var costumes = costumeRepository.findAllByCategory(category);

			if (costumes.isEmpty()) {
				throw new BaseRuntimeException(HttpStatus.NOT_FOUND, "조회할 수 있는 코스튬이 없습니다.");
			}

			var costumeList = costumes.stream()
				.map(costume -> CostumeGetResponse.builder()
					.costume(costume)
					.build())
				.toList();

			redisCostumeTemplate.opsForList().rightPushAll("costume_" + category, costumeList.toArray(new CostumeGetResponse[0]));

			return costumeList;
		}
	}

	@Override
	public CostumeGetResponse selectCostume(Long costumeId) {

		var costume = costumeRepository.findById(costumeId)
			.orElseThrow(() -> new IllegalArgumentException("코스튬 정보가 없습니다. (ID=" + costumeId + ")"));

		return CostumeGetResponse.builder()
			.costume(costume)
			.build();
	}

	@Override
	public Long insertCostume(CostumePostRequest request, MultipartFile pngFile, MultipartFile gifFile) throws
		IOException {

		var category = CostumeCategory.valueOf(request.category());
		// 이미지 url 획득 로직 추가해야 함

		var quest = questRepository.findById(request.questId())
			.orElseThrow(() -> new IllegalArgumentException("퀘스트 정보가 없습니다. (ID=" + request.questId() + ")"));

		var imgUrl = imageUrlProvider.getImageUrl(pngFile);
		var gifUrl = imageUrlProvider.getImageUrl(gifFile);

		var costume = Costume.builder()
			.name(request.name())
			.category(category)
			.quest(quest)
			.imgUrl(imgUrl)
			.gifUrl(gifUrl)
			.build();

		return costumeRepository.save(costume).getCostumeId();
	}

	@Override
	public Long deleteCostume(Long costumeId) {

		var costume = costumeRepository.findById(costumeId)
			.orElseThrow(() -> new IllegalArgumentException("코스튬 정보가 없습니다. (ID=" + costumeId + ")"));

		costumeRepository.deleteById(costumeId);

		return costume.getCostumeId();
	}

}
