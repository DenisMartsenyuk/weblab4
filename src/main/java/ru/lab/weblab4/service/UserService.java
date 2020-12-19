package ru.lab.weblab4.service;

import ru.lab.weblab4.model.User;

public interface UserService {
    User register(User user);
    User findByUsername(String username);
}
