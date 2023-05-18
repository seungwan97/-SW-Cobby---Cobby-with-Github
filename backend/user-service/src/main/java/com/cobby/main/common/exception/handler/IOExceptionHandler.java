package com.cobby.main.common.exception.handler;

import java.io.IOException;
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/exception/handler/IOExceptionHandler.java

=======
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/exception/handler/IOExceptionHandler.java
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/exception/handler/IOExceptionHandler.java
=======
import com.cobby.main.common.mattermost.NotificationManager;

import jakarta.servlet.http.HttpServletRequest;
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/exception/handler/IOExceptionHandler.java
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class IOExceptionHandler {

<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/exception/handler/IOExceptionHandler.java
=======
	@Autowired
	private NotificationManager notificationManager;
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/exception/handler/IOExceptionHandler.java
	/**
	 * 파일시스템으로부터 이미지 파일을 저장하거나 가져오는 과정에서 발생하는 IOException 을 처리하는
	 * exception handler 입니다.
	 * @param ex IOException
	 * @return ProblemDetail (RFC 7807 양식의 ResponseBody)
	 */
	@ExceptionHandler(IOException.class)
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/exception/handler/IOExceptionHandler.java
	public ProblemDetail handleIO(IOException ex) {
=======
	public ProblemDetail handleIO(IOException ex, HttpServletRequest req) {
		notificationManager.sendNotification(ex, req.getRequestURI(), getParams(req));

>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/exception/handler/IOExceptionHandler.java

		var problemDetail =  ProblemDetail
			.forStatusAndDetail(
				HttpStatus.NOT_FOUND,
				"파일 처리에 문제가 발생했습니다.");

		problemDetail.setProperty("error", new StringBuilder()
			.append("(")
			.append(ex.getMessage().split("\\(")[1])
			.toString());

		return problemDetail;
	}
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/exception/handler/IOExceptionHandler.java
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
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/exception/handler/IOExceptionHandler.java
}
