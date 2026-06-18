package com.somativa.cafearomaesabor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
// import AntPathRequestMatcher removed to keep configuration compatible with older Spring versions

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		// Usuários em memória para testes
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder.encode("123456"))
				.roles("ADMIN")
				.build();

		UserDetails joao = User.builder()
				.username("joao")
				.password(passwordEncoder.encode("123456"))
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(admin, joao);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// desabilita CSRF para simplificar o fluxo de logout via GET e facilitar testes
				.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(auth -> auth
						// permitir acesso aos recursos estáticos e à página de login
						.requestMatchers("/**","/css/**", "/js/**", "/images/**", "/login", "/database.sql").permitAll()
						.anyRequest().authenticated()
				)
				.formLogin(form -> form
						.loginPage("/login")
						.loginProcessingUrl("/login")
						.usernameParameter("username")
						.passwordParameter("password")
						.defaultSuccessUrl("/home", true)
						.permitAll()
				)
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login")
						.permitAll()
				)
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}
}
