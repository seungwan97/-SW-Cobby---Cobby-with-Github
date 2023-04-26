package com.cobby.main.costume.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobby.main.costume.db.entity.Costume;

public interface CostumeRepository extends JpaRepository<Costume, Integer> {

}
