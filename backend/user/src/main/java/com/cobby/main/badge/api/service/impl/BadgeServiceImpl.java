package com.cobby.main.badge.api.service.impl;


import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.cobby.main.activitylog.api.service.ActivityLogService;
import com.cobby.main.badge.api.dto.response.BadgeGetResponse;
import com.cobby.main.badge.api.service.BadgeService;
import com.cobby.main.common.exception.NotFoundException;
import com.cobby.main.user.db.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
@Slf4j
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;

	@Value("${request.main.url}")
	private String mainUrl;
	private final AmazonS3 amazonS3Client;
	private final UserRepository userRepository;
	private final ActivityLogService activityLogService;

	/*
		ex ) ![daeeun1's github stats](https://github-readme-stats.vercel.app/api?username=daeeun1&show_icons=true&theme=tokyonight)
	 	이렇게 요청하면 들어오기에 들어오는 정보는 nickname밖에 없다!
	 */
	@Override
	public String getBadge(String nickname) {
		log.info(getAvatar(nickname).toString());
		String svgContent = getSvg(nickname);

		return svgContent;
	}


	// 인코딩 한 것을 올리고 이름은 같게 맞추고 거서
	// 입고 있는 옷 가지고 오기 ...
	private String getCharacter(){

		var character = amazonS3Client.getObjectAsString(bucketName, "character/cobby.txt");

		return character.toString();
	}

	// 아바타 정보 lv, outfit
	private BadgeGetResponse getAvatar(String nickname){
		var findUser = userRepository.findByNickname(nickname).orElseThrow(NotFoundException::new);

		OkHttpClient client = new OkHttpClient();
		Request.Builder builder = new Request.Builder()
			.url(mainUrl + "/avatars")
			.addHeader("userId", findUser.getId());
		Request request = builder.build();

		try {
			Response response = client.newCall(request).execute();
			if (response.code() == 200) {

				JSONObject jsonObject = new JSONObject(response.body().string());
				log.info(jsonObject.toString());
				JSONObject contentObject = jsonObject.getJSONObject("content");
				JSONObject outfitsObject = contentObject.getJSONObject("outfits");
				Map<String, Object> outfitsMap = outfitsObject.toMap();

				String head = (String)outfitsMap.get("head");
				String effect = (String)outfitsMap.get("effect");
				String body = (String)outfitsMap.get("body");
				log.info("현재 입고 있는 것 : " + head);
				log.info("현재 입고 있는 것 : " + effect);
				log.info("현재 입고 있는 것 : " + body);

				var badgeGetResponse = BadgeGetResponse.builder()
					.level(contentObject.getInt("level"))
					.head(head)
					.effect(effect)
					.body(body)
					.build();
				return badgeGetResponse;

			} else {
				System.out.println("API 요청이 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Long getCommitCnt(String nickname){
		var findUser = userRepository.findByNickname(nickname).orElseThrow(NotFoundException::new);
		log.info(String.valueOf(findUser));
		return activityLogService.getActivityLogCommit(findUser.getId()).getTodayCnt();
	}

	public String getSvg(String nickname){
		StringBuilder svg = new StringBuilder();
		svg.append("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"500\" height=\"350\" viewBox=\"0 0 700 350\">\n")
			.append("\t<style>\n")
			.append("\t\t.card {\n")
			.append("\t\t\tstroke: black;\n")
			.append("\t\t\tstroke-width: 1px;\n")
			.append("\t\t\tfill: #333333;\n")
			.append("\t\t\tdisplay: flex;\n")
			.append("\t\t\talign-items: center;\n")
			.append("\t\t\twidth: 700px;\n")
			.append("\t\t\theight: 350px;\n")
			.append("\t\t}\n")
			.append("\n")
			.append("\t\t.character {\n")
			.append("\t\t\twidth: 300px;\n")
			.append("\t\t\theight: 300px;\n")
			.append("\t\t}\n")
			.append("\n")
			.append("\t\t.info {\n")
			.append("\t\t\twidth: 300px;\n")
			.append("\t\t\tdisplay: flex;\n")
			.append("\t\t\tflex-direction: column;\n")
			.append("\t\t\talign-items: center;\n")
			.append("\t\t\tpadding: 50px;\n")
			.append("\t\t}\n")
			.append("\n")
			.append("\t\t.info_line {\n")
			.append("\t\t\tfont-size: 20px;\n")
			.append("\t\t\tfont-weight: bolder;\n")
			.append("\t\t\tfill: #ffffff;\n")
			.append("\t\t\tmargin-bottom: 20px;\n")
			.append("\t\t}\n")
			.append("\t</style>\n")
			.append("\n")
			.append("\t<rect class=\"card\" x=\"0\" y=\"0\" />\n")
			.append("\n")
			.append("\t<image href=\"data:image/gif;base64," + getCharacter() + "\" x=\"0\" y=\"0\" width=\"300px\" alt=\"cobby\" />\n")
			.append("\t<image href=\"data:image/gif;base64,\" x=\"0\" y=\"0\" width=\"300px\" alt=\"body\" />\n")
			.append("\t<image href=\"data:image/gif;base64,\" x=\"0\" y=\"0\" width=\"300px\" alt=\"head\" />\n")
			.append("\n")
			.append("\t<g class=\"info\">\n")
			.append("\t\t<text class=\"info_line\" x=\"500\" y=\"100\" text-anchor=\"middle\">" + nickname + "'s COBBY</text>\n")
			.append("\t\t<text class=\"info_line\" x=\"500\" y=\"175\" text-anchor=\"middle\">LV " + getAvatar(nickname).getLevel() + " </text>\n")
			.append("\t\t<text class=\"info_line\" x=\"500\" y=\"250\" text-anchor=\"middle\">Today's Commits : " + getCommitCnt(nickname) +"</text>\n")
			.append("\t</g>\n")
			.append("</svg>\n");
		return svg.toString();
	}


}
