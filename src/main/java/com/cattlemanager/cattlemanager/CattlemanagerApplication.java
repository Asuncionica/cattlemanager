package com.cattlemanager.cattlemanager;

import org.springframework.boot.SpringApplication;//Permite arrancar la aplicación de Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication;//Es una anotación que configura automáticamente muchas cosas.

@SpringBootApplication
public class CattlemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CattlemanagerApplication.class, args);//Esta línea arranca toda la aplicación Spring Boot.
	}

}
/*@SpringBootApplication
Esta anotación hace tres cosas importantes automáticamente:

Configura Spring Boot automáticamente (auto-configuración).

Busca componentes de Spring en el proyecto.

Activa la configuración de Spring.

En resumen:

👉 Le dice a Spring:
"Aquí empieza mi aplicación, configura todo lo necesario."*/
