package com.cobby.main.activitylog.api.dto.response;

import com.cobby.main.activitylog.db.entity.ActivityLog;
import com.cobby.main.activitylog.db.entity.ActivityType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommitLogResponse {

	private ActivityType activityType;
	private Long relayCnt;
	private Long todayCnt;

	@Builder
	public CommitLogResponse(ActivityLog activityLog, Long todayCnt) {
		this.activityType = activityLog.getActivityType();
		this.relayCnt = activityLog.getRelayCnt();
		this.todayCnt = todayCnt;
	}
}
