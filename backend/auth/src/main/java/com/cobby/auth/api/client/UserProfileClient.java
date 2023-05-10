package com.cobby.auth.api.client;

import com.cobby.auth.api.dto.UserInfoDto;
import com.cobby.auth.common.model.BaseResponseBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-profile-client", url = "${request.url.user-server}")
public interface UserProfileClient {
    @PostMapping
    ResponseEntity<? extends BaseResponseBody> insertProfile(@RequestBody UserInfoDto userInfoDto);
}
