package io.github.jhlee.controller;

import io.github.jhlee.model.User;
import io.github.jhlee.response.ApiResponse;
import io.github.jhlee.response.Status;
import io.github.jhlee.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<Integer>> create(@RequestBody User user) {
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

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<User>>> readList() {
        log.info("readList");

        List<User> users = userService.readList();

        return new ResponseEntity<>(
                ApiResponse.success(Status.OK, users),
                Status.OK.getHttpStatus()
        );
    }

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
