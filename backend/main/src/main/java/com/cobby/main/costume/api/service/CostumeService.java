package com.cobby.main.costume.api.service;

import java.util.List;

import com.cobby.main.costume.api.dto.request.CostumePostRequest;
import com.cobby.main.costume.api.dto.response.CostumeGetResponse;

public interface CostumeService {

	List<CostumeGetResponse> selectAllCostumes();

	CostumeGetResponse selectCostume(Long costumeId);

	Long insertCostume(CostumePostRequest costumePostRequest);

	Long deleteCostume(Long costumeId);
}
