package com.cobby.main.avatar.db.repository;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cobby.main.avatar.db.entity.AvatarCostume;
import com.cobby.main.avatar.db.entity.AvatarQuest;

@Repository
public interface AvatarQuestRepository extends JpaRepository<AvatarQuest, Integer> {
	List<AvatarQuest> findAllByAvatar_AvatarId(String avatarId);
}
