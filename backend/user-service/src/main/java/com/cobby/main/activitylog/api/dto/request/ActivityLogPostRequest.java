package com.cobby.main.activitylog.api.dto.request;

import java.util.Map;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

public record ActivityLogPostRequest (
	Map<String, Objects> repo
) {}
