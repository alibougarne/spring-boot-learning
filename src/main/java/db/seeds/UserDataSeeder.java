package db.seeds;


import com.real360.demo.features.users.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserDataSeeder implements CommandLineRunner {

    @Autowired
    private ServletWebServerApplicationContext server;

    @Autowired
    UserRepository userRepository;

    @Value("classpath:data/users.json")
    Resource resourceFile;

    @Value("${server.port}")
    private int port;

    @Override
    public void run(String... args) throws Exception {
        // seedData();
        // System.out.println(resourceFile);
    }


    public void seedData() {
        // System.out.println(resourceFile);
        System.out.println("🎯 running on port:");
        Long a = userRepository.count();
        System.out.println(a);
        System.out.println("https://localhost:" + port);

    }
}
