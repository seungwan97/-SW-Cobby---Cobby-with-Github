package com.cobby.main.activitylog.api.dto.request;

import java.util.Map;
import java.util.Objects;

public record ActivityLogPostRequest (
	Map<String, Objects> repo
) {}
