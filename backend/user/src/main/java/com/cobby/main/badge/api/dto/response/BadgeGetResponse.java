package com.cobby.main.badge.api.dto.response;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BadgeGetResponse{
	private Integer level;
	private String head;
	private String effect;
	private String body;
}
