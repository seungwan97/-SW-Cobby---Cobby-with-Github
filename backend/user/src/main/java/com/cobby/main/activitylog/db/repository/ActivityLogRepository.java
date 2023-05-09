package com.cobby.main.activitylog.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cobby.main.activitylog.db.entity.ActivityLog;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Integer> {

	Optional<ActivityLog> findTopByUserIdOrderByIdDesc(String userId);
	List<ActivityLog> findByUserIdOrderByIdDesc(String userId);
}
