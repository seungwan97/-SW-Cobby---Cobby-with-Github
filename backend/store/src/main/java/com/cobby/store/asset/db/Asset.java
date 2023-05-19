package com.cobby.store.asset.db;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "assets")
public class Asset {

	@Field
	private String assetId;

	@Field
	private String extensionType;

	@Field
	private String url;
}
