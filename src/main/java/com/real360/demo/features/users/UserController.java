package com.real360.demo.features.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
 @GetMapping(path = "/")
    public ResponseEntity<?> getUsers(@RequestParam(required = false) String firstName) {
     try {
         return  new ResponseEntity<>(firstName, HttpStatus.OK);
     }catch (Exception e){
         return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
     }
 }
}
