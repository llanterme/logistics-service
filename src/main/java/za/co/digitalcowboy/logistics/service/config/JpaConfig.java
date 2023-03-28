package za.co.digitalcowboy.logistics.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories("za.co.digitalcowboy.logistics.service.repository")
public class JpaConfig {

}
