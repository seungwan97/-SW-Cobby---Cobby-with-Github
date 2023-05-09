package com.cobby.main.activitylog.api.dto.response;

import com.cobby.main.activitylog.db.entity.ActivityType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ActivityLogCommitResponse {

	private ActivityType activityType;
	private Long relayCnt;
	private String userId;
	private Long todayCnt;

	@Builder
	public ActivityLogCommitResponse(ActivityType activityType, Long relayCnt, String userId, Long todayCnt) {
		this.activityType = activityType;
		this.relayCnt = relayCnt;
		this.userId = userId;
		this.todayCnt = todayCnt;
	}
}
