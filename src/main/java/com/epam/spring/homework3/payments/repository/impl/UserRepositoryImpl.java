package com.epam.spring.homework3.payments.repository.impl;

import com.epam.spring.homework3.payments.model.User;
import com.epam.spring.homework3.payments.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final List<User> userList = new ArrayList<>();
    private static int USER_ID_COUNT = 0;

    @Override
    public User createUser(User user) {
        user.setUserId(++USER_ID_COUNT);
        userList.add(user);
        return user;
    }

    @Override
    public User getUser(int userId) {
        return userList.stream()
                .filter(user -> user.getUserId() == userId)
                .findAny()
                .orElseThrow(() -> new RuntimeException("User is not found"));
    }

    @Override
    public User updateUser(int userId, User updatedUser) {
        boolean userIsDeleted = userList.removeIf(user -> user.getUserId() == userId);
        if (!userIsDeleted)
            throw new RuntimeException("User is not updated");

        updatedUser.setUserId(++USER_ID_COUNT);
        userList.add(updatedUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(int userId) {
        userList.removeIf(user -> user.getUserId() == userId);
    }

    @Override
    public List<User> listUsers() {
        return new ArrayList<>(userList);
    }

}
