package com.cobby.main.avatar.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobby.main.avatar.db.entity.AvatarCostume;
import com.cobby.main.avatar.db.entity.AvatarTitle;

public interface AvatarTitleRepository extends JpaRepository<AvatarTitle, Long> {
	List<AvatarTitle> findAllByAvatar_AvatarId(String avatarId);
	Optional<AvatarTitle> findByTitle_TitleId(Long titleId);
}
