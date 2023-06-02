package ru.kata.spring.boot_security.demo.DAO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.Model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class UserDAOImpl  implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUserByNamePass(String name, String password) {
        return (User) entityManager.createQuery("from User where name =: name and password =: password")
                .setParameter("name", name)
                .setParameter("password", password)
                .getSingleResult();
    }

    @Override
    public User getUserByName(String name) {
        return  (User) entityManager.createQuery("from User where name =: name")
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        return  (User) entityManager.createQuery("from User where email =: email")
                .setParameter("email", email)
                .getSingleResult();
    }
}
