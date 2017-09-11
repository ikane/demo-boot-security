package com.example.demobootsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * The Class SecurityConfigProd.
 * 
 * This class represent Spring Security configuration in standalone mode e.g not using SSO with Spring Cloud OAuth2 for example. It's convenient in developpment
 * environment to prevent having to connect to an SSO server.
 */
@EnableWebSecurity
public class SecurityConfigDev extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
	 */
	//@formatter:off
	@Override
	public void configure(WebSecurity web) throws Exception {
		// Spring Security should completely ignore URLs starting with /resources/
		web.ignoring()			
			.antMatchers("/assets/**")
			.antMatchers("/font-awesome/**")
			.antMatchers("/fonts/**")
		    .antMatchers("/resources/**")
		    .antMatchers("/bower_components/**")
		    .antMatchers("/swagger-ui.html","/swagger-resources/**", "/v2/api-docs")
		    ;
	}
	//@formatter:on
		
	
	 /* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Auto-generated method stub
		http
		//.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		//.and()
		.csrf().disable()		
		.authorizeRequests()
		 	.antMatchers("/static/**").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.and()
			.httpBasic()
//			.failureUrl("/login?error")
//			.defaultSuccessUrl("/")
//			.loginPage("/login")
//			.permitAll()
//			.and()
//		.logout()
//			.permitAll()
		.and()
		.addFilter(new CustomPreAuthentocationFilter())
		;
	}
	// @formatter:on

	/**
	 * Configure global.
	 *
	 * @param auth the auth
	 * @throws Exception the exception
	 */
	/*
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
	            .withUser("admin").password("1234").roles("ADMIN")
	            .and()
                .withUser("reader").password("1234").roles("READER")
                ;
    }
    */
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
}
