package com.dav.kat.crud.server.service;

import com.dav.kat.crud.server.dao.UserDao;
import com.dav.kat.crud.server.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public List<User> listUsers() {

        return userDao.listUsers();
    }

    @Override
    public User showUser(Long id) {
        return userDao.showUser(id);
    }

    @Override
    @Transactional
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public void update(User user, String firstName, String lastName) {
        userDao.update(user, firstName, lastName);
    }
}
