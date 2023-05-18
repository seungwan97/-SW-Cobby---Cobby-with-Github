package com.cobby.main.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/exception/NotFoundException.java
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super("해당 정보를 찾을 수 없습니다.");
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
=======
	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super("해당 정보를 찾을 수 없습니다.");
	}

	public NotFoundException(String msg) {
		super(msg);
	}
}
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/exception/NotFoundException.java
