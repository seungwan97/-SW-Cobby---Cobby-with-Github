package com.cobby.main.quest.db.repository;

import com.cobby.main.quest.db.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Integer> {

}
