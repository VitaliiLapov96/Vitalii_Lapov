package com.epam.spring.homework3.payments.repository;


import com.epam.spring.homework3.payments.model.User;

import java.util.List;

public interface UserRepository {

    User createUser(User user);
    User getUser(int userId);
    User updateUser(int userId, User updatedUser);
    void deleteUser(int userId);
    List<User> listUsers();

}
