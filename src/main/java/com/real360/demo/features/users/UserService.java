package com.real360.demo.features.users;

import com.real360.demo.features.users.userRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 1.0.0
     * @param skip the pagination skip parameter
     * @param take the pagination take parameter
     * @return a list of User
     * @return the list
     * @throws Exception users not found
     */
    public List<User> fetchAllUsers(Long skip, Long take) throws Exception {
        List<User> users;
        users = userRepository.fetchAllUsers(skip, take);
        return users;
    }

    public List<User> getUsers(){
        List<User> users;
        users = userRepository.findAll();
        System.out.println(users.size());
        return users;
    }
}
