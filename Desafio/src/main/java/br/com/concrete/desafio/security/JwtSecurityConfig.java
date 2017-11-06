package br.com.concrete.desafio.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableGlobalMethodSecurity(prePostEnabled =true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
	
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers("/api/register","/api/login","/api/profile/**");
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable()
		.authorizeRequests().antMatchers("/api/register","/api/login").permitAll()
		.anyRequest().authenticated()
		.and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.headers().cacheControl();
		
	}
	
}
