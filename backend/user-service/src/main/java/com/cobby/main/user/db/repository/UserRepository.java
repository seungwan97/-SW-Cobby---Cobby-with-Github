package com.cobby.main.user.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cobby.main.user.db.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByNickname(String name);
}
