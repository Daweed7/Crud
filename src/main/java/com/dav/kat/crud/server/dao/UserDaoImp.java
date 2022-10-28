package com.dav.kat.crud.server.dao;

import com.dav.kat.crud.server.model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("select c from User c").getResultList();
    }

    @Override
    public void create(User user) {

        if (user.getId() != null) {
            user = entityManager.merge(user);
        }
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {

        entityManager.createQuery("DELETE FROM User e WHERE e.id = :id")
                     .setParameter("id", id)
                     .executeUpdate();
    }

    @Override
    public void update(User user, String firstName, String lastName) {

        user.setFirstName(firstName);
        user.setLastName(lastName);
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public User showUser(Long id) {

        return (User) entityManager.createQuery("select e FROM User e WHERE e.id = :id").setParameter("id", id)
                                   .getSingleResult();
    }
}
