package me.matt.springsecurity.resolutions;

import java.lang.reflect.Field;

import static me.matt.springsecurity.resolutions.ReflectionSupport.getDeclaredFieldByColumnName;
import static me.matt.springsecurity.resolutions.ReflectionSupport.getDeclaredFieldByName;
import static me.matt.springsecurity.resolutions.ReflectionSupport.getDeclaredFieldByType;
import static me.matt.springsecurity.resolutions.ReflectionSupport.getProperty;

public class ReflectedUserAuthority {
	static Field userField;
	static Field usernameColumnField;
	static Field authorityField;
	static Field authorityColumnField;

	static {
		userField = getDeclaredFieldByType(UserAuthority.class, User.class);
		if (userField != null) userField.setAccessible(true);
		usernameColumnField = getDeclaredFieldByColumnName(UserAuthority.class, "username");
		authorityField = getDeclaredFieldByName(UserAuthority.class, "authority");
		authorityColumnField = getDeclaredFieldByColumnName(UserAuthority.class, "authority");
		if (authorityColumnField != null) authorityColumnField.setAccessible(true);
	}

	UserAuthority userAuthority;

	public ReflectedUserAuthority(UserAuthority userAuthority) {
		this.userAuthority = userAuthority;
	}

	User getUser() {
		return getProperty(this.userAuthority, userField);
	}

	String getAuthority() {
		return getProperty(this.userAuthority, authorityColumnField);
	}
}
