package com.real360.demo.features.users;

import com.real360.demo.features.roles.Role;
import com.real360.demo.features.roles.roleRepository.RoleRepository;
import com.real360.demo.features.users.dtos.UserDTO;
import com.real360.demo.features.users.userRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * The type User service.
 */
@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Fetch all users list.
     *
     * @param skip the pagination skip parameter
     * @param take the pagination take parameter
     * @return a list of User
     * @throws Exception users not found
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 0.0.1
     */
    public List<User> fetchAllUsers(Long skip, Long take) throws Exception {
        List<User> users;
        users = userRepository.fetchAllUsers(skip, take);
        return users;
    }

    /**
     * Get users list.
     *
     * @return the list
     */
    public List<User> getUsers() {
        List<User> users;
        users = userRepository.findAll();
        System.out.println(users.size());
        return users;
    }


    /**
     * Save user.
     *
     * @param userDTO the user dto
     * @return the user
     * @throws Exception Roles not found
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 0.0.1
     */
    public User saveUser(UserDTO userDTO) throws Exception {
        System.out.println(userDTO);
        User user = new User();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Set<Role> roles = new HashSet<>();
        for (String role : userDTO.getRoles()) {
            Optional<Role> roleFound = roleRepository.findRoleByName(role);
            if (roleFound.isPresent()) {
                roles.add(roleFound.get());
            }
        }
        System.out.println("size role ===> "+roleRepository.findAll().size());
        if (roles.isEmpty())
            throw new Exception("Roles not found");
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setActive(userDTO.isActive());
        user.setProfilePicture(userDTO.getProfilePicture());
        user.setRoles(roles);

        return userRepository.save(user);
    }


}
