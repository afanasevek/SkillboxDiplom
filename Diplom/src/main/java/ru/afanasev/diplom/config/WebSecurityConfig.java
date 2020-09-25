package ru.afanasev.diplom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ru.afanasev.diplom.service.UserService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
		@Autowired
		UserService userSevice;
		
		@Bean
		public BCryptPasswordEncoder bcrypt() {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
		}
	
		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			http
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.permitAll()
			.and()
			.logout()
			.permitAll()
			.logoutSuccessUrl("/");
		}
		
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSevice).passwordEncoder(bcrypt());
		}
		

}
