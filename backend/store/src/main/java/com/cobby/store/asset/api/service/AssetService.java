package com.cobby.store.asset.api.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.cobby.store.asset.api.dto.response.AssetGetResponse;

public interface AssetService {

	AssetGetResponse selectAsset(String assetId) throws IOException;

	/**
	 * 파일
	 * @param asset 저장할 파일
	 * @return
	 */
	String insertAsset(MultipartFile asset);
}
