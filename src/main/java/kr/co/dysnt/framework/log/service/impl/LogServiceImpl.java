package kr.co.dysnt.framework.log.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.security.user.TmsUser;
import kr.co.dysnt.framework.log.dao.LogDao;
import kr.co.dysnt.framework.log.dao.vo.LogVo;
import kr.co.dysnt.framework.log.service.LogService;

import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogDao logDao;

    @Override
    public void insertTbUserLog(LogVo logVo) throws Exception {

        String thisUserId = "";
        String phoneIp = "";

        // AuthVo userDetails = null;
        TmsUser tmsUser = null;
        try {
            // userDetails = (AuthVo)
            // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            // thisUserId = userDetails.getUserId();
            tmsUser = (TmsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            thisUserId = tmsUser.getUserId();
        } catch (Exception e) {
            thisUserId = "DRIVER";
            log.error("security context 사용자 정보 조회 예외");
            log.error(e.getMessage());
        }

        logDao.insertTbUserLog(logVo);
    }

}
