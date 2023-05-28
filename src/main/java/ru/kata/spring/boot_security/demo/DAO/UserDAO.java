package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.Model.User;

import java.util.List;

public interface UserDAO {
//        void add(User user);
//        User getUserById(Long id);
//        List<User> listUsers();
//        void updateUser(User user);
//        void deleteUser(Long id);

        void add(User user);
        User getUserById(Long id);
        List<User> listUsers();
        void updateUser(User user);
        void deleteUser(Long id);
        User getUserByNamePass(String name, String password);
        User getUserByName(String name);
}
