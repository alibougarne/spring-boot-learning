package com.real360.demo.features.users.userRepository;

import com.real360.demo.features.users.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {


    @Override
    public List<User> fetchAllUsers(Long take, Long skip) {
        List<User> users = new ArrayList<>();
        return users;
    }
}
