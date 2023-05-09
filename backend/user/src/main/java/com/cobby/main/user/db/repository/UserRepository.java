package com.cobby.main.user.db.repository;

import java.util.List;
import java.util.Optional;

import com.cobby.main.user.api.dto.response.UserMainResponse;
import com.cobby.main.user.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findById(String userId);

	Optional<User> findByNickname(String name);
}
