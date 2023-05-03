// package com.cobby.main.common.exception.handler;
//
// import java.io.IOException;
// import java.util.Enumeration;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ProblemDetail;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
//
// import com.cobby.main.common.mattermost.NotificationManager;
//
// import jakarta.servlet.http.HttpServletRequest;
// import lombok.extern.slf4j.Slf4j;
//
// @Slf4j
// @ControllerAdvice
// public class IOExceptionHandler {
//
// 	@Autowired
// 	private NotificationManager notificationManager;
// 	/**
// 	 * 파일시스템으로부터 이미지 파일을 저장하거나 가져오는 과정에서 발생하는 IOException 을 처리하는
// 	 * exception handler 입니다.
// 	 * @param ex IOException
// 	 * @return ProblemDetail (RFC 7807 양식의 ResponseBody)
// 	 */
// 	@ExceptionHandler(IOException.class)
// 	public ProblemDetail handleIO(IOException ex, HttpServletRequest req) {
// 		notificationManager.sendNotification(ex, req.getRequestURI(), getParams(req));
//
//
// 		var problemDetail =  ProblemDetail
// 			.forStatusAndDetail(
// 				HttpStatus.NOT_FOUND,
// 				"파일 처리에 문제가 발생했습니다.");
//
// 		problemDetail.setProperty("error", new StringBuilder()
// 			.append("(")
// 			.append(ex.getMessage().split("\\(")[1])
// 			.toString());
//
// 		return problemDetail;
// 	}
//
// 	private String getParams(HttpServletRequest req) {
// 		StringBuilder params = new StringBuilder();
// 		Enumeration<String> keys = req.getParameterNames();
// 		while (keys.hasMoreElements()) {
// 			String key = keys.nextElement();
// 			params.append("- ").append(key).append(" : ").append(req.getParameter(key)).append("/n");
// 		}
//
// 		return params.toString();
// 	}
// }
