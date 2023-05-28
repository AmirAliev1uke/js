package ru.kata.spring.boot_security.demo.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.RoleDAO;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.Model.Role;
import ru.kata.spring.boot_security.demo.Model.User;

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
    public void add(ArrayList<Integer> roles, String name, String lastName, String password, String email) {
        Set<Role> roleSet = new HashSet<>();
        for (Integer roleId : roles) {
            roleSet.add(new Role(roleId));
        }
        User user = new User(name, password, email, lastName, roleSet);
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.add(user);
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        return userDAO.listUsers();
    }

    @Transactional
    @Override
    public void updateUser(ArrayList<Integer> roles, String name, String lastName, String password, String email) {
        Set<Role> roleSet = new HashSet<>();
        for (Integer roleId : roles) {
            roleSet.add(new Role(roleId));
        }
        User user = userDAO.getUserByName(name);
        user.setRoles(roleSet);
        user.setEmail(email);
        user.setName(name);
        user.setLastName(lastName);
        user.setPassword(password);

        Set<Role> roleSet1 = new HashSet<>();
        for (Role role : user.getRoles()) {
            roleSet1.add(roleDAO.getById(role.getId()));
        }
        user.setRoles(roleSet1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public User getUserByNamePass(String name, String password) {
        return userDAO.getUserByNamePass(name,password);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }
}
