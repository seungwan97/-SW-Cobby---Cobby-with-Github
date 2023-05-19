package com.cobby.store.common.logger;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class RequestAndResponseLoggingAspect {
	@Around("within(com.cobby.main.*.controller..*)")
	public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		try {
			return joinPoint.proceed(joinPoint.getArgs());
		} finally {
			log.info("Method " + request.getMethod() + " [" + ZonedDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")) + "] "
				+ request.getRequestURI() + " from " + request.getRemoteAddr());
		}
	}

}
