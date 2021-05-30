package com.real360.demo.features.users;

import com.real360.demo.features.users.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> fetchAllUsers(Long skip, Long take) throws Exception {
        List<User> users;
        users = userRepository.fetchAllUsers(skip, take);
        System.out.println(users.size());
        return users;
    }

    public List<User> getUsers(){
        List<User> users;
        users = userRepository.findAll();
        System.out.println(users.size());
        return users;
    }
}
