package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
	
	 @Bean
	 public TokenAuthenticationFilter tokenAuthenticationFilter() {
	    return new TokenAuthenticationFilter();
	 }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/","/mylogin","/login").permitAll();
		
		http.authorizeRequests().anyRequest().authenticated();
		
		http.formLogin().successHandler(authenticationSuccessHandler);
		
		http.logout().logoutSuccessUrl("/");
		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
	}
	
	@Bean
	public NoOpPasswordEncoder noop() {
		return (NoOpPasswordEncoder)NoOpPasswordEncoder.getInstance();
	}
	
	

}
