package com.cobby.main.tier.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobby.main.tier.db.entity.Tier;

public interface TierRepository extends JpaRepository<Tier, Integer> {
}
