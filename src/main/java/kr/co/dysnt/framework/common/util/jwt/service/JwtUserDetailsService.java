package kr.co.dysnt.framework.common.util.jwt.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.security.user.TmsUser;
import kr.co.dysnt.framework.filter.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("jwtUserDetailsService")
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    // @NonNull
    // private final AuthService authService;
    @Resource(name = "tmsUserDetailsService")
    private UserDetailsService tmsUserDetailsService;

//    private static final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class); // ✅ Logger 추가

    @Override
    public TmsUser loadUserByUsername(String username) throws UsernameNotFoundException {

        // TmsUser authVo = new TmsUser();

        try {
            // Map<String, Object> paramMap = new HashMap<String, Object>();
            // paramMap.put("userId", username);
            // authVo = authService.getUser(paramMap);
            TmsUser tmsUser = (TmsUser) tmsUserDetailsService.loadUserByUsername(username);
            return tmsUser;

        } catch (Exception e) {
            log.error("[TMS LOGIN] JwtUserDetailsService loadUserByUsername Exception : {}", e.getMessage());
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
