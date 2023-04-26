package com.cobby.main.stat.db.entity;

import com.cobby.main.user.db.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class Stat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stat_id", updatable = false, columnDefinition = "INT UNSIGNED")
	private Long id;

	@OneToOne
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	@Column(nullable = false, columnDefinition = "INT UNSIGNED")
	private Long commitCnt;
	@Column(nullable = false, columnDefinition = "INT UNSIGNED")
	private Long starCnt;
	@Column(nullable = false, columnDefinition = "INT UNSIGNED")
	private Long forkCnt;
	@Column(nullable = false, columnDefinition = "INT UNSIGNED")
	private Long prCnt;
	@Column(nullable = false, columnDefinition = "INT UNSIGNED")
	private Long followerCnt;
	@Column(nullable = false, columnDefinition = "INT UNSIGNED")
	private Long issueCnt;

}
