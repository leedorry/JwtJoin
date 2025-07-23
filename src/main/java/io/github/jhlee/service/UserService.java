package io.github.jhlee.service;

import io.github.jhlee.mapper.UserMapper;
import io.github.jhlee.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public int create(User user) {
        log.info("create; user={}", user);
        return userMapper.create(user);
    }

    public User read(String id) {
        log.info("read; id={}", id);
        return userMapper.read(id);
    }

    public List<User> readList() {
        log.info("readList;");
        return userMapper.readList();
    }

    public int update(User user) {
        log.info("update; user={}", user);
        return userMapper.update(user);
    }

    public int delete(String id) {
        log.info("delete; id={}", id);
        return userMapper.delete(id);
    }

}
