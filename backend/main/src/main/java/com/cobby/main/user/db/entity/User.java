package com.cobby.main.user.db.entity;

import static jakarta.persistence.EnumType.*;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import com.cobby.main.activitylog.db.entity.ActivityLog;
import com.cobby.main.common.entity.BaseTimeEntity;
import com.cobby.main.stat.db.entity.Stat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity{

	@Id
	private String id;

	@OneToOne(mappedBy = "user")
	private Stat stat;

	@OneToOne(mappedBy = "user")
	private ActivityLog activityLog;

	@Column(nullable = false)
	private String nickname;
	@Column(nullable = false)
	@Enumerated(STRING)
	private State state;
	@Column(nullable = false)
	private String githubUri;


	@Override
	public String toString() {
		return "User{" +
			"id='" + id + '\'' +
			", stat=" + stat +
			", activityLog=" + activityLog +
			", nickname='" + nickname + '\'' +
			", state=" + state +
			", githubUri='" + githubUri + '\'' +
			'}';
	}

}
