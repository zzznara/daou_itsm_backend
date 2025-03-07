package kr.co.dysnt.framework.common.util.jwt.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.security.user.TmsUser;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

@Slf4j
@Service("jwtMemberUserDetailsService")
@RequiredArgsConstructor
public class JwtMemberUserDetailsService implements UserDetailsService {

    // @NonNull
    // private final AuthService authService;
    @Resource(name = "tmsUserDetailsService")
    private UserDetailsService tmsUserDetailsService;

    @Override
    public TmsUser loadUserByUsername(String username) throws UsernameNotFoundException {

        // AuthVo authVo = new AuthVo();

        try {
            // Map<String, Object> paramMap = new HashMap<String, Object>();
            // paramMap.put("userId", username);
            // authVo = authService.getMemberUser(paramMap);
            TmsUser tmsUser = (TmsUser) tmsUserDetailsService.loadUserByUsername(username);
            return tmsUser;

        } catch (Exception e) {
            log.error("[MEMBER LOGIN] JwtMemberUserDetailsService loadUserByUsername Exception : {}", e.getMessage());
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
