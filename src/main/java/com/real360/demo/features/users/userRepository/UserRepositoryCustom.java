package com.real360.demo.features.users.userRepository;

import com.real360.demo.features.users.User;

import java.util.List;

/**
 * The interface User repository custom.
 */
public interface UserRepositoryCustom {
    /**
     * Fetch all users list.
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 1.0.0
     * @param skip the pagination skip parameter
     * @param take the pagination take parameter
     * @return a list of User
     * @return the list
     * @throws Exception the exception
     */
    List<User> fetchAllUsers(Long skip, Long take) throws Exception;
}
