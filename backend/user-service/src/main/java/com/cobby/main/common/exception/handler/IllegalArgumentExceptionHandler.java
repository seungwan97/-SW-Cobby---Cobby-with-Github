package com.cobby.main.common.exception.handler;

<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/exception/handler/IllegalArgumentExceptionHandler.java
=======
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/exception/handler/IllegalArgumentExceptionHandler.java
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/exception/handler/IllegalArgumentExceptionHandler.java
@ControllerAdvice
public class IllegalArgumentExceptionHandler {

=======
import com.cobby.main.common.mattermost.NotificationManager;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class IllegalArgumentExceptionHandler {

	@Autowired
	private NotificationManager notificationManager;

>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/exception/handler/IllegalArgumentExceptionHandler.java
	/**
	 * IllegalArgumentException 예외를 내부 서버 에러 (500 Internal Server Error) 이 아닌
	 * 클라이언트의 요청 에러 (400 Bad Request)로 표시하기 위해 ExceptionHandler 를 정의했습니다.
	 *
	 *
	 * @param ex IllegalArgumentException
	 * @return ProblemDetail (RFC 7807 양식의 ResponseBody)
	 */
	@ExceptionHandler(IllegalArgumentException.class)
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/exception/handler/IllegalArgumentExceptionHandler.java
	public ProblemDetail handleIllegalArgument(IllegalArgumentException ex) {
=======
	public ProblemDetail handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest req) {
		notificationManager.sendNotification(ex, req.getRequestURI(), getParams(req));
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/exception/handler/IllegalArgumentExceptionHandler.java

		return ProblemDetail
			.forStatusAndDetail(
				HttpStatus.BAD_REQUEST,
				ex.getMessage());
	}

<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/exception/handler/IllegalArgumentExceptionHandler.java
=======
	private String getParams(HttpServletRequest req) {
		StringBuilder params = new StringBuilder();
		Enumeration<String> keys = req.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			params.append("- ").append(key).append(" : ").append(req.getParameter(key)).append("/n");
		}

		return params.toString();
	}

>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/exception/handler/IllegalArgumentExceptionHandler.java
}

