package com.softarex.portal.controller;

import com.softarex.portal.dto.LoginDto;
import com.softarex.portal.dto.RegistrationDto;
import com.softarex.portal.dto.TokenDto;
import com.softarex.portal.dto.UserDto;
import com.softarex.portal.mapper.RegistrationMapper;
import com.softarex.portal.mapper.UserMapper;
import com.softarex.portal.model.User;
import com.softarex.portal.service.LoginService;
import com.softarex.portal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;
    private final LoginService loginService;
    private final RegistrationMapper registrationMapper;

    @PostMapping(value = "/register")
    public UserDto registerUser(@RequestBody RegistrationDto registrationDto) {
        User userToSave = registrationMapper.mapToEntity(registrationDto);
        User savedUser = loginService.register(userToSave);
        return userMapper.mapToDto(savedUser);
    }

    @PostMapping(value = "/login")
    public TokenDto login(@RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }
}
