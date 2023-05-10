package com.cobby.main.costume.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.costume.db.entity.enumtype.CostumeCategory;

@Repository
public interface CostumeRepository extends JpaRepository<Costume, Long> {
	List<Costume> findAllByCategory(CostumeCategory costumeCategory);
}
