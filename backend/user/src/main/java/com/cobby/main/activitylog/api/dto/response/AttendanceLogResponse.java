package com.cobby.main.activitylog.api.dto.response;

import com.cobby.main.activitylog.db.entity.ActivityLog;
import com.cobby.main.activitylog.db.entity.ActivityType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AttendanceLogResponse {

	private ActivityType activityType;
	private Long relayCnt;

	@Builder
	public AttendanceLogResponse(ActivityLog activityLog) {
		this.activityType = activityLog.getActivityType();
		this.relayCnt = activityLog.getRelayCnt();
	}
}
