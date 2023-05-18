package com.cobby.main.tier.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TierGetResponse {

	private Integer tierId;

	private String name;

	private String emblemImgUrl;
}
