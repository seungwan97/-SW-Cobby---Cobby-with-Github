package com.cobby.main.costume.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobby.main.costume.db.entity.AvatarCostume;

public interface AvatarCostumeRepository extends JpaRepository<AvatarCostume, Integer> {
}
