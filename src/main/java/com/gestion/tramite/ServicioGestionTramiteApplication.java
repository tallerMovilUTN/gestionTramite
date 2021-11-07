package com.gestion.tramite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**

 ///METODO CONVENSIONAL PARA GENERAR UN JAR
 @SpringBootApplication
public class ServicioFabricaApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServicioFabricaApplication.class, args);
	}

}
**/




////METODO PARA GENERAR UN WAR
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ServicioGestionTramiteApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(ServicioGestionTramiteApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServicioGestionTramiteApplication.class);
	}

}