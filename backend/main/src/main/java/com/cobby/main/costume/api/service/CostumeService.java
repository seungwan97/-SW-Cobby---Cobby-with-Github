package com.cobby.main.costume.api.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cobby.main.costume.api.dto.request.CostumePostRequest;
import com.cobby.main.costume.api.dto.response.CostumeGetResponse;

public interface CostumeService {

	List<CostumeGetResponse> selectAllCostumes();

	CostumeGetResponse selectCostume(Long costumeId);

	Long insertCostume(CostumePostRequest costumePostRequest, MultipartFile pngFile, MultipartFile gifFile) throws
		IOException;

	Long deleteCostume(Long costumeId);
}
