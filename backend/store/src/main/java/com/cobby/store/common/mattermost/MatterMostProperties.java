package com.cobby.store.common.mattermost;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("notification.mattermost")
public class MatterMostProperties {

	private String channel;
	private String pretext;
	private String color = "#ff5d52";
	private String authorName;
	private String authorIcon;
	private String title;
	private String text = "";
	private String footer = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

}
