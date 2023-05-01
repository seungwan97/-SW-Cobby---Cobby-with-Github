package com.cobby.main.avatar.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobby.main.avatar.db.entity.AvatarCostume;
import com.cobby.main.avatar.db.entity.AvatarQuest;

public interface AvatarQuestRepository extends JpaRepository<AvatarQuest, Integer> {
}
