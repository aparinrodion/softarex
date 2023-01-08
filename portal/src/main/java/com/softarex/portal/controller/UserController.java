package com.softarex.portal.controller;

import com.softarex.portal.dto.*;
import com.softarex.portal.mapper.RegistrationMapper;
import com.softarex.portal.mapper.UserMapper;
import com.softarex.portal.model.User;
import com.softarex.portal.security.CustomUserDetails;
import com.softarex.portal.service.AuthService;
import com.softarex.portal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserMapper userMapper;
    private final AuthService authService;
    private final RegistrationMapper registrationMapper;
    private final UserService userService;

    @PostMapping(value = "/register")
    public UserDto registerUser(@RequestBody RegistrationDto registrationDto) {
        User userToSave = registrationMapper.mapToEntity(registrationDto);
        User savedUser = authService.register(userToSave);
        return userMapper.mapToDto(savedUser);
    }

    @PostMapping(value = "/login")
    public TokenDto login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @Secured("ROLE_USER")
    @PostMapping(value = "/change-password/")
    public void changePassword(@RequestBody ResetPasswordDto resetPasswordDto, Principal principal) {
        authService.changePassword(principal.getName(),
                resetPasswordDto.getOldPassword(),
                resetPasswordDto.getNewPassword());
    }

    @Secured("ROLE_USER")
    @PostMapping(value = "/profile/update")
    public UserDto updateUserInfo(@RequestBody UserDto userDto, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userMapper.mapToEntity(userDto);
        user = userService.updateUser(user, customUserDetails.getId());
        return userMapper.mapToDto(user);
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/profile")
    public UserDto getProfile(Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userService.getUserById(customUserDetails.getId());
        return userMapper.mapToDto(user);
    }
}
