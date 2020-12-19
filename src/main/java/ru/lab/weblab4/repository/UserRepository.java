package ru.lab.weblab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.weblab4.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
