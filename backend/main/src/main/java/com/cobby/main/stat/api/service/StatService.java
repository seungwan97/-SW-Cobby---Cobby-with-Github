package com.cobby.main.stat.api.service;

import com.cobby.main.stat.api.dto.request.StatPostRequest;
import com.cobby.main.stat.api.dto.request.StatSubscribeRequest;

public interface StatService {

	void subscribeUserInfo(StatSubscribeRequest statSubscribeRequest);

}
