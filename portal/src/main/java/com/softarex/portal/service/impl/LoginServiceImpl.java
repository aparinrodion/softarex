package com.softarex.portal.service.impl;

import com.softarex.portal.dto.LoginDto;
import com.softarex.portal.dto.TokenDto;
import com.softarex.portal.exceptions.WrongLoginCredentialsException;
import com.softarex.portal.model.Role;
import com.softarex.portal.model.User;
import com.softarex.portal.security.JwtProvider;
import com.softarex.portal.service.LoginService;
import com.softarex.portal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Override
    public TokenDto login(LoginDto loginDto) {
        User user = userService.getByUsername(loginDto.getUsername());
        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            String name = user.getFirstName() + " " + user.getLastName();
            String tokenValue = jwtProvider.generateToken(user.getEmail(), name);
            return new TokenDto(tokenValue);
        }
        throw new WrongLoginCredentialsException("Wrong login credentials " + loginDto.getUsername());
    }

    @Override
    public User register(User user) {
        user.setRoles(Set.of(new Role(1L, "USER")));
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        System.out.println(encodedPassword);
        user.setPassword(encodedPassword);
        return userService.save(user);
    }
}