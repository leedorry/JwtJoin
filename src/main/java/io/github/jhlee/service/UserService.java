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

        User existId = userMapper.findById(request.getUsername());
        if (existId != null) {
            throw new IllegalArgumentException("중복된 id가 존재합니다.");
        }

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

    public int update(User user) {
        log.info("update; user={}", user);
        return userMapper.update(user);
    }

    public int delete(String id) {
        log.info("delete; id={}", id);
        return userMapper.delete(id);
    }

}
