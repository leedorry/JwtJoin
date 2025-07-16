package io.github.jhlee.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private int code;
    private String message;
    private T data;

    public ApiResponse(Status status, T data) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = data;
    }

    public static <T> ApiResponse<T> success(Status status, T data) {
        return new ApiResponse<>(status, data);
    }

    public static <T> ApiResponse<T> error(Status status) {
        return new ApiResponse<>(status, null);
    }
}
