package com.cobby.main.avatar.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobby.main.avatar.db.entity.AvatarCostume;

public interface AvatarCostumeRepository extends JpaRepository<AvatarCostume, Integer> {
}
