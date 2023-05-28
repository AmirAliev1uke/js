package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.Model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
//    void add(User user);
//    User getUserById(Long id);
//    List<User> listUsers();
//    void updateUser(User user);
//    void deleteUser(Long id);

    void add(ArrayList<Integer> roles, String name, String lastName, String password, String email);
    User getUserById(Long id);
    List<User> listUsers();
    void updateUser(ArrayList<Integer> roles, String name, String lastName, String password, String email);
    void deleteUser(Long id);
    User getUserByNamePass(String name, String password);
    User getUserByName(String name);

}
