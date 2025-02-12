package org.example.bookshop;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.dao.RoleDao;
import org.example.bookshop.dao.UserDao;
import org.example.bookshop.security.Role;
import org.example.bookshop.security.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@RequiredArgsConstructor
public class BookShopApplication {
    private final RoleDao roleDao;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    @Bean @Transactional @Profile("dev")
    public ApplicationRunner runner(){
        return  r ->{
            Role userRole = new Role();
            userRole.setRoleName("ROLE_USER");
            Role adminRole = new Role();
            adminRole.setRoleName("ROLE_ADMIN");

            User user=new User();
            user.setUsername("mary");
            user.setEmail("mary@gmail.com");
            user.setPhoneNumber("123456789");
            user.setPassword(passwordEncoder.encode("12345"));
            user.addRole(roleDao.save(userRole));
            userDao.save(user);

            User admin=new User();
            admin.setUsername("john");
            admin.setEmail("john@gmail.com");
            admin.setPhoneNumber("123456789");
            admin.setPassword(passwordEncoder.encode("12345"));
            admin.addRole(roleDao.save(adminRole));
            userDao.save(admin);
        } ;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

}
