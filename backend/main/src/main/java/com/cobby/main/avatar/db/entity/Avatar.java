package com.cobby.main.avatar.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Avatar {

	@Id
	@Column(name = "avatar_id")
	private String avatarId;

}
