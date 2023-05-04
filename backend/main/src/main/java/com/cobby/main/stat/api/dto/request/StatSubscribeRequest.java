package com.cobby.main.stat.api.dto.request;

public record StatSubscribeRequest(

	String userId,
	StatPostRequest statPostRequest
){}
