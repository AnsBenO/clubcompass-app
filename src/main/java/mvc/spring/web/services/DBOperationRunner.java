// package mvc.spring.web.services;

// import java.util.Collections;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import mvc.spring.web.models.Role;
// import mvc.spring.web.models.UserEntity;
// import mvc.spring.web.models.Role.RoleName;
// import mvc.spring.web.repositories.RoleRepository;
// import mvc.spring.web.repositories.UserRepository;

// @Component
// public class DBOperationRunner implements CommandLineRunner {

// private UserRepository userRepository;

// private RoleRepository roleRepository;

// public DBOperationRunner(UserRepository userRepository, RoleRepository
// roleRepository) {
// this.userRepository = userRepository;
// this.roleRepository = roleRepository;
// }

// @Override
// public void run(String... args) throws Exception {
// // Fetch the ADMIN role from the database or get a reference to it
// Role adminRole = roleRepository.findByName(RoleName.ADMIN);
// if (adminRole == null) {
// // If the ADMIN role doesn't exist, create it
// adminRole = Role.builder().name(RoleName.ADMIN).build();
// roleRepository.save(adminRole);
// }

// // Create the user and associate it with the ADMIN role
// UserEntity adminUser = UserEntity.builder()
// .username("Admin")
// .email("admin@example.com")
// .password("admin")
// .roles(Collections.singletonList(adminRole))
// .build();
// userRepository.save(adminUser);
// }

// }
