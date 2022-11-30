package com.springboot.CRUDdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class CustomSecurity extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
		.withUser("user")
		.password("pass")
		.roles("USER")
		.and()
		.withUser("admin")
		.password("admin")
		.roles("ADMIN");
		
	}
	
	// without below method we get "IllegalArgumentException: There is no PasswordEncoder mapped for the id "null""
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	//below method to authorize to request
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		//highest priorety should be at top always
		.antMatchers("/api/admin").hasRole("ADMIN")
		.antMatchers("/api/students").hasRole("ADMIN")
		.antMatchers("/api/user").hasRole("USER")
//		.antMatchers("/").permitAll()
		.and().formLogin();
	
	}

}
