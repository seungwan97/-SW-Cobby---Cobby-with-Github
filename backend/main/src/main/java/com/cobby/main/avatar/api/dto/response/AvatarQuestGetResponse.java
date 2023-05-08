package com.cobby.main.avatar.api.dto.response;

import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import com.cobby.main.quest.db.entity.Quest;

import lombok.Builder;
import lombok.Data;

@Data
public class AvatarQuestGetResponse {

	private Long avatarQuestId;

	private QuestGetResponse quest;

	@Builder
	public AvatarQuestGetResponse(Long avatarQuestId, Quest quest) {
		this.avatarQuestId = avatarQuestId;
		this.quest = QuestGetResponse.builder()
			.quest(quest)
			.build();
	}

}
