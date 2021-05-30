package db.seeds;


import com.github.javafaker.Faker;
import com.real360.demo.features.roles.Role;
import com.real360.demo.features.roles.roleRepository.RoleRepository;
import com.real360.demo.features.users.User;
import com.real360.demo.features.users.userRepository.UserRepository;
import com.sun.istack.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Array;
import java.util.*;

public class UsersDataSeeds {
    public static void seedData(@NotNull UserRepository userRepository, @NotNull RoleRepository roleRepository) {
        System.out.println("🧩 Seeding Roles data...");
        // create and save a role

        Set<Role> roles = new HashSet<>();
        List<String> names = Arrays.asList("superAdmin","companyAdmin", "projectAdmin", "basicAdmin");
        if (roleRepository.count() == 0) {
            for (int i = 0; i < names.size(); i++) {
                Role role = new Role();
                role.setName(names.get(i));
                role.setCreatedAt(new Date());
                role.setUpdatedAt(role.getCreatedAt());
                roleRepository.save(role);
                roles.add(role);
            }
        } else {
            roles.addAll(roleRepository.findAll());
        }
        // create and save users
        System.out.println("🧩 Seeding Users data...");
        Set<User> users = new HashSet<>();
        Long countUsers = userRepository.count();
        if (countUsers == 0) {
            Faker faker = new Faker();
            for (int i = 0; i <= 10; i++) {
                User user = new User();
                user.setActive(true);
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                user.setFirstName(faker.name().firstName());
                user.setLastName(faker.name().lastName());
                user.setEmail(faker.internet().safeEmailAddress(user.getFirstName().concat(".").concat(user.getLastName()).toLowerCase()));
                user.setPassword(bCryptPasswordEncoder.encode("admin"));
                user.setProfilePicture("");
                user.setRoles(getRandomElement(roles,faker.number().numberBetween(1, roles.size()) ));
                users.add(user);
            }
            userRepository.saveAll(users);
        }
    }

    static Set<Role>
    getRandomElement(Set<Role> list, int totalItems)
    {
        Random rand = new Random();
        List<Role> roles = new ArrayList<>();
        roles.addAll(list);
        // create a temporary list for storing
        // selected element
        Set<Role> newList = new HashSet<>();
        for (int i = 0; i < totalItems; i++) {

            // take a raundom index between 0 to size
            // of given List
            int randomIndex = rand.nextInt(list.size());

            // add element in temporary list
            newList.add(roles.get(i));

            // Remove selected element from orginal list
            list.remove(randomIndex);
        }
        return newList;
    }
}
