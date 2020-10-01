package ru.afanasev.diplom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.afanasev.diplom.service.UserService;

@Configuration 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
		@Autowired
		UserService userSevice;


	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return NoOpPasswordEncoder.getInstance();
	    }
		
		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			http
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/api/init")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/api/post/")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/api/search/")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/api/byDate")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/api/byTag")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/api/post/moderation")
			.authenticated()
			.antMatchers(HttpMethod.GET, "/api/post/my")
			.authenticated()
			.antMatchers(HttpMethod.GET, "/api/post/{id}")
			.permitAll()
			.antMatchers(HttpMethod.POST, "/api/post")
			.authenticated()
			.antMatchers(HttpMethod.POST, "api/image")
			.authenticated()
			.antMatchers(HttpMethod.PUT, "/api/post/{id}")
			.authenticated()
			.antMatchers(HttpMethod.POST, "/api/comment/")
			.authenticated()
			.antMatchers(HttpMethod.GET, "/api/tag/")
			.permitAll()
			.antMatchers(HttpMethod.POST, "/api/moderation")
			.hasAnyRole("MODERATOR")
			.antMatchers(HttpMethod.POST, "/api/auth/login")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/api/auth/check")
			.permitAll()
			.antMatchers(HttpMethod.POST, "/api/auth/restore")
			.permitAll()
			.antMatchers(HttpMethod.POST, "/api/auth/password")
			.permitAll()
			.antMatchers(HttpMethod.POST, "/api/auth/register")
			.permitAll()
			.antMatchers(HttpMethod.POST, "/api/profile/my")
			.authenticated()
			.antMatchers(HttpMethod.GET, "/api/auth/captcha")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/api/statistics/my")
			.authenticated()
			.antMatchers(HttpMethod.GET, "/api/statistics/all")
			.permitAll()
			.antMatchers(HttpMethod.POST, "/api/post/like")
			.authenticated()
			.antMatchers(HttpMethod.POST, "/api/post/dislike")
			.authenticated()
			.antMatchers(HttpMethod.GET, "/api/auth/logout")
			.authenticated()
			.antMatchers(HttpMethod.GET, "/api/settings/")
			.permitAll()
			.antMatchers(HttpMethod.PUT, "/api/settings/")
			.hasAnyRole("Moderator"); 

		}
		
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSevice).passwordEncoder(passwordEncoder());
		}
		

}
