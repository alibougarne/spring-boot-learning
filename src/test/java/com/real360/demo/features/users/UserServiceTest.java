package com.real360.demo.features.users;

import com.real360.demo.features.users.userRepository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
//    private AutoCloseable autoCloseable;
    private UserService underTest;

    @BeforeEach
    void setUp() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UserService(userRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
//        autoCloseable.close();
    }

    @Test
    void fetchAllUsers()  throws Exception {
        // then
        underTest.fetchAllUsers(null, null);
        // verify
        verify(userRepository).fetchAllUsers(null, null);
    }

    @Test
    @Disabled
    void getUsers() {
        underTest.getUsers();
        verify(userRepository).findAll();
    }
}