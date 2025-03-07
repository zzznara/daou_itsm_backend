package kr.co.dysnt.framework.common.provider;

// package kr.co.kisco.kiscotmsmobile.common.provider;
//
// import kr.co.kisco.kiscotmsmobile.auth.dao.vo.AuthVo;
// import lombok.NonNull;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.security.authentication.AuthenticationProvider;
// import
// org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;
//
// import java.util.ArrayList;
// import java.util.Collections;
//
// @Slf4j
// @Component
// @RequiredArgsConstructor
// public class CustomAuthenticationProvider implements AuthenticationProvider {
//
// private final UserDetailsService jwtUserDetailsService;
//
// private final PasswordEncoder passwordEncoder;
//
// @Override
// public Authentication authenticate(Authentication authentication) throws
// AuthenticationException {
// String name = authentication.getName();
// String password = authentication.getCredentials().toString();
//
// AuthVo authVo = (AuthVo) jwtUserDetailsService.loadUserByUsername(name);
//
// log.debug("authenticate name : {}", name);
// log.debug("authenticate passowrd : {}", password);
// log.debug("authVo password : {}", authVo.getPassword());
//
// if (passwordEncoder.matches(password, authVo.getPassword())) {
// throw new AuthenticationException("비밀번호가 일치하지 않습니다."){};
// }
//
// SimpleGrantedAuthority simpleGrantedAuthority = new
// SimpleGrantedAuthority(authVo.getRole());
//
// return new UsernamePasswordAuthenticationToken(name, password,
// new ArrayList<>(Collections.singleton(simpleGrantedAuthority)));
// }
//
// @Override
// public boolean supports(Class<?> authentication) {
// return authentication.equals(UsernamePasswordAuthenticationToken.class);
// }
// }
