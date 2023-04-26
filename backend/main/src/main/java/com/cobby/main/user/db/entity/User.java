package com.cobby.main.user.db.entity;


import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.cobby.main.activitylog.db.entity.ActivityLog;
import com.cobby.main.common.entity.BaseTimeEntity;
import com.cobby.main.common.entity.UpdateTimeEntity;
import com.cobby.main.stat.db.entity.Stat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
public class User extends BaseTimeEntity{

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "user_id", columnDefinition = "BINARY(16)")
	private UUID id;

	@OneToOne(mappedBy = "user")
	private Stat stat;

	@OneToOne(mappedBy = "user")
	private ActivityLog activityLog;

	@Column(nullable = false)
	private String nickname;
	@Column(nullable = false)
	private State state;
	@Column(nullable = false)
	private String githubUri;
}
