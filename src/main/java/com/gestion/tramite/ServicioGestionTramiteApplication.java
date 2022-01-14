package com.gestion.tramite;

import com.gestion.tramite.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

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


	/**@Configuration
	public class WebConfig extends WebMvcConfigurerAdapter {

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
		}
	}**/


	@Configuration
//@Profile(PROFILE_DEV)
	public class CorsConfiguration {

		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
							.allowedOrigins("*")
							.allowedHeaders("*")
							.allowedMethods("*");
				}
			};
		}
	}


	@EnableWebSecurity
	@Configuration
	class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
			web.ignoring().antMatchers("/fileController/**");
			web.ignoring().antMatchers("/emailController/**");

		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/usuario").permitAll()
					.antMatchers(HttpMethod.POST, "/contactos").permitAll()
					.antMatchers(HttpMethod.POST, "/documentos").permitAll()
					.antMatchers(HttpMethod.POST, "/gestionTramites").permitAll()
					.antMatchers(HttpMethod.POST, "/personas").permitAll()
					.antMatchers(HttpMethod.POST, "/tramites").permitAll()
					.antMatchers(HttpMethod.POST, "/sendMail").permitAll()
					.anyRequest().authenticated();
			http.cors();


		}


	}


	/**@Configuration
	@EnableWebMvc
	public class SpringConfig implements WebMvcConfigurer {

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedOrigins("http://localhost:4200");
		}
	}**/
}