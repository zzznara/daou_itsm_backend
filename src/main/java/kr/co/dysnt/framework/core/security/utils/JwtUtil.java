package kr.co.dysnt.framework.core.security.utils;

import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import kr.co.dysnt.framework.core.security.user.TmsUser;

@Component
public class JwtUtil {
  // @Value("${jwt.secret}")
  // private static final String secret =
  // "xFyCYquhzRruR5LRG/u97x9uvplBAjUcP6LaaTosYpbwopUEyPfhyVWr4WNxRTxGJh2d5iCEtNT9aWoz6OfImw==";//
  // Base64 encode "123"
  public static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

  private long tokenValidTime = 10000L * 60 * 60;

  public static TmsUser parseToken(String token) {
    try {
      //
      // // 토큰의 유효성 + 만료일자 확인
      // public boolean validateToken(String jwtToken) {
      // try {
      // Jws<Claims> claims =
      // Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
      // return !claims.getBody().getExpiration().before(new Date());
      // } catch (Exception e) {
      // return false;
      // }
      // }
      //
      Claims body = Jwts.parser()
          // .setSigningKey(secret)
          .setSigningKey(secretKey)
          .parseClaimsJws(token)
          .getBody();
      System.out.println("jwt token parse = " + body.toString());
      String username = (String) body.get("name");
      String password = (String) body.get("password");
      String role = (String) body.get("role");

      // 以下設定會影響Spring Security是否讓此帳號通過驗證
      boolean enabled = true; // 此帳號是否啟用
      boolean accountNonExpired = true; // 此帳號是否未過期
      boolean credentialsNonExpired = true; // 此憑證是否過期
      boolean accountNonLocked = true; // 此帳號是否鎖住

      List authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(role); // 取得角色權限

      // User user = new User(username, password, enabled, accountNonExpired,
      // credentialsNonExpired, accountNonLocked, authorityList);
      TmsUser user = new TmsUser();
      user.setUserId(username);

      return user;

    } catch (JwtException | ClassCastException e) {
      return null;
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 產生 JWT token, payload中裝載name, password, role
   */
  public String generateToken(TmsUser user) {
    Claims claims = Jwts.claims().setSubject("");
    claims.put("name", user.getUsername());
    claims.put("password", user.getPassword());
    claims.put("role", user.getRole());
    Date now = new Date();
    return Jwts.builder().setClaims(claims) // 정보 저장
        .setIssuedAt(now) // 토큰 발행 시간 정보
        .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
        // .signWith(SignatureAlgorithm.HS512, secret) // 사용할 암호화 알고리즘과
        .signWith(secretKey)
        // signature 에 들어갈 secret값 세팅
        .setSubject(user.getUserId())
        .compact();
    //
    // return Jwts.builder()
    // .setClaims(claims)
    // .signWith(SignatureAlgorithm.HS512, secret)
    // .compact();
  }

  public String generateRnToken(TmsUser user) {
    Claims claims = Jwts.claims().setSubject("");

    /*
     * claims.put("name", user.getUsername());
     * claims.put("password", user.getPassword());
     * claims.put("role", user.getRole());
     */

    claims.put("name", "ghong");
    claims.put("password", "ghong");
    claims.put("role", "DRIVER");

    Date now = new Date();
    return Jwts.builder().setClaims(claims) // 정보 저장
        .setIssuedAt(now) // 토큰 발행 시간 정보
        .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
        // .signWith(SignatureAlgorithm.HS512, secret) // 사용할 암호화 알고리즘과
        .signWith(secretKey)
        // signature 에 들어갈 secret값 세팅
        .compact();
    //
    // return Jwts.builder()
    // .setClaims(claims)
    // .signWith(SignatureAlgorithm.HS512, secret)
    // .compact();
  }
}
