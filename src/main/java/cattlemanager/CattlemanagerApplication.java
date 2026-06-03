package cattlemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Punto de entrada de la aplicación CattleManager
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class CattlemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CattlemanagerApplication.class, args);
	}

}
