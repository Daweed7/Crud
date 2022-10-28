package com.dav.kat.crud.server.dao;

import com.dav.kat.crud.server.model.User;
import java.util.List;

public interface UserDao {

    List<User> listUsers();

    void create(User user);

    void delete(Long id);

    void update(User user, String firstName, String lastName);

    User showUser(Long id);
}
