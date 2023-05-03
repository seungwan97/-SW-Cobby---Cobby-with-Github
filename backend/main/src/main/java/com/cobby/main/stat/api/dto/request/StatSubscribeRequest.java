package com.cobby.main.stat.api.dto.request;

import lombok.Getter;

@Getter
public class StatSubscribeRequest {

	private String userId;
	private StatPostRequest statPostRequest;
}
