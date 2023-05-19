package com.cobby.store.common.response;

import org.springframework.http.HttpHeaders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 응답 컨텐츠에 상태값을 함께 담아서 주기 위해 ResponseBody 에 들어갈 공통 body 양식을 정의한 클래스입니다.
 * <p>
 * 구성 요소는 다음과 같습니다.<p>
 * - status:  상태 코드 넘버 (ex. 200)<p>
 * - headers:  Content-Type (ex. MediaType.IMAGE_SVG_XML) <p>
 * - content: 컨텐츠        (ex. Object)
 *
 * @param <T>
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SvgResponseBody<T> {

  private T content;

  private HttpHeaders headers;

  private Integer status;



}

