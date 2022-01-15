package com.epam.spring.homework4.payments.repository;


import com.epam.spring.homework4.payments.model.User;

import java.util.List;

public interface UserRepository {

    User createUser(User user);
    User getUser(Long userId);
    User updateUser(Long userId, User updatedUser);
    void deleteUser(Long userId);
    List<User> listUsers();

}
