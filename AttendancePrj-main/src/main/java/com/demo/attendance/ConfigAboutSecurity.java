package com.demo.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.demo.attendance.service.AttendancePrjUserDetailsService;

@EnableWebSecurity
public class ConfigAboutSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private AttendancePrjUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/login", "/sign-in", "/sign-up", "/resources/**").permitAll()
			.antMatchers("/admin/**").hasRole("Admin")
			.antMatchers("/employee/**").hasAnyRole("Admin", "Employee")
			.anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/sign-in")
			.loginProcessingUrl("/login")
			.usernameParameter("mail")
			.passwordParameter("pass")
			.defaultSuccessUrl("/home", true)
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
