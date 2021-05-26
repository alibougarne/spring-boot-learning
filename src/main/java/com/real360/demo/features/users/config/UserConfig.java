package com.real360.demo.features.users.config;

import com.github.javafaker.Faker;
import com.real360.demo.features.roles.Role;
import com.real360.demo.features.roles.roleRepository.RoleRepository;
import com.real360.demo.features.users.User;
import com.real360.demo.features.users.userRepository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, RoleRepository roleRepository) {
        try{
            return args -> {
                System.out.println("🧩 Seeding data...");
                // create and save a role
                Role role = new Role();
                role.setName("superAdmin");
                Set<Role> roles = new HashSet<>();
                if(roleRepository.count() == 0) {
                    role.setName("superUser");
                    role.setCreatedAt(new Date());
                    role.setUpdatedAt(role.getCreatedAt());
                    roleRepository.save(role);
                }else{
                    role = roleRepository.findAll().get(0);
                }
                System.out.println("=======Role ID=======");
                System.out.println(role.getId());
                roles.add(role);
                System.out.println(roles.size());
                // create and save users
                Set<User> users = new HashSet<>();
                Long countUsers = userRepository.count();
                if(countUsers == 0){
                    Faker faker = new Faker();
                    for (int i = 0; i <=10; i++){
                        User user = new User();
                        user.setActive(true);
                        user.setCreatedAt(new Date());
                        user.setUpdatedAt(user.getCreatedAt());
                        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                        user.setFirstName(faker.name().firstName());
                        user.setLastName(faker.name().lastName());
                        user.setEmail(faker.internet().safeEmailAddress(user.getFirstName().concat(".").concat(user.getLastName()).toLowerCase()));
                        user.setPassword(bCryptPasswordEncoder.encode("admin"));
                        user.setProfilePicture("");
                        user.setRoles(roles);
                        users.add(user);
                    }
                    System.out.println("===== Users count======");
                    System.out.println(users.size());
                    userRepository.saveAll(users);
                }
                System.out.println("🎯 Seeding data completed...");

            };
        }
        catch (Exception e){
            System.out.println(e.getCause());
        }
        return args -> {};
    }
}
