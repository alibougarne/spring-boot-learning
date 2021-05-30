package db.seeds;

import com.github.javafaker.Faker;
import com.real360.demo.features.companies.Company;
import com.real360.demo.features.companies.CompanyRepository;
import com.real360.demo.features.projects.Project;
import com.real360.demo.features.projects.ProjectRepository;
import com.sun.istack.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProjectsDataSeeds {

    public static void seedData(@NotNull ProjectRepository projectRepository, @NotNull CompanyRepository companyRepository) {

        List<Company> companies = companyRepository.findAll();
        System.out.println(companies.size());
        System.out.println("🧩 Seeding Projects data...");
        Set<Project> projects = new HashSet<>();
        Long countProjects = projectRepository.count();
        if (countProjects == 0) {
            Faker faker = new Faker();
            for (int i = 0; i <= 10; i++) {
                Project project = new Project();
                project.setName(faker.funnyName().name());
                project.setAddress(faker.address().fullAddress());
                project.setAreaTotal((float) faker.number().numberBetween(1, 500));
                project.setCity(faker.address().city());
                project.setCountry(faker.address().countryCode());
                project.setCurrency(faker.currency().code());
                Company company = companies.get(companies.size()%3);
                project.setCompany(company);
                projects.add(project);
            }
            projectRepository.saveAll(projects);
        }

    }
}
