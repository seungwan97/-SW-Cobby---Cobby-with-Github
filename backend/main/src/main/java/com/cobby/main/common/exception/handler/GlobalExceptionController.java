// package com.cobby.main.common.exception.handler;
//
// import java.util.ArrayList;
// import java.util.Enumeration;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
//
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.BindingResult;
// import org.springframework.validation.FieldError;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
//
// import com.cobby.main.common.mattermost.NotificationManager;
//
// import jakarta.servlet.http.HttpServletRequest;
// import lombok.RequiredArgsConstructor;
//
// @ControllerAdvice
// @RequiredArgsConstructor
// public class GlobalExceptionController {
//
// 	private final NotificationManager notificationManager;
//
// 	@ExceptionHandler(Exception.class)
// 	public ResponseEntity<?> mattermostSender(Exception e, HttpServletRequest request) {
// 		e.printStackTrace();
// 		notificationManager.sendNotification(e, request.getRequestURI(), getParams(request));
//
// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
// 	}
//
// 	@ExceptionHandler(MethodArgumentNotValidException.class)
// 	public ResponseEntity<?> handleValidationExceptions(BindingResult bindingResult) {
// 		Map<String, List<String>> errors = new HashMap<>();
// 		List<String> errorList = new ArrayList<>();
// 		bindingResult.getAllErrors().forEach(c -> errorList.add(((FieldError)c).getField() + " : " + c.getDefaultMessage()));
// 		errors.put("errors", errorList);
// 		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
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
