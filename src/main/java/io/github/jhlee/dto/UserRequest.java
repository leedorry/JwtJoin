package io.github.jhlee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @Schema(description = "아이디", example = "lee")
    @NotBlank(message = "아이디는 필수입니다.")
    private String username;

    @Schema(description = "이메일", example = "lee@gmail.com")
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Schema(description = "비밀번호", example = "abc@123")
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @Schema(description = "이름", example = "리")
    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Schema(description = "나이", example = "20")
    private int age;

}
