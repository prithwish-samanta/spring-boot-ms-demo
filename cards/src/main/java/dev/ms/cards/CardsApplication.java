package dev.ms.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "DemoBank Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Prithwish Samanta",
						email = "wprith@gmail.com",
						url = "https://github.com/prithwish-samanta"
				),
				license = @License(
						name = "Demo licence",
						url = "https://www.demo.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "DemoBank Cards microservice REST API external Documentation",
				url = "https://www.demo.com/swagger-ui.html"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
