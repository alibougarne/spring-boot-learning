package db.seeds;


import com.github.javafaker.Faker;
import com.real360.demo.features.companies.Company;
import com.real360.demo.features.companies.CompanyRepository;
import com.real360.demo.features.users.User;
import com.real360.demo.features.users.userRepository.UserRepository;
import com.sun.istack.NotNull;

import java.util.HashSet;
import java.util.Set;

public class CompaniesDataSeeds {
    public static void seedData(@NotNull CompanyRepository companyRepository, @NotNull UserRepository userRepository) {
       User admin = userRepository.findAll().get(0);
        System.out.println("🧩 Seeding Companies data...");
        Set<Company> companies = new HashSet<>();
        Long countCompanies = companyRepository.count();
        if(countCompanies == 0){
            Faker faker = new Faker();
            for(int i = 0; i <= 10; i++){
                Company company = new Company();
                company.setAdmin(admin);
                System.out.println(company.getAdmin().getId());
                company.setAddress(faker.address().fullAddress());
                company.setCity(faker.address().city());
                company.setLogoUrl(faker.internet().image());
                company.setName(faker.company().name());
                company.setState(faker.address().state());
                company.setNumEmployees(faker.random().nextInt(100, 50000));
                company.setPhone(faker.phoneNumber().phoneNumber());
                companies.add(company);
            }
            companyRepository.saveAll(companies);
        }
    }
}
