package kr.co.dysnt.framework.log.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import kr.co.dysnt.framework.common.dao.CommonDao;
import kr.co.dysnt.framework.log.dao.vo.LogVo;

@Slf4j
@Repository
public class LogDao extends CommonDao {

    private final String sqlNameSpace = "mapper.log.";

    public void insertTbUserLog(LogVo logVo) {
        insert(sqlNameSpace + "insertTbUserLog", logVo);
    }
}
