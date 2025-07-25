package io.github.jhlee.service;

import io.github.jhlee.dto.UserRequest;
import io.github.jhlee.mapper.UserMapper;
import io.github.jhlee.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public int create(UserRequest request) {
        log.info("create; request={}", request);

        // @TODO : 1. Valid

        // @TODO : 2. findById
        // @TODO : 2-1. 중복된 아이디가 있다면 예외 처리

        User user = modelMapper.map(request, User.class);

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);

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

    public boolean findByUser(UserRequest user) {
        log.info("findByUser; user={}", user);
        return userMapper.findByUser(user);
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
