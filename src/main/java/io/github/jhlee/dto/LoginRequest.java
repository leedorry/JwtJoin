package io.github.jhlee.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class LoginRequest {

    @ApiParam("사용자 아이디")
    String id;

    @ApiParam("비밀번호")
    String password;

}
