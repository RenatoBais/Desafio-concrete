package br.com.concrete.desafio.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
	 
	public JwtAuthenticationTokenFilter() {
		super("/**");
	}
	
	@Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }
	
	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
	    super.setAuthenticationManager(authenticationManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		String authenticationToken = request.getHeader(JwtConstants.header);
		
		if(authenticationToken == null) {
			throw new RuntimeException("Não autorizado!");
		}
		
		JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(authenticationToken);
		jwtAuthenticationToken.setToken(authenticationToken);
		jwtAuthenticationToken.setAuthenticated(true);
		return getAuthenticationManager().authenticate(jwtAuthenticationToken);
	}

	@Override
	 protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
			 FilterChain chain, Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request,response);
	}

}	
