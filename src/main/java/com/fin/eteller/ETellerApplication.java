package com.fin.eteller;

import com.fin.eteller.entity.Role;
import com.fin.eteller.entity.Transaction;
import com.fin.eteller.entity.User;
import com.fin.eteller.service.TransactionService;
import com.fin.eteller.service.UserService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ETellerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ETellerApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, TransactionService transactionService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "John Doe", "johnd@gmail.com", "1234",new ArrayList<>()));
			userService.saveUser(new User(null, "Jane Doe", "janed@gmail.com", "1234",new ArrayList<>()));

			userService.addRoleToUser("johnd@gmail.com","ROLE_USER");
			userService.addRoleToUser("janed@gmail.com","ROLE_MANAGER");








		};
	}


}
