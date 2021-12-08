package edu.escuelaing.arsw.boardUIApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "edu.escuelaing.arsw.boardUI" })
@EntityScan(basePackages = { "edu.escuelaing.arsw.boardUI" })
@EnableJpaRepositories(basePackages = { "edu.escuelaing.arsw.boardUI" })
public class BoardUIApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardUIApplication.class, args);
	}
}