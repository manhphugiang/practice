package ca.sheridancollege.giangma.security;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

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

import lombok.AllArgsConstructor;
@Configuration
@EnableWebSecurity
@AllArgsConstructor

public class SecurityConfig {
	
	
	private AccessDeniedHandler accessDenied;
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http,
	PasswordEncoder passwordEncoder) throws Exception 
	
	{
	AuthenticationManagerBuilder authenticationManagerBuilder =
	http.getSharedObject(AuthenticationManagerBuilder.class);
	
	
	authenticationManagerBuilder.userDetailsService(userDetailsService)
	.passwordEncoder(passwordEncoder);
	
	
    authenticationManagerBuilder
    .userDetailsService(userDetailsService)
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
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		
		
		http.authorizeHttpRequests((authz)-> authz
				.requestMatchers(antMatcher("/")).permitAll()
				.requestMatchers(antMatcher("/h2-console/**")).permitAll()
				.requestMatchers(antMatcher(HttpMethod.POST, "/register")).permitAll()
				.requestMatchers(antMatcher("/img/**")).permitAll()  
				.requestMatchers(antMatcher("/css/**")).permitAll()
				.requestMatchers(antMatcher("/registration/**")).permitAll()

				.anyRequest().authenticated()
				
				
				
				)
		
		
		
		
		
		//customize login and logout
		.formLogin(   (formLogin) -> formLogin   //cái dòng này sẽ yêu cầu login nếu bấm vào page user or pickle
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
