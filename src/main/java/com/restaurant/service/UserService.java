package com.restaurant.service;

import com.restaurant.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
