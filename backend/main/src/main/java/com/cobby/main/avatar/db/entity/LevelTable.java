package com.cobby.main.avatar.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "level_table")
@Entity
public class LevelTable {

	@Id
	@Column(name = "level", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer level;

	@Column(name = "next_exp", nullable = false)
	private Integer nextExp;

	@Column(name = "prev_exp", nullable = false)
	private Integer prevExp;
}
