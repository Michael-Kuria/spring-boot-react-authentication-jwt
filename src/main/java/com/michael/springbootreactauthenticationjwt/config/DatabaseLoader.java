package com.michael.springbootreactauthenticationjwt.config;

import com.michael.springbootreactauthenticationjwt.model.ApplicationUser;
import com.michael.springbootreactauthenticationjwt.model.Student;
import com.michael.springbootreactauthenticationjwt.model.UserRole;
import com.michael.springbootreactauthenticationjwt.service.ApplicationUserService;
import com.michael.springbootreactauthenticationjwt.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader {

    private final ApplicationUserService applicationUserService;
    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;

    public DatabaseLoader(ApplicationUserService applicationUserService, StudentService studentService, PasswordEncoder passwordEncoder) {
        this.applicationUserService = applicationUserService;
        this.studentService = studentService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner commandLineRunner(){

        return args -> {
            ApplicationUser user = new ApplicationUser("mchege78@gmail.com",passwordEncoder.encode("password"), "Michael", "Kuria", UserRole.ADMIN,true, true, true, true);

            Student std1 = new Student("Michael", "Kuria", 3);
            Student std2 = new Student("Michael", "Kuria", 3);
            Student std3 = new Student("Michael", "Kuria", 3);
            Student std4 = new Student("Michael", "Kuria", 3);
            Student std5 = new Student("Michael", "Kuria", 3);
            Student std6 = new Student("Michael", "Kuria", 3);

            if(applicationUserService.applicationUserCount() < 1)
                applicationUserService.addApplicationUser(user);

            if(studentService.studentCount() < 1){
                studentService.addStudent(std1);
                studentService.addStudent(std2);
                studentService.addStudent(std3);
                studentService.addStudent(std4);
                studentService.addStudent(std5);
                studentService.addStudent(std6);
            }

        };



    }

}
