package ca.sheridancollege.ngquocth.security;

import org.springframework.context.annotation.Bean;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.AllArgsConstructor;

//class này để override default id va password

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	
	private AccessDeniedHandler accessDenied;
	
	//tạo account
	@Bean 
	public InMemoryUserDetailsManager userDetailsService (PasswordEncoder passwordEncoder) {
		//tạo user 1 và role
		UserDetails user1 = User
				.withUsername("Thai")
				.password(passwordEncoder.encode("123"))
				.roles("USER") //roles always be upper case
				.build();
		
		//tạo user 2 và role
		UserDetails user2 = User
				.withUsername("Phu")
				.password(passwordEncoder.encode("321"))
				.roles("USER", "PICKLE")  
				.build();
		
		return new InMemoryUserDetailsManager(user1, user2);
		
	}
	
	
	

	@Bean
	public PasswordEncoder passwordEncoder() { 
		PasswordEncoder encoder = 
				PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;
	}

	
		
	
	//import static org.springframework.security
	//.web.util.matcher.AntPathRequestMatcher.antMatcher;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((authz)-> authz
			.requestMatchers(antMatcher("/user")).hasRole("USER")  //Note: Map with controller
			//.requestMatchers(antMatcher("/user")).hasAnyRole("USER", "ROLE2", ...) cái này là hasAnyRole
			
			//OR
			//.permitAll() -> to access without login
			
			//OR
			//.requestMatchers(antMatcher("/user/**")) -> Wildcard
			// matches -> "/user" or "/user/something" or "/user/a/b/c/4/7"
			
			.requestMatchers(antMatcher("/pickle")).hasRole("PICKLE")
			.requestMatchers(antMatcher("/")).permitAll()
			.requestMatchers(antMatcher("/img/**")).permitAll()    //allow everyone to see imgs
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
		
		.exceptionHandling( (exceptionHandling)-> exceptionHandling
				.accessDeniedHandler(accessDenied)
				
		);
		
		
		return http.build();
		
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}







