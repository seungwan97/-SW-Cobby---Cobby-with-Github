package com.cobby.main.activitylog.api.service;

import java.util.Map;

import com.cobby.main.activitylog.api.dto.response.ActivityLogCommitResponse;
import com.cobby.main.activitylog.api.dto.response.ActivityLogResponse;

public interface ActivityLogService {

	void webhookCreate(Map<String, String> headers, String payload);

	ActivityLogResponse getactivityLogInfo(String userId);

	ActivityLogCommitResponse getactivityLogCommit(String userId);
}
