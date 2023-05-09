package com.cobby.main.user.api.dto.request;


public record UserPostRequest (
	String userId,
	String nickname,
	String githubUrl,
	String githubToken
){}
