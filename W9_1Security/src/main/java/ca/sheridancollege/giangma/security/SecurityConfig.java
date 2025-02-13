package ca.sheridancollege.giangma.security;
import ca.sheridancollege.giangma.security.LoginAccessDeniedHandler;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
@Configuration
@EnableWebSecurity
@AllArgsConstructor

public class SecurityConfig {

	private AccessDeniedHandler accessDenied;
	private UserDetailsService userDetailsService;
//	@Bean
//	public InMemoryUserDetailsManager userDetailsServer (PasswordEncoder passwordEncoder) {
//		UserDetails user1 = User
//				.withUsername("Manh")
//				.password(passwordEncoder.encode("123"))
//				.roles("USER")
//				.build();
//		
//
//		UserDetails user2 = User
//				.withUsername("Thai")
//				.password(passwordEncoder.encode("123"))
//				.roles("USER", "CREAM")
//				.build();
//				return new InMemoryUserDetailsManager(user1, user2);
//	}
//	
	
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
			.requestMatchers(antMatcher("/page1")).hasRole("USER")  //Note: Map with controller
			//.requestMatchers(antMatcher("/user")).hasAnyRole("USER", "ROLE2", ...) cái này là hasAnyRole
			
			//OR
			//.permitAll() -> to access without login
			
			//OR
			//.requestMatchers(antMatcher("/user/**")) -> Wildcard
			// matches -> "/user" or "/user/something" or "/user/a/b/c/4/7"
			
			.requestMatchers(antMatcher("/page2")).hasRole("CREAM")
			.requestMatchers(antMatcher("/")).permitAll()
			.requestMatchers(antMatcher("/registration")).permitAll()
			.requestMatchers(antMatcher(HttpMethod.POST, "/register")).permitAll()
			.requestMatchers(antMatcher("/img/**")).permitAll()//allow everyone to see imgs
			.requestMatchers(antMatcher("/h2-console/**")).permitAll()

			
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
