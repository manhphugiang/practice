package ca.sheridancollege.giangma.security;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
@Configuration
@EnableWebSecurity
@AllArgsConstructor

public class SecurityConfig {

	private AccessDeniedHandler accessDenied;
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http,
	PasswordEncoder passwordEncoder) throws Exception {
	AuthenticationManagerBuilder authenticationManagerBuilder =
	http.getSharedObject(AuthenticationManagerBuilder.class);
	authenticationManagerBuilder.userDetailsService(userDetailsService)
	.passwordEncoder(passwordEncoder);
	return authenticationManagerBuilder.build();
	}
	
		
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	
	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		// delete on production. we need this for development, when we migrate to another system 
		
		http.csrf().disable();
		http.headers().frameOptions().disable();	
		
		
		
		
		
		http.authorizeHttpRequests((authz)-> authz

			
			.requestMatchers(antMatcher("/edit/**")).hasRole("VENDER")
			.requestMatchers(antMatcher("/delete/**")).hasRole("VENDER")
			.requestMatchers(antMatcher("/addTicket")).hasRole("VENDER")
			.requestMatchers(antMatcher("/viewVender/**")).hasRole("VENDER")
			
			
			
			
			.requestMatchers(antMatcher("/")).permitAll()			
			.requestMatchers(antMatcher("/registration")).permitAll()
			.requestMatchers(antMatcher(HttpMethod.POST, "/register")).permitAll()
			.requestMatchers(antMatcher("/img/**")).permitAll()
			.requestMatchers(antMatcher("/h2-console/**")).permitAll()
			.requestMatchers(antMatcher("/css/**")).permitAll()

			
			.anyRequest().authenticated()
		)
		
			
		
		//customize login and logout
		.formLogin(   (formLogin) -> formLogin 
				.loginPage("/login")
				.failureUrl("/login?failed")
				.permitAll()
				)
		
		.logout(  	(logout) -> logout
				.deleteCookies("remove")
				.invalidateHttpSession(false)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/?logout")
				.permitAll()
				)
		
		.exceptionHandling((exceptionHandling) -> exceptionHandling
		    .accessDeniedHandler(accessDenied)
		);


			return http.build();
	}	
	
	
	
	
	
	
	
	
	
	
}
