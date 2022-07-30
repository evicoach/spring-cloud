package io.confluent.developer.spring.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(@Autowired StudentRepository studentRepository) {
        return args -> {
            Student mariam = new Student(1L,
                    "Mariam", LocalDate.of(2002, Month.APRIL, 2),
                    "mariam@email.com");
            Student alex = new Student(
                    "Alex",LocalDate.of(2004, Month.APRIL, 2),
                    "alex@email.com");

            studentRepository.saveAll(List.of(mariam, alex));
        };
    }
}
