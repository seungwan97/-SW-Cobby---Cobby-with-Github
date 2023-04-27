package com.cobby.main.stat.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cobby.main.stat.db.entity.Stat;

@Repository
public interface StatRepository extends JpaRepository<Stat, Integer> {

	Optional<Stat> findByUser_Id(@Param(value = "userID") String userId);
}
