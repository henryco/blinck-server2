package dev.tindersamurai.blinckserver.security.details;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class BlinckUserDetails implements UserDetails {

	private @Getter final String username;
	private final String[] authorities;
	private final boolean locked;

	public BlinckUserDetails(
			String username, boolean locked,
			String ... authorities
	) {
		this.authorities = authorities;
		this.username = username;
		this.locked = locked;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(authorities);
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.locked;
	}

	@Override
	public boolean isAccountNonExpired() {
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
	public String getPassword() {
		return null;
	}
}