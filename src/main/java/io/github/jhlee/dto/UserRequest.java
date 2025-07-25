package io.github.jhlee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserRequest {

    @Schema(description = "아이디", example = "lee")
    private String id;

    @Schema(description = "이메일", example = "lee@gmail.com")
    private String email;

    @Schema(description = "비밀번호", example = "abc@123")
    private String password;

    @Schema(description = "이름", example = "리")
    private String name;

    @Schema(description = "나이", example = "20")
    private int age;

}
