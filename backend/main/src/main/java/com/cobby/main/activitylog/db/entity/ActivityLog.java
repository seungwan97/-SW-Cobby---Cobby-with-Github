package com.cobby.main.activitylog.db.entity;

import com.cobby.main.common.entity.BaseTimeEntity;
import com.cobby.main.common.entity.UpdateTimeEntity;
import com.cobby.main.user.db.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLog extends UpdateTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stat_id", updatable = false, columnDefinition = "INT UNSIGNED")
	private Long id;

	@OneToOne
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	@Column(nullable = false)
	private ActivityType activityType;

	@Column(nullable = false, columnDefinition = "INT UNSIGNED")
	private Long relayCnt;

}
