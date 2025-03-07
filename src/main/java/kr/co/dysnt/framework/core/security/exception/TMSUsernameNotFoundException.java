package kr.co.dysnt.framework.core.security.exception;

import org.springframework.security.core.AuthenticationException;

public class TMSUsernameNotFoundException extends AuthenticationException {
	private static final long serialVersionUID = 1L;

	public TMSUsernameNotFoundException(String msg) {
		super(msg);
	}

}
