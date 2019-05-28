package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()
		.and().authorizeRequests().anyRequest()
		.authenticated().and().formLogin().loginPage("/mylogin")
		.loginProcessingUrl("/log").usernameParameter("name")
		.passwordParameter("pass").defaultSuccessUrl("/").permitAll()
		.and().logout().logoutUrl("/mylogout").logoutSuccessUrl("/").permitAll();
	}
	
	@Bean
	public NoOpPasswordEncoder noop() {
		return (NoOpPasswordEncoder)NoOpPasswordEncoder.getInstance();
	}
	
	

}
