package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;
    @Override
    public List<Role> roleList() {
        return roleDAO.roleList();
    }

    @Override
    public Role getById(Integer id) {
        return roleDAO.getById(id);
    }

    @Override
    public Role getByName(String name) {
        return roleDAO.getByName(name);
    }
}
