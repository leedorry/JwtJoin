package io.github.jhlee.Mapper;

import io.github.jhlee.Model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int create(User user);
    List<User> readList();
    User read(String id);
    int update(User user);
    int delete(String id);

}
