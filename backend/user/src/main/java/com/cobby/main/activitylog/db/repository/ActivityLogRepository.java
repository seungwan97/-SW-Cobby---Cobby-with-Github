package com.cobby.main.activitylog.db.repository;

import java.util.Optional;

import com.cobby.main.activitylog.db.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Integer> {

	Optional<ActivityLog> findTopByUserIdOrderByIdDesc(String userId);
}
