package com.cobby.main.badge.api.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.cobby.main.activitylog.api.dto.response.CommitLogResponse;
import com.cobby.main.activitylog.api.service.ActivityLogService;
import com.cobby.main.badge.api.dto.response.BadgeGetResponse;
import com.cobby.main.badge.api.service.BadgeService;
import com.cobby.main.common.exception.NotFoundException;
import com.cobby.main.stat.db.repository.StatRepository;
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
	private final StatRepository statRepository;
	private final ActivityLogService activityLogService;

	/*
		ex ) ![daeeun1's github stats](https://github-readme-stats.vercel.app/api?username=daeeun1&show_icons=true&theme=tokyonight)
	 	이렇게 요청하면 들어오기에 들어오는 정보는 nickname밖에 없다!
	 */
	@Override
	public String getBadge(String nickname) {
		String svgContent = getSvg(nickname);

		return svgContent;
	}

	private String getCustome(String name){
		log.info("s3 url : " + bucketName + "base64/"  + name + ".txt");
		var custome = amazonS3Client.getObjectAsString(bucketName, "base64/"  + name + ".txt");
		return custome.toString();
	}


	// 인코딩 한 것을 올리고 이름은 같게 맞추고 거서
	// 입고 있는 옷 가지고 오기 ...
	private String getCharacter(){
		var character = amazonS3Client.getObjectAsString(bucketName, "character/cobby.txt");
		return character.toString();
	}

	private String getCharacterPng(){
		var character = amazonS3Client.getObjectAsString(bucketName, "character/cobbyPng.txt");
		return character.toString();
	}

	private String getFont(){
		var character = amazonS3Client.getObjectAsString(bucketName, "character/DNFBitBitTTF.txt");
		return "data:font/ttf;base64," + character.toString();
	}

	// 아바타 정보 lv, outfit
	private BadgeGetResponse getAvatar(String nickname){
		var findUser = userRepository.findByNickname(nickname).orElseThrow(NotFoundException::new);

		OkHttpClient client = new OkHttpClient();
		Request.Builder builder = new Request.Builder()
			.url(mainUrl + "/avatars/server")
			.addHeader("userId", findUser.getId());
		Request request = builder.build();
		log.info(mainUrl + "/avatars");

		try {
			Response response = client.newCall(request).execute();
			if (response.code() == 200) {

				JSONObject contentObject = new JSONObject(response.body().string()).getJSONObject("content");
				log.info(contentObject.toString());
				JSONArray costumeArray = contentObject.getJSONArray("costumes");
				log.info("코스튬 목록입니다 : " + contentObject.getJSONArray("costumes").toString());
				String head = "";
				String effect = "";
				String body = "";

				for(int i = 0; i < costumeArray.length(); i++){
					var name = costumeArray.getJSONObject(i).getString("name");
					var category = costumeArray.getJSONObject(i).getString("category");
					if(category.equals("BODY")) body = name;
					else if(category.equals("HEAD")) head = name;
					else if(category.equals("EFFECT")) effect = name;
				}

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

	private CommitLogResponse getCommitCnt(String nickname){
		var findUser = userRepository.findByNickname(nickname).orElseThrow(NotFoundException::new);
		log.info(String.valueOf(findUser));
		return activityLogService.getCommitActivityLog(findUser.getId());
	}

	private Long getStatCommitCnt(String id){
		var findStat = statRepository.findById(id).orElseThrow(NotFoundException::new);
		return findStat.getCommitCnt();
	}

	private String findById(String nickname){
		var findUser = userRepository.findByNickname(nickname).orElseThrow(NotFoundException::new);
		return findUser.getId();
	}

	public String getSvg(String nickname){
		BadgeGetResponse badgeGetResponse = getAvatar(nickname);
		String body = "";
		String effect = "";
		String head = "";

		if(!badgeGetResponse.getBody().isEmpty()) body = getCustome(badgeGetResponse.getBody());
		if(!badgeGetResponse.getEffect().isEmpty()) effect = getCustome(badgeGetResponse.getEffect());
		if(!badgeGetResponse.getHead().isEmpty()) head = getCustome(badgeGetResponse.getHead());

		StringBuilder svg = new StringBuilder();
		svg.append("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"600\" height=\"285\">\n")
			.append("<style>\n")
			.append("    .cobby { width: 130px; }\n")
			.append("    .info {\n")
			.append("        width: 300px;\n")
			.append("        display: flex;\n")
			.append("        flex-direction: column;\n")
			.append("        align-items: center;\n")
			.append("        padding: 50px;\n")
			.append("    }\n")
			.append("    .info_line {\n")
			.append("        font-family: 'Cobby', sans-serif;\n")
			.append("        font-size: 20px;\n")
			.append("        font-weight: bolder;\n")
			.append("        fill: #ffffff;\n")
			.append("        margin-bottom: 20px;\n")
			.append("    }\n")
			.append("    @font-face {\n")
			.append("        font-fmily: 'Cobby';\n")
			.append("        src: url('"+ getFont() + "');\n")
			.append("    }\n")
			.append("    .line {\n")
			.append("        letter-spacing: 1px;\n")
			.append("        font-family: 'Cobby';\n")
			.append("        font-size: 11px;\n")
			.append("        fill: #FFFFF8;\n")
			.append("    }\n")
			.append("    .text_info {\n")
			.append("        letter-spacing: 2px;\n")
			.append("        font-size: 35px;\n")
			.append("        fill: #FEEBB6;\n")
			.append("    }\n")
			.append("    .text_info_0 {\n")
			.append("        letter-spacing: 2px;\n")
			.append("        font-size: 35px;\n")
			.append("    }\n")
			.append("    .text_info_1 {\n")
			.append("        letter-spacing: 2px;\n")
			.append("        font-size: 16px;\n")
			.append("    }\n")
			.append("    .text_info_2 {\n")
			.append("        letter-spacing: 2px;\n")
			.append("        font-size: 35px;\n")
			.append("        fill: #FEEBB6;\n")
			.append("    }\n")
			.append("</style>\n")
			.append("<rect class=\"card\" x=\"0\" y=\"0\" />\n")
			.append("<image href=\"data:image/png;base64," + getCharacterPng() + "\" x=\"0\" y=\"0\" alt=\"cobby\" />\n")
			.append("<image class='cobby' href=\"data:image/gif;base64," + getCharacter() + "\" x=\"93\" y=\"91\" width=\"300px\" alt=\"cobby\" />\n")
			.append("<image class='cobby' href=\"data:image/gif;base64," + body + "\" x=\"93\" y=\"91\" width=\"300px\" alt=\"head\" />\n")
			.append("<image class='cobby' href=\"data:image/gif;base64," + effect + "\" x=\"93\" y=\"91\" width=\"300px\" alt=\"body\" />\n")
			.append("<image class='cobby' href=\"data:image/gif;base64," + head + "\" x=\"93\" y=\"91\" width=\"300px\" alt=\"effect\" />\n")
			.append("<g class=\"info\">\n")
			.append("    <text class=\"info_line text_info\" x=\"270\" y=\"90\" text-anchor=\"left\">\n")
			.append("       " + nickname + "'s \n")
			.append("    </text>\n")
			.append("    <text class=\"info_line text_info_0\" x=\"270\" y=\"130\" text-anchor=\"left\">\n")
			.append("       Cobby\n")
			.append("    </text>\n")
			.append("    <text class=\"info_line text_info_1\" x=\"270\" y=\"170\" text-anchor=\"left\">\n")
			.append("      Total Commits : " + getStatCommitCnt(findById(nickname)) + "\n")
			.append("    </text>\n")
			.append("    <text class=\"info_line text_info_1\" x=\"270\" y=\"195\" text-anchor=\"left\">\n")
			.append("      Today's Commits : " + getCommitCnt(nickname).getTodayCnt() + "\n")
			.append("    </text>\n")
			.append("    <text class=\"info_line text_info_1\" x=\"270\" y=\"220\" text-anchor=\"left\">\n")
			.append("      Consecutive Commits : " + getCommitCnt(nickname).getRelayCnt() + "\n")
			.append("    </text>\n")
			.append("    <text class=\"info_line text_info_2\" x=\"108\" y=\"60\" text-anchor=\"left\">\n")
			.append("      Lv. " + badgeGetResponse.getLevel() + "\n")
			.append("    </text>\n")
			.append("	 <text class=\"line\" x=\"77\" y=\"256\" text-anchor=\"left\">\n")
			.append("  		<a href=\"https://cobby-play.com\" target=\"_blank\">\n")
			.append("    	@ https://cobby-play.com\n")
			.append("  	</a>\n")
			.append("</text>\n")
			.append("  </g>\n")
			.append("</svg>");
		return svg.toString();
	}

}
