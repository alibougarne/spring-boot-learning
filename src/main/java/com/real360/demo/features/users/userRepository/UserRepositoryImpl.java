package com.real360.demo.features.users.userRepository;

import com.real360.demo.features.users.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {


    @Override
    public List<User> fetchAllUsers(Long take, Long skip) throws Exception {
        List<User> users = new ArrayList<>();
        if (!users.isEmpty())
            throw new Exception("Unauthorized user");
        return users;
    }
}
