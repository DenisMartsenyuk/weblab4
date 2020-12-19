package ru.lab.weblab4.security.jwt;

import ru.lab.weblab4.model.User;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(user.getId(), user.getUsername(), user.getPassword());
    }
}
