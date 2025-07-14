package io.github.jhlee.Response;

import lombok.Data;

@Data
public class Result {

    int code;
    String message;
    Object payload;

}
