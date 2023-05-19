package com.cobby.store.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cobby.store.common.mattermost.MatterMostProperties;
import com.cobby.store.common.mattermost.MatterMostSender;
import com.cobby.store.common.mattermost.NotificationManager;

@Configuration
public class MatterMostConfig {

	@Bean
	public NotificationManager notificationManager() {
		return new NotificationManager(matterMostSender());
	}

	@Bean
	public MatterMostSender matterMostSender() {
		return new MatterMostSender(matterMostProperties());
	}

	@Bean
	public MatterMostProperties matterMostProperties() {
		return new MatterMostProperties();
	}
}
