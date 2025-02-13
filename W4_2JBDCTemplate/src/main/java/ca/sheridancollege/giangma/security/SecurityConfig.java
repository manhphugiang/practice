package ca.sheridancollege.giangma.security;

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

import lombok.AllArgsConstructor;

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
//    public InMemoryUserDetailsManager userDetailsServer(PasswordEncoder passwordEncoder) {
//        UserDetails customer = User
//            .withUsername("customer")
//            .password(passwordEncoder.encode("123"))
//            .roles("customer")
//            .build();
//
//        UserDetails bartender = User
//            .withUsername("bartender")
//            .password(passwordEncoder.encode("123"))
//            .roles("bartender")
//            .build();
//
//        return new InMemoryUserDetailsManager(customer, bartender);
//    }
//	
//	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		
		
		
		http.csrf().disable();
		http.headers().frameOptions().disable();	
		
		
		
		
		
		
		http.authorizeHttpRequests((authz) -> authz
				.requestMatchers(antMatcher("/edit/**")).hasRole("bartender")
				.requestMatchers(antMatcher("/delete/**")).hasRole("bartender")
				.requestMatchers(antMatcher("/add")).hasRole("bartender")
				.requestMatchers(antMatcher("/")).permitAll()
				.requestMatchers(antMatcher("/img/**")).permitAll()  
				.requestMatchers(antMatcher("/css/**")).permitAll()
				
				.requestMatchers(antMatcher("/h2-console/**")).permitAll()
				.requestMatchers(antMatcher(HttpMethod.POST, "/register")).permitAll()

				.anyRequest().authenticated()
				)
		
		
		.formLogin( (formLogin) -> formLogin
				.loginPage("/login")
				.failureUrl("/login?failed")
				.permitAll()
				)
		.logout( (logout) -> logout
				.deleteCookies("remove")
				.invalidateHttpSession(false)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/?logout")
				.permitAll())
		.exceptionHandling((exceptionHandling) -> exceptionHandling
			    .accessDeniedHandler(accessDenied)
			);
		
		
;			return http.build();

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
