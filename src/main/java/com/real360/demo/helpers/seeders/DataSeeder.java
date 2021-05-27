package com.real360.demo.helpers.seeders;

import com.real360.demo.features.companies.CompanyRepository;
import com.real360.demo.features.roles.roleRepository.RoleRepository;
import com.real360.demo.features.users.userRepository.UserRepository;
import db.seeds.CompaniesDataSeeds;
import db.seeds.UsersDataSeeds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        try {
            return args -> {
                System.out.println("🚀 Seeding data Begin...");
                UsersDataSeeds.seedData(userRepository, roleRepository);
                CompaniesDataSeeds.seedData(companyRepository, userRepository);
                System.out.println("🎯 Seeding data completed...");
            };
        } catch (Exception e) {
            System.out.println(e.getCause());
            return null;
        }
    }
}
