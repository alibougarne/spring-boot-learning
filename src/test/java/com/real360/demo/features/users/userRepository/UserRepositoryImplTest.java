package com.real360.demo.features.users.userRepository;

import com.real360.demo.features.roles.Role;
import com.real360.demo.features.roles.roleRepository.RoleRepository;
import com.real360.demo.features.users.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
@DataJpaTest
class UserRepositoryImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setUp() {
    }


    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void itShouldFetchAllUsers() throws Exception {
        // given
        Role superAdmin = new Role();
//        superAdmin = roleRepository.findAll().get(0);
        Set<Role> roles = new HashSet<>();
//        if(superAdmin == null){
            superAdmin.setName("superAdmin");
            roleRepository.save(superAdmin);
//        }

        roles.add(superAdmin);
        System.out.println(superAdmin.getId());

        User user = new User();
        user.setFirstName("Ali");
        user.setLastName("Bougarne");
        user.setProfilePicture("https://img.com/pad920img.png");
        user.setActive(true);
        user.setEmail("ali.bougarne@gmail.com");
        user.setPassword(bCryptPasswordEncoder.encode("admin"));
        user.setRoles(roles);
        userRepository.save(user);

        // when
        List<User> users = userRepository.fetchAllUsers(null, null);

        // then
        Assertions.assertThat(users).containsAll(userRepository.findAll());

    }
}