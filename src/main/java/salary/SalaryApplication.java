package salary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import salary.domain.Salary;
import salary.domain.SalaryRepository;
import salary.domain.User;
import salary.domain.UserRepository;

@SpringBootApplication
public class SalaryApplication {
	private static final Logger log = LoggerFactory.getLogger(SalaryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SalaryApplication.class, args);
	}

@Bean
public CommandLineRunner salaryDb(SalaryRepository srepository, UserRepository urepository){
	return (args) -> {
		log.info("save salaries");
		
		
		srepository.save(new Salary("Ruman", "ruman123", "a12345", "turku", 123456, 890));
		srepository.save(new Salary("ryabov", "ryabov123", "a567845", "helsinki", 12365, 900));
		
		// Create users: admin/admin user/user
		User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
		User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
		urepository.save(user1);
		urepository.save(user2);
		
		log.info("fetch salaries");
		for (Salary salary: srepository.findAll()){
			log.info(salary.toString());
		}
		};
	}
	}
