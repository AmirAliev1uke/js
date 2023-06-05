package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void add(ArrayList<Integer> roles, String name, String lastName, String password, String email, Integer age) {
        Set<Role> roleSet = new HashSet<>();
        for (Integer roleId : roles) {
            roleSet.add(new Role(roleId));
        }
        User user = new User(name, password, email, lastName, age, roleSet);
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.add(user);
    }

    @Transactional
    @Override
    public User getUserById(Integer id) {
        return userDAO.getUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDAO.listUsers();
    }

    @Transactional
    @Override
    public void updateUser(ArrayList<Integer> roles, String name, String lastName, String password, String email, Integer age, Integer id) {
        Set<Role> roleSet = new HashSet<>();
        for (Integer roleId : roles) {
            roleSet.add(new Role(roleId));
        }
        User user = userDAO.getUserById(id);
        user.setRoles(roleSet);
        user.setEmail(email);
        user.setName(name);
        user.setLastName(lastName);
        user.setPassword(passwordEncoder.encode(password));
        user.setAge(age);

        Set<Role> roleSet1 = new HashSet<>();
        for (Role role : user.getRoles()) {
            roleSet1.add(roleDAO.getById(role.getId()));
        }
        user.setRoles(roleSet1);
        userDAO.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Integer id) {
        userDAO.deleteUser(id);
    }

    @Override
    public User getUserByNamePass(String name, String password) {
        return userDAO.getUserByNamePass(name, password);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
}
