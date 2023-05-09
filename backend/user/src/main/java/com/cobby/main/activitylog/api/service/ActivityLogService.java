package com.cobby.main.activitylog.api.service;

import java.util.Map;

public interface ActivityLogService {

	void webhookCreate(Map<String, String> headers, String payload);
}
