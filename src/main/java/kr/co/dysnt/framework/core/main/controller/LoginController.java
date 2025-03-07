package kr.co.dysnt.framework.core.main.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.dysnt.framework.core.security.service.impl.TmsUserDetailsServiceImpl;
import kr.co.dysnt.framework.core.security.user.TmsUser;
import kr.co.dysnt.framework.core.security.utils.JwtUtil;
import kr.co.dysnt.framework.core.util.controller.CommonController;

@Controller
@RequestMapping("/accounts")
public class LoginController extends CommonController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TmsUserDetailsServiceImpl tmsUserDetailsService; // ✅ `@Resource` → `@Autowired`로 변경

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // ✅ 비밀번호 인코더

    @RequestMapping(value = "/login")
    public @ResponseBody JSONObject login(HttpServletRequest request, @RequestBody Map<String, String> reqParam)
            throws Exception {
        JSONObject result = new JSONObject();

        String userId = reqParam.get("userId");
        String password = reqParam.get("password");
        String token;

        try {
            // ✅ `UserDetails`로 받아서 변환
            UserDetails userDetails = tmsUserDetailsService.loadUserByUsername(userId);
            if (userDetails instanceof TmsUser) {
                TmsUser tmsUser = (TmsUser) userDetails;

                // ✅ 비밀번호 비교
                if (encoder.matches(password, tmsUser.getPassword())) {
                    token = jwtUtil.generateToken(tmsUser);
                    result.put("code", "200");
                    result.put("token", token);
                    result.put("userInfo", tmsUser);
                    result.put("result", "success!");
                } else {
                    result.put("code", "400");
                    result.put("result", "패스워드가 틀렸습니다.");
                }
            } else {
                result.put("code", "400");
                result.put("result", "사용자 아이디가 틀렸습니다.");
            }
        } catch (Exception e) {
            result.put("code", "500");
            result.put("result", "서버 오류 발생: " + e.getMessage());
        }

        return result;
    }
}
