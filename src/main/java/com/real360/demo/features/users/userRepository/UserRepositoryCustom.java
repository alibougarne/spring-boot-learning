package com.real360.demo.features.users.userRepository;

import com.real360.demo.features.users.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> fetchAllUsers(Long skip, Long take) throws Exception;

}
