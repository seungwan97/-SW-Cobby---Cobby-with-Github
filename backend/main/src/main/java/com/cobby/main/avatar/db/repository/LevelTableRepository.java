package com.cobby.main.avatar.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobby.main.avatar.db.entity.LevelTable;

public interface LevelTableRepository extends JpaRepository<LevelTable, Integer> {
	Optional<LevelTable> findTopByPrevExpIsLessThanEqualOrderByLevelDesc(Integer nextExp);
}