package com.softarex.portal.service;

import com.softarex.portal.model.User;

public interface UserService {
    User save(User user);

    User getByUsername(String username);

    User updateUser(User userToUpdate, Long id);
}
