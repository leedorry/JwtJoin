package io.github.jhlee.Controller;

import io.github.jhlee.Response.Result;
import io.github.jhlee.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("")
    public Result create(){
        return null;
    }

    @GetMapping("/{id}")
    public Result read(@PathVariable String id) {
        log.info("read; id={}", id);
        return null;
    }

    @GetMapping("")
    public Result readList() {
        return userService.readList();
    }

    @PatchMapping("")
    public Result update() {
        return null;
    }

    @DeleteMapping("")
    public Result delete() {
        return null;
    }

}
