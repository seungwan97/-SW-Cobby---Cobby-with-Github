package com.cobby.main.user.db.entity;

import static jakarta.persistence.EnumType.*;

import com.cobby.main.common.entity.BaseTimeEntity;
import com.cobby.main.stat.db.entity.Stat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity{

	@Id
	private String id;

	@Column(nullable = false)
	private String nickname;
	@Column(nullable = false)
	@Enumerated(STRING)
	private State state;
	@Column(nullable = false)
	private String githubUrl;
	@Column(nullable = false)
	private String githubToken;


	@Builder
	public User(String id, String nickname, State state, String githubUrl) {
		this.id = id;
		this.nickname = nickname;
		this.state = state;
		this.githubUrl = githubUrl;
	}
}
