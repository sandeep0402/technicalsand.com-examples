package com.technicalsand.springsecurity.auth.defaultlogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(new BCryptPasswordEncoder())
				.withUser("technicalsand")
				.password(passwordEncoder().encode("password1"))
				.roles("USER")
				.and()
				.withUser("reader")
				.password(passwordEncoder().encode("reader1"))
				.roles("USER")
		;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
                .authorizeRequests()
				    .antMatchers("/resources/**").permitAll()
				    .anyRequest().authenticated()
				    .and()
				.formLogin()
				    .loginPage("/login")
				    .permitAll()
				    .and()
				.logout()
				    .permitAll()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
}