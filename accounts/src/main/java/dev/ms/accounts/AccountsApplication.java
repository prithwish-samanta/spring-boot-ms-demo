package dev.ms.accounts;

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
				title = "Accounts microservice REST API documentation", 
				description = "DemoBank Accounts microservice REST API documentation",
				version = "v1", 
				contact = @Contact(
						name = "Prithwish Samanta",
						email = "wprith@gmail.com", 
						url = "https://github.com/prithwish-samanta"
				),
				license = @License(
						name = "Dummy licence",
						url = "http://dummy.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "DemoBank Accounts microservice REST API external documentation",
				url = "http://dummy.com"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
