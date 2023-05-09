package com.cobby.main.stat.api.service;

import com.cobby.main.stat.api.dto.request.StatSubscribeRequest;
import com.cobby.main.stat.api.dto.response.StatResponse;

public interface StatService {

	void subscribeUserInfo(StatSubscribeRequest statSubscribeRequest);

	StatResponse getUserInfo(String userId);
}
