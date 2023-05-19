package com.cobby.auth.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 서버 요청에대한 기본 응답값(바디) 정의.
 * 응답 컨텐츠에 상태값을 함께 담아서 주기 위해 ResponseBody 에 들어갈 공통 body 양식을 정의한 클래스입니다. 구성 요소는 다음과 같습니다. - status:
 * 상태 코드 넘버 (ex. 200) - naming:  상태 코드 이름 - content: 컨텐츠
 *
 * @param <T>
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseResponseBody<T> {
    private Integer statusCode;

    private String message;

    private T content;

}
