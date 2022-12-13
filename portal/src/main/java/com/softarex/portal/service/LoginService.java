package com.softarex.portal.service;

import com.softarex.portal.dto.LoginDto;
import com.softarex.portal.dto.TokenDto;
import com.softarex.portal.model.User;

public interface LoginService {
    User register(User user);

    TokenDto login(LoginDto loginDto);
}
