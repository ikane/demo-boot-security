package com.example.demobootsecurity.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MmsUserDetails implements UserDetails {
	
	private String username;
	private String password;
	private List<SimpleGrantedAuthority> roles = new ArrayList<>();
	
	public MmsUserDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public MmsUserDetails(String username, String role) {
		this.username = username;
		this.password = "1234";
		this.roles.add(new SimpleGrantedAuthority(role));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public String toString() {
		return "[ user:" + this.username + " - password:" + this.password + "- roles:" + this.roles.toString() + " ]";
	}

}
