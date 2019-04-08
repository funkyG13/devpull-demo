package com.devpull.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.devpull.demo.model.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
		auth.inMemoryAuthentication()
			.withUser("mike").password(passEncoder().encode("mike")).roles("ADMIN","USER")
			.and()
			.withUser("like").password(passEncoder().encode("like")).roles("USER")
			.and()
			.withUser("nike").password(passEncoder().encode("nike")).roles("COMPANY");;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/login").permitAll()
			.antMatchers("/api/register").permitAll()
			.antMatchers("/api/messages/**").hasAnyRole("USER","COMPANY","ADMIN")
			.antMatchers(HttpMethod.GET, "/api/users").hasAnyRole("USER","COMPANY")
			.antMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("USER","COMPANY")
			.antMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/api/users").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
			.antMatchers("/api/home/**").authenticated()
			.antMatchers("/api/**").hasRole("ADMIN")
			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/perform_login")
			.defaultSuccessUrl("/homepage.html")
			.failureUrl("/login.html?error=true")
			.and()
			.httpBasic();
	}
	
}
