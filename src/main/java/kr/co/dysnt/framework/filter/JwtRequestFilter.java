package kr.co.dysnt.framework.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import kr.co.dysnt.framework.common.util.jwt.JwtTokenUtil;
import kr.co.dysnt.framework.common.util.jwt.service.JwtUserDetailsService;
import kr.co.dysnt.framework.core.security.user.TmsUser;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public JwtRequestFilter(JwtUserDetailsService jwtUserDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        if (StringUtils.startsWith(requestTokenHeader, "Bearer ")) {
            String jwtToken = requestTokenHeader.substring(7);

            try {
                String username = jwtTokenUtil.getUsernameFromToken(jwtToken);

                if (StringUtils.isNotEmpty(username)
                        && SecurityContextHolder.getContext().getAuthentication() == null) {

                    TmsUser tmsUser = jwtUserDetailsService.loadUserByUsername(username);

                    if (jwtTokenUtil.validateToken(jwtToken, tmsUser)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                tmsUser, null, tmsUser.getAuthorities());
                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            } catch (IllegalArgumentException e) {
                log.error("Unable to get JWT Token", e);
            } catch (ExpiredJwtException e) {
                log.error("JWT Token has expired", e);
            } catch (Exception e) {
                log.error("JWT Token validation error", e);
            }
        } else {
            log.warn("JWT Token does not begin with Bearer String");
        }
        chain.doFilter(request, response);
    }
}
