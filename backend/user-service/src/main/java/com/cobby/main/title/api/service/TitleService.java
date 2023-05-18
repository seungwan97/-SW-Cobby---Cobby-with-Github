package com.cobby.main.title.api.service;

import java.util.List;

import com.cobby.main.title.api.dto.request.TitlePostRequest;
import com.cobby.main.title.api.dto.request.TitlePutRequest;
import com.cobby.main.title.api.dto.response.TitleGetResponse;
import com.cobby.main.title.db.entity.Title;

public interface TitleService {

	TitleGetResponse selectTitle(Long titleId);
	List<TitleGetResponse> selectAllTitles();
	Long insertTitle(TitlePostRequest titleInfo);
	Long updateTitle(TitlePutRequest titleInfo);
	Long deleteTitle(Long titleId);
}
