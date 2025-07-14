package io.github.jhlee.Service;

import io.github.jhlee.Mapper.UserMapper;
import io.github.jhlee.Response.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public Result readList() {
        log.info("readList;");

        Result result = new Result();
        result.setCode(200);
        result.setMessage("success");
        result.setPayload(userMapper.readList());

        return result;
    }

}
