package io.github.jhlee.Response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Status {

    OK(HttpStatus.OK, "요청이 성공적으로 처리되었습니다."),                                // 200
    CREATED(HttpStatus.CREATED, "리소스가 생성되었습니다."),                                     // 201
    NO_CONTENT(HttpStatus.NO_CONTENT, ""),                               // 202
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),                           // 400
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),                         // 401
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근이 거부되었습니다."),                            // 402
    NOT_FOUND(HttpStatus.NOT_FOUND, "리소스를 찾을 수 없습니다."),                        // 404
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다."); // 500

    private final HttpStatus httpStatus;
    private final String message;

    Status(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getCode() {
        return httpStatus.value();
    }

}
