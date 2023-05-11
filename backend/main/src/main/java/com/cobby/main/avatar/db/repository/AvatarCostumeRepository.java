package com.cobby.main.avatar.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobby.main.avatar.db.entity.AvatarCostume;
import com.cobby.main.costume.db.entity.enumtype.CostumeCategory;

public interface AvatarCostumeRepository extends JpaRepository<AvatarCostume, Long> {

	List<AvatarCostume> findAllByAvatar_AvatarId(String avatarId);

	Optional<AvatarCostume> findByCostume_CostumeId(Long costumeId);

	List<AvatarCostume> findAllByAvatar_AvatarIdAndCostume_Category(String userId, CostumeCategory category);

}
