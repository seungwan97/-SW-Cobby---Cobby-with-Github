package com.cobby.main.tier.db.entity;

import com.cobby.main.tier.api.dto.response.TierGetResponse;
import com.cobby.main.title.api.dto.response.TitleGetResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Tier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tierId;

	private String name;

	private String emblemImgUrl;

	// Entity to Dto
	public TierGetResponse toDto() {return new TierGetResponse(tierId, name, emblemImgUrl);}


}
