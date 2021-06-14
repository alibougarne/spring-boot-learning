package com.real360.demo.features.users;

import com.real360.demo.features.users.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/users")
public class UserController {

//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private UserService userService;

//    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam(required = false) Long skip, @RequestParam(required = false) Long take) throws Exception {
        System.out.println(skip + " ," + take);
        List<User> users = userService.fetchAllUsers(skip, take);
//        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
