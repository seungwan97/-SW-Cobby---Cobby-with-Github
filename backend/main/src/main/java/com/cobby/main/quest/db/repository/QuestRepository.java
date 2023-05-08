package com.cobby.main.quest.db.repository;

import java.util.List;

import com.cobby.main.quest.db.entity.Quest;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {
	List<Quest> findAllByQuestTypeOrderByQuestGoal(QuestCategory questCategory);
}
