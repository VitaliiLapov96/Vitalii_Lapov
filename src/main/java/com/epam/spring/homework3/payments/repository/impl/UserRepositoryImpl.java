package com.epam.spring.homework3.payments.repository.impl;

import com.epam.spring.homework3.payments.model.User;
import com.epam.spring.homework3.payments.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final List<User> userList = new ArrayList<>();
    private static Long USER_ID_COUNT = 0L;

    @Override
    public User createUser(User user) {
        user.setUserId(++USER_ID_COUNT);
        user.setDate(LocalDate.now());
        userList.add(user);
        return user;
    }

    @Override
    public User getUser(Long userId) {
        return userList.stream()
                .filter(user -> Objects.equals(user.getUserId(), userId))
                .findAny()
                .orElseThrow(() -> new RuntimeException("User is not found"));
    }

    @Override
    public User updateUser(Long userId, User updatedUser) {
        User user = userList.stream()
                .filter(nextUser -> Objects.equals(nextUser.getUserId(), userId))
                .findAny()
                .orElseThrow(() -> new RuntimeException("User is not updated"));

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setRepeatPassword(updatedUser.getRepeatPassword());
        user.setDate(updatedUser.getDate());
        user.setIsAdmin(updatedUser.getIsAdmin());
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        userList.removeIf(user -> Objects.equals(user.getUserId(), userId));
    }

    @Override
    public List<User> listUsers() {
        return new ArrayList<>(userList);
    }

}
