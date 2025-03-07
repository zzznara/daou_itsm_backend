package kr.co.dysnt.framework.common.util.jwt;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.co.dysnt.framework.common.util.jwt.service.JwtUserDetailsService;
import kr.co.dysnt.framework.core.security.utils.JwtUtil;

@Component
// @RequiredArgsConstructor
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    // @Resource(name = "jwtUserDetailsService")
    private final JwtUserDetailsService jwtUserDetailsService;

    private static final long serialVersionUID = 7822451712065323343L;
    private static final long JWT_TOKEN_VALIDITY = 100L * 60 * 60 * 1000; // 단위: 밀리초

    /**
     * JWT 서명 키 생성 (HMAC SHA-512 사용)
     */

    public JwtTokenUtil(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    private Key getSigningKey() {
        //byte[] keyBytes = Base64.getDecoder().decode(JwtUtil.secretKey);
        //return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
        
        return JwtUtil.secretKey;
    }

    /**
     * 토큰에서 사용자 이름(Username) 추출
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * 토큰에서 만료 날짜 추출
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * 토큰에서 특정 Claim 추출 (제네릭)
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 토큰의 모든 Claims 반환
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 토큰이 만료되었는지 확인
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 새로운 JWT 토큰 생성
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * JWT 토큰으로부터 Authentication 객체 생성
     */
    public Authentication getAuthentication(String token) {
        String username = getUsernameFromToken(token);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * 토큰이 유효한지 검증
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
