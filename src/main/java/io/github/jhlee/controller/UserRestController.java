package io.github.jhlee.controller;

import io.github.jhlee.dto.UserRequest;
import io.github.jhlee.model.User;
import io.github.jhlee.response.ApiResponse;
import io.github.jhlee.response.Status;
import io.github.jhlee.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @Operation(summary = "회원 가입 요청",
            description = "회원 정보가 추가됩니다.",
            tags = {"User Controller"}
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "CREATED")
    })
    @PostMapping("")
    public ResponseEntity<ApiResponse<Integer>> create(@Valid @RequestBody UserRequest user) {
        log.info("create; user={}", user);

        int created = userService.create(user);

        if (created == 0) {
            return new ResponseEntity<>(
                    ApiResponse.success(Status.OK, created),
                    Status.OK.getHttpStatus()
            );
        } else
            return new ResponseEntity<>(
                    ApiResponse.success(Status.CREATED, created),
                    Status.CREATED.getHttpStatus()
            );
    }

    @Operation(summary = "회원 조회",
            description = "회원을 조회합니다.",
            tags = {"User Controller"}
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> read(@PathVariable String id) {
        log.info("read; id={}", id);

        User user = userService.read(id);

        if (user == null) {
            return new ResponseEntity<>(
                    ApiResponse.error(Status.NOT_FOUND),
                    Status.NOT_FOUND.getHttpStatus()
            );
        } else
            return new ResponseEntity<>(
                    ApiResponse.success(Status.OK, user),
                    Status.OK.getHttpStatus()
            );
    }

    @Operation(summary = "회원 리스트 조회", description = "회원 리스트를 조회합니다.", tags = {"User Controller"})
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
    })
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<User>>> readList() {
        log.info("readList");

        List<User> users = userService.readList();

        return new ResponseEntity<>(
                ApiResponse.success(Status.OK, users),
                Status.OK.getHttpStatus()
        );
    }

    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.", tags = {"User Controller"})
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "202", description = "NO CONTENT"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @PatchMapping("")
    public ResponseEntity<ApiResponse<Integer>> update(@RequestBody User user) {
        log.info("update; user={}", user);

        int updated = userService.update(user);

        if (updated == 0) {
            return new ResponseEntity<>(
                    ApiResponse.error(Status.NOT_FOUND),
                    Status.NOT_FOUND.getHttpStatus()
            );
        } else
            return new ResponseEntity<>(
                    ApiResponse.success(Status.NO_CONTENT, updated),
                    Status.NO_CONTENT.getHttpStatus()
            );
    }

    @Operation(summary = "회원 정보 삭제", description = "회원 정보를 삭제합니다.", tags = {"User Controller"})
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "202", description = "NO CONTENT"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable String id) {
        log.info("delete; id={}", id);

        int deleted = userService.delete(id);

        if (deleted == 0) {
            return new ResponseEntity<>(
                    ApiResponse.error(Status.NOT_FOUND),
                    HttpStatus.NOT_FOUND
            );
        } else
            return new ResponseEntity<>(
                    ApiResponse.success(Status.NO_CONTENT, deleted),
                    HttpStatus.NO_CONTENT
            );
    }

}
