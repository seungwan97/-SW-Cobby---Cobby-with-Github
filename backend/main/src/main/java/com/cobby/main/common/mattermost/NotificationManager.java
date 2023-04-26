package com.cobby.main.common.mattermost;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
