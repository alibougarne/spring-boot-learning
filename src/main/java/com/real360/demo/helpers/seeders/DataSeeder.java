package com.real360.demo.helpers.seeders;

import com.real360.demo.features.companies.CompanyRepository;
import com.real360.demo.features.projects.ProjectRepository;
import com.real360.demo.features.roles.roleRepository.RoleRepository;
import com.real360.demo.features.users.userRepository.UserRepository;
import db.seeds.CompaniesDataSeeds;
import db.seeds.ProjectsDataSeeds;
import db.seeds.UsersDataSeeds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;


/**
 * The type Data seeder.
 */
@Configuration
public class DataSeeder {

    /**
     * The User repository.
     */
    @Autowired
    UserRepository userRepository;
    /**
     * The Role repository.
     */
    @Autowired
    RoleRepository roleRepository;
    /**
     * The Company repository.
     */
    @Autowired
    CompanyRepository companyRepository;
    /**
     * The Project repository.
     */
    @Autowired
    ProjectRepository projectRepository;

    /**
     * Command line runner command line runner.
     * used to seed some data on app start-up
     * @return the command line runner
     */
    @Bean
    @Transactional
    CommandLineRunner commandLineRunner() {
        try {
            return args -> {
                System.out.println("🚀 Seeding data Begin...");
                UsersDataSeeds.seedData(userRepository, roleRepository);
                CompaniesDataSeeds.seedData(companyRepository, userRepository);
                ProjectsDataSeeds.seedData(projectRepository, companyRepository);
                System.out.println("🎯 Seeding data completed...");
            };
        } catch (Exception e) {
            System.out.println(e.getCause());
            return null;
        }
    }
}
