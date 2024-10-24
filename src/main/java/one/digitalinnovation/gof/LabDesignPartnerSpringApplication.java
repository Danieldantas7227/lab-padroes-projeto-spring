package one.digitalinnovation.gof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"model", "controller", "service", "service.impl", "config"})
@EnableJpaRepositories(basePackages = {"model"})
@EntityScan(basePackages = {"model"})
@EnableFeignClients(basePackages = "service")
@SpringBootApplication
public class LabDesignPartnerSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabDesignPartnerSpringApplication.class, args);
	}
}
