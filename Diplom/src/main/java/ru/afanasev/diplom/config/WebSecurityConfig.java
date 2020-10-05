package ru.afanasev.diplom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.afanasev.diplom.object.Roles;
import ru.afanasev.diplom.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userSevice;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/init").permitAll()
				.antMatchers(HttpMethod.GET, "/api/post/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/search/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/byDate").permitAll()
				.antMatchers(HttpMethod.GET, "/api/byTag").permitAll()
				.antMatchers(HttpMethod.GET, "/api/post/moderation").hasAnyAuthority(Roles.MODERATOR.name())
				.antMatchers(HttpMethod.GET, "/api/post/my").hasAnyAuthority(Roles.MODERATOR.name(), Roles.USER.name())
				.antMatchers(HttpMethod.GET, "/api/post/{id}").permitAll()
				.antMatchers(HttpMethod.POST, "/api/post").hasAnyAuthority(Roles.MODERATOR.name(), Roles.USER.name())
				.antMatchers(HttpMethod.POST, "api/image").hasAnyAuthority(Roles.MODERATOR.name(), Roles.USER.name())
				.antMatchers(HttpMethod.PUT, "/api/post/{id}").hasAnyAuthority(Roles.MODERATOR.name(), Roles.USER.name())
				.antMatchers(HttpMethod.POST, "/api/comment/").hasAnyAuthority(Roles.MODERATOR.name(), Roles.USER.name())
				.antMatchers(HttpMethod.GET, "/api/tag/").permitAll()
				.antMatchers(HttpMethod.POST, "/api/moderation").hasAnyAuthority(Roles.MODERATOR.name())
				.antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
				.antMatchers(HttpMethod.GET, "/api/auth/check").permitAll()
				.antMatchers(HttpMethod.POST, "/api/auth/restore").permitAll()
				.antMatchers(HttpMethod.POST, "/api/auth/password").permitAll()
				.antMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
				.antMatchers(HttpMethod.POST, "/api/profile/my").hasAnyAuthority(Roles.MODERATOR.name(), Roles.USER.name())
				.antMatchers(HttpMethod.GET, "/api/auth/captcha").permitAll()
				.antMatchers(HttpMethod.GET, "/api/statistics/my").hasAnyAuthority(Roles.MODERATOR.name(), Roles.USER.name())
				.antMatchers(HttpMethod.GET, "/api/statistics/all").permitAll()
				.antMatchers(HttpMethod.POST, "/api/post/like").hasAnyAuthority(Roles.MODERATOR.name(), Roles.USER.name())
				.antMatchers(HttpMethod.POST, "/api/post/dislike").hasAnyAuthority(Roles.MODERATOR.name(), Roles.USER.name())
				.antMatchers(HttpMethod.GET, "/api/auth/logout").hasAnyAuthority(Roles.MODERATOR.name(), Roles.USER.name())
				.antMatchers(HttpMethod.GET, "/api/settings/").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/settings/").hasAnyAuthority(Roles.MODERATOR.name());

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSevice).passwordEncoder(passwordEncoder());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}
	
	

}
