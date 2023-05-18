package com.cobby.main.stat.api.service;

import com.cobby.main.stat.api.dto.request.StatSubscribeRequest;
import com.cobby.main.stat.api.dto.response.StatResponse;

public interface StatService {

	void subscribeStatInfo(StatSubscribeRequest statSubscribeRequest);

	StatResponse getStatInfo(String userId);
}
