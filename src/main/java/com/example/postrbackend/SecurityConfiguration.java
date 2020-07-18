package com.example.postrbackend;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

/**
 * @author Vinicius Carvalho
 * @since 1.2.2
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/**").authenticated()
				.and()
				.oauth2ResourceServer()
				.jwt()
				.and()
				.authenticationEntryPoint(new Http403ForbiddenEntryPoint());

	}
}
