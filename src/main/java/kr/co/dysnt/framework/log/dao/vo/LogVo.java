package kr.co.dysnt.framework.log.dao.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class LogVo implements Serializable {

    private static final long serialVersionUID = 8012111128224654940L;

    private BigDecimal logSeq;
    private String userId;
    private String cnntBrwr;
    private String cnntOps;
    private String cnntIp;
    private String useHisCntn;
    private String menuId;
    private Date inptDt;
    private String inptId;
    private String inptIp;
    private Date updtDt;
    private String updtId;
    private String updtIp;
}
