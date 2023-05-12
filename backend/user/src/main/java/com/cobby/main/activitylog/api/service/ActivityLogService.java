package com.cobby.main.activitylog.api.service;

import java.util.Map;

import com.cobby.main.activitylog.api.dto.response.ActivityLogCommitResponse;
import com.cobby.main.activitylog.api.dto.response.ActivityLogResponse;
import com.cobby.main.activitylog.db.entity.ActivityLog;

public interface ActivityLogService {

	void webhookCreate(Map<String, String> headers, String payload);

	ActivityLog getActivityLogInfo(String userId);

	ActivityLogCommitResponse getActivityLogCommit(String userId);
}
