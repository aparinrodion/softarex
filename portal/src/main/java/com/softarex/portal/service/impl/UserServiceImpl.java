package com.softarex.portal.service.impl;

import com.softarex.portal.exceptions.UserNotFoundException;
import com.softarex.portal.model.User;
import com.softarex.portal.repository.UserRepository;
import com.softarex.portal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
