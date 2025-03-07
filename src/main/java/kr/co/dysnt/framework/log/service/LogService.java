package kr.co.dysnt.framework.log.service;

import java.util.HashMap;

import kr.co.dysnt.framework.log.dao.vo.LogVo;

public interface LogService {

    void insertTbUserLog(LogVo logVo) throws Exception;
}
