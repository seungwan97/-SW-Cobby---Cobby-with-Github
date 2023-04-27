package com.cobby.main.avatar.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cobby.main.avatar.db.entity.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, String> {

}
