package com.cobby.main.costume.api.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cobby.main.common.exception.BaseRuntimeException;
import com.cobby.main.costume.api.dto.request.CostumePostRequest;
import com.cobby.main.costume.api.dto.response.CostumeGetResponse;
import com.cobby.main.costume.api.service.CostumeService;
import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.costume.db.entity.enumtype.CostumeCategory;
import com.cobby.main.costume.db.repository.CostumeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CostumeServiceImpl implements CostumeService {

	private final CostumeRepository costumeRepository;

	@Override
	public List<CostumeGetResponse> selectAllCostumes() {

		var costumes = costumeRepository.findAll();

		if(costumes.isEmpty()) {
			throw new BaseRuntimeException(HttpStatus.NOT_FOUND, "조회할 수 있는 코스튬이 없습니다.");
		}

		return costumes.stream()
			.map(costume -> CostumeGetResponse.builder()
				.costume(costume)
				.build())
			.toList();
	}

	@Override
	public CostumeGetResponse selectCostume(Integer costumeId) {

		var costume = costumeRepository.findById(costumeId)
			.orElseThrow(() -> new IllegalArgumentException("코스튬 정보가 없습니다. (ID=" + costumeId + ")"));

		return CostumeGetResponse.builder()
			.costume(costume)
			.build();
	}

	@Override
	public Long insertCostume(CostumePostRequest request) {

		var category = CostumeCategory.valueOf(request.getCategory());
		// 이미지 url 획득 로직 추가해야 함

		var costume = Costume.builder()
			.name(request.getName())
			.category(category)
			.imgUrl(null)
			.silhouetteImgUrl(null)
			.gifUrl(null)
			.build();

		return costumeRepository.save(costume).getCostumeId();
	}

	@Override
	public Long deleteCostume(Integer costumeId) {

		var costume = costumeRepository.findById(costumeId)
			.orElseThrow(() -> new IllegalArgumentException("코스튬 정보가 없습니다. (ID=" + costumeId + ")"));

		costumeRepository.deleteById(costumeId);

		return costume.getCostumeId();
	}
}
