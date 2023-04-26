package com.cobby.main.title.api.service;

import java.util.List;

import com.cobby.main.title.api.dto.request.TitlePostRequest;
import com.cobby.main.title.api.dto.request.TitlePutRequest;
import com.cobby.main.title.api.dto.response.TitleGetResponse;
import com.cobby.main.title.db.entity.Title;

public interface TitleService {

	List<TitleGetResponse> selectAllTitle();
	void insertTitle(TitlePostRequest titleInfo);
	void updateTitle(TitlePutRequest titleInfo);
	void deleteTitle(int titleId);
}
