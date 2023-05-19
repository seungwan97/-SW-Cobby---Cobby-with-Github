package com.cobby.store.common.mattermost;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class NotificationManager {

	private final MatterMostSender matterMostSender;

	public void sendNotification(Exception e, String uri, String params) {
		matterMostSender.sendMessage(e, uri, params);
	}
}
