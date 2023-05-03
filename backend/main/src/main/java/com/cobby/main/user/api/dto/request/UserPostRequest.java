package com.cobby.main.user.api.dto.request;

import lombok.Getter;


public record UserPostRequest (
	String userId,
	String nickname,
	String githubUrl,
	String githubToken
){}
