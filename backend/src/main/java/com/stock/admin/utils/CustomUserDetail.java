package com.stock.admin.utils;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import com.stock.admin.model.entity.ApplicationUser;

public class CustomUserDetail implements UserDetails{

    private static final long serialVersionUID = 1L;
    private ApplicationUser user;

    private Set<GrantedAuthority> authorities=null;

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities)
    {
        this.authorities=authorities;
    }

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
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

}

