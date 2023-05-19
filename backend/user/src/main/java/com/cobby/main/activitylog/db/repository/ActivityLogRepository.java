package com.cobby.main.activitylog.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cobby.main.activitylog.db.entity.ActivityLog;
import com.cobby.main.activitylog.db.entity.ActivityType;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Integer> {

	default List<ActivityLog> findAllByType(String userId, ActivityType type) {
		return findByUserIdAndActivityTypeOrderByIdDesc(userId, type);
	}

	default Optional<ActivityLog> findLastByType(String userId, ActivityType activityType) {
		return findTopByUserIdAndActivityTypeOrderByIdDesc(userId, activityType);
	}

	List<ActivityLog> findByUserIdAndActivityTypeOrderByIdDesc(String userId, ActivityType type);

	Optional<ActivityLog> findTopByUserIdAndActivityTypeOrderByIdDesc(String userId, ActivityType activityType);

}
