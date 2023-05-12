package com.cobby.main.activitylog.api.dto.response;

import com.cobby.main.activitylog.db.entity.ActivityType;
import com.cobby.main.user.db.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ActivityLogResponse {

	private ActivityType activityType;
	private Long relayCnt;
	private User user;

	@Builder
	public ActivityLogResponse(ActivityType activityType, Long relayCnt, User user) {
		this.activityType = activityType;
		this.relayCnt = relayCnt;
		this.user = user;
	}
}
