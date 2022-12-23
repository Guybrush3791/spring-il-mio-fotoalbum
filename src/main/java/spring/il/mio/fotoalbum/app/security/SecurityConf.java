package spring.il.mio.fotoalbum.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

import spring.il.mio.fotoalbum.app.service.security.UserService;

@Configuration
public class SecurityConf {

	@Bean
	public SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {
		
		// everything is open beside all routes starting with /admin
		http.authorizeHttpRequests()	
//				.requestMatchers("/admin/", "/admin/**").hasAuthority("ADMIN") // disabled for debug	
				.requestMatchers("/**").permitAll()
			.and().formLogin()
				.loginPage("/home")
			.and().logout()
			.and().cors().disable()
			.csrf().disable()
		; 
		
		return http.build();
	}
	
	@Bean
	public SpringSecurityDialect securityDialect() {
	    
		return new SpringSecurityDialect();
	}
	
	@Bean
	public UserDetailsService getUserDetailService() {
		
		return new UserService();
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider getAuthProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(getUserDetailService());
		provider.setPasswordEncoder(getPasswordEncoder());
		
		return provider;
	}
}
