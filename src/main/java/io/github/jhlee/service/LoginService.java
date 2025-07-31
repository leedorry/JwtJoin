package io.github.jhlee.service;

import io.github.jhlee.mapper.UserMapper;
import io.github.jhlee.model.LoginUserDetails;
import io.github.jhlee.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userMapper.findById(id);

        if (user == null)
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + id);

        return new LoginUserDetails(user);
    }
}
