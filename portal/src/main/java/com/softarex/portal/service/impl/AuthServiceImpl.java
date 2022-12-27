package com.softarex.portal.service.impl;

import com.softarex.portal.dto.LoginDto;
import com.softarex.portal.dto.TokenDto;
import com.softarex.portal.exceptions.WrongLoginCredentialsException;
import com.softarex.portal.model.Role;
import com.softarex.portal.model.User;
import com.softarex.portal.security.JwtProvider;
import com.softarex.portal.service.AuthService;
import com.softarex.portal.service.MailSender;
import com.softarex.portal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final MailSender mailSender;

    @Override
    public TokenDto login(LoginDto loginDto) {
        User user = userService.getByUsername(loginDto.getUsername());
        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            String name = user.getFirstName() + " " + user.getLastName();
            String tokenValue = jwtProvider.generateToken(user.getEmail(), name);
            return new TokenDto(tokenValue);
        }
        throw new WrongLoginCredentialsException("Wrong login credentials");
    }

    @Override
    public User register(User user) {
        user.setRoles(Set.of(new Role(1L, "USER")));
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userService.save(user);
    }

    @Override
    @Transactional
    public void changePassword(String email, String oldPassword, String newPassword) {
        User user = userService.getByUsername(email);
        String password = user.getPassword();
        if (passwordEncoder.matches(oldPassword, password)) {
            user.setPassword(passwordEncoder.encode(newPassword));
            mailSender.sendMessage(email, "Password has been changed", "New password");
        } else {
            mailSender.sendMessage(email, "Attempt to change password", "New password");
            throw new WrongLoginCredentialsException("Password doesnt match");
        }
    }
}