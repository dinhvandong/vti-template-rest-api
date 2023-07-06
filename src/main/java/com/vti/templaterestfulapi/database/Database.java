package com.vti.templaterestfulapi.database;


import com.vti.templaterestfulapi.models.ERole;
import com.vti.templaterestfulapi.models.Role;
import com.vti.templaterestfulapi.models.User;
import com.vti.templaterestfulapi.repositories.RoleRepository;
import com.vti.templaterestfulapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class Database {
//https://www.devglan.com/spring-boot/spring-boot-mongodb-configuration
    @Autowired
    PasswordEncoder encoder;

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository,
                                   UserRepository userRepository) {
        return new CommandLineRunner()
        {
            @Override
            public void run(String... args) throws Exception {
              //  new ConectorSQL().mostrarDatos();

                Role role1 = new Role();
                role1.setId(1);
                role1.setName(ERole.ROLE_ADMIN);

                Role role2 = new Role();
                role2.setId(2);
                role2.setName(ERole.ROLE_MODERATOR);

                Role role3 = new Role();
                role3.setId(3);
                role3.setName(ERole.ROLE_AH);

                Role role4 = new Role();
                role4.setId(4);
                role4.setName(ERole.ROLE_TEACHER);
                if(roleRepository.findAll().size()==0){
                    roleRepository.save(role1);
                    roleRepository.save(role2);
                    roleRepository.save(role3);
                    roleRepository.save(role4);
                }

            // Generate user super admin
                if(userRepository.findAll().size()==0){
                    User admin = new User();
                    admin.setId(1l);
                    admin.setUserName("super_admin@fpt.edu.vn");
                    admin.setEmail("super_admin@fpt.edu.vn");
                    admin.setFullName("Super");
                    admin.setCreatedTime(new Date());
                    admin.setParentID(0);
                    admin.setDepartmentID(0);
                    admin.setNote("This is super account");
                    Set<Role> roles = new HashSet<>();
                    admin.setPassWord(encoder.encode("Azd1232421@#"));
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                    admin.setRoles(roles);
                    userRepository.save(admin);
                }
        }};
    }
}