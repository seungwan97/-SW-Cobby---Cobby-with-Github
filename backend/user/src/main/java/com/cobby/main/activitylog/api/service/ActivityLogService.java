package com.cobby.main.activitylog.api.service;

import java.util.Map;

import com.cobby.main.activitylog.api.dto.response.AttendanceLogResponse;
import com.cobby.main.activitylog.api.dto.response.CommitLogResponse;
import com.cobby.main.activitylog.db.entity.ActivityType;

public interface ActivityLogService {

	void webhookCreate(Map<String, String> headers, String payload);

	AttendanceLogResponse getAttendanceActivityLog(String userId);

	CommitLogResponse getCommitActivityLog(String userId);

}
