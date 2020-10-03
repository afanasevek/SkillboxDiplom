package ru.afanasev.diplom.object;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
	USER, MODERATOR;

	@Override
	public String getAuthority() {

		return name();
	}
}
