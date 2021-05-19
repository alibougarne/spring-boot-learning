package com.real360.demo.features.users.userRepository;

import com.real360.demo.features.users.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> getAllUsers(Long skip, Long take);

}
