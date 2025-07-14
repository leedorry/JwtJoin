package io.github.jhlee.Mapper;

import io.github.jhlee.Model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> readList();

}
