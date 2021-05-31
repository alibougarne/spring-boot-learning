package com.real360.demo.features.users;

import com.real360.demo.features.roles.Role;
import com.real360.demo.features.roles.roleRepository.RoleRepository;
import com.real360.demo.features.users.dtos.UserDTO;
import com.real360.demo.features.users.userRepository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;
    //    private AutoCloseable autoCloseable;
    private UserService underTest;

    @BeforeEach
    void setUp() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UserService(userRepository, roleRepository);
    }

    @AfterEach
//    @Disabled
    void tearDown() {
        roleRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void fetchAllUsers() throws Exception {
        // then
        underTest.fetchAllUsers(null, null);
        // verify
        verify(userRepository).fetchAllUsers(null, null);
    }

    @Test
    @Disabled
    void getUsers() {
        // then
        underTest.getUsers();
        // verify
        verify(userRepository).findAll();
    }

    @Test
    void saveUser() throws Exception {
        // then
        String[] roles = {"superAdmin", "projectAdmin", "companyAdmin", "basicAdmin"};
        for(String roleName:roles){
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
        UserDTO userDTO = new UserDTO(
                "Mourad",
                "sekkal",
                "mourad.sekkal@gmail.com",
                "admin",
                "www.google.com/photo/123242.png",
                true,
                roles
        );
        underTest.saveUser(userDTO);
        // verify
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser.getFirstName()).isEqualTo(userDTO.getFirstName());


    }
}