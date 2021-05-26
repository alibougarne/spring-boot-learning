package com.real360.demo.features.users;

import com.real360.demo.features.users.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;



@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/")
    public ResponseEntity<?> getUsers(@RequestParam(required = false) String firstName) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        List<User> users = userRepository.fetchAllUsers(null, null);
        return new ResponseEntity<>(choice, HttpStatus.OK);

    }
}
