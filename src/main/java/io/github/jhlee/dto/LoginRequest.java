package io.github.jhlee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginRequest {

    @Schema(description = "아이디", example = "lee")
    String id;

    @Schema(description = "비밀번호", example = "abc@123")
    String password;

}
