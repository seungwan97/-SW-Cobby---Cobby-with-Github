package com.cobby.main.avatar.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobby.main.avatar.db.entity.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Integer> {

}
