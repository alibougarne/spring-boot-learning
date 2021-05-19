package com.real360.demo.features.users.userRepository;

import com.real360.demo.features.users.User;

import java.util.ArrayList;
import java.util.List;

public class IUserRepository implements UserRepositoryCustom {

    @Override
    public List<User> getAllUsers(Long take, Long skip) {
        List<User> users = new ArrayList<>();
        return users;
    }
}
