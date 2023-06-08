package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserDAO {
        void add(User user);
        User getUserById(Integer id);
        List<User> listUsers();
        void updateUser(User user);
        void deleteUser(Integer id);
        User getUserByNamePass(String name, String password);
        User getUserByName(String name);
        User getUserByEmail(String email);
}
