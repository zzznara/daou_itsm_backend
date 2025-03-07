package kr.co.dysnt.framework.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.dysnt.framework.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final UserDetailsService jwtUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter; // ✅ 필드 추가

    /**
     * ✅ 명확한 생성자 주입 추가 (Spring이 `JwtRequestFilter`를 자동 주입)
     */
    public WebSecurityConfig(UserDetailsService jwtUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * ✅ AuthenticationManager를 최신 방식으로 등록
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(jwtUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(Collections.singletonList(authProvider));
    }

    /**
     * ✅ Spring Security 필터 체인 설정
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // ✅ jwtRequestFilter 제거 (필드 사용)
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/auth/**"),
                                new AntPathRequestMatcher("/accounts/**"),
                                new AntPathRequestMatcher("/app/**"),
                                new AntPathRequestMatcher("/tms/**"),
                                new AntPathRequestMatcher("/appinfo"),
                                new AntPathRequestMatcher("/member/**"),
                                new AntPathRequestMatcher("/bus/**"))
                        .permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            Map<String, Object> responseMap = new HashMap<>();
                            ObjectMapper mapper = new ObjectMapper();
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            responseMap.put("error", true);
                            responseMap.put("message", "Unauthorized");
                            response.setHeader("Content-Type", "application/json;charset=UTF-8");
                            response.getWriter().write(mapper.writeValueAsString(responseMap));
                        }))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // ✅ 필드에서 가져와 사용

        return http.build();
    }

    /**
     * ✅ BCryptPasswordEncoder를 Bean으로 등록
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * ✅ CORS 설정
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
