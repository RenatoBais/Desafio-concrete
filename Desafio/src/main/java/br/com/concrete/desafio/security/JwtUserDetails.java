package br.com.concrete.desafio.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class JwtUserDetails implements UserDetails {

	private static final long serialVersionUID = -6506568667490781936L;

	private String userName;
	
	private String token;
	
	private long id;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtUserDetails(String userName, long id, String token, List<GrantedAuthority> authorities) {
		
		this.userName = userName;
		this.id = id;
		this.token = token;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	public String getUserName() {
		return userName;
	}

	public String getToken() {
		return token;
	}

	public long getId() {
		return id;
	}

}
