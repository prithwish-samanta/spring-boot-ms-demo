package dev.ms.loans;

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
				title = "Loans microservice REST API Documentation",
				description = "DemoBank Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Prithwish Samanta",
						email = "wprith@gmail.com",
						url = "https://github.com/prithwish-samanta"
				),
				license = @License(
						name = "Demo License",
						url = "https://www.demo.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "DemoBank Loans microservice REST API external Documentation",
				url = "https://www.demo.com/swagger-ui.html"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
