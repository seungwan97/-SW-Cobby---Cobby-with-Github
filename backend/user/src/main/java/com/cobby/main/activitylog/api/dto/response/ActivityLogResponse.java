package com.cobby.main.activitylog.api.dto.response;

import com.cobby.main.activitylog.db.entity.ActivityType;
import com.cobby.main.user.db.entity.User;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ActivityLogResponse {

	private ActivityType activityType;
	private Long relayCnt;
	private String userId;

	@Builder
	public ActivityLogResponse(ActivityType activityType, Long relayCnt, String userId) {
		this.activityType = activityType;
		this.relayCnt = relayCnt;
		this.userId = userId;
	}
}
