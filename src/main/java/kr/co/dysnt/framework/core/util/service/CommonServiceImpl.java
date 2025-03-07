package kr.co.dysnt.framework.core.util.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import kr.co.dysnt.framework.common.auth.dao.AuthDao;
import kr.co.dysnt.framework.core.security.user.TmsUser;
import kr.co.dysnt.framework.core.util.IUtility;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

public class CommonServiceImpl extends EgovAbstractServiceImpl {

	protected static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(CommonServiceImpl.class);

	public Map<String, Map<String, Object>> checkMap = null;

	protected DefaultTransactionDefinition definition; // 기본 트랜잭션 정의

	@Resource(name = "authDao")
	private AuthDao authDao;

	@Resource(name = "txManager")
	protected DataSourceTransactionManager transactionManager; // Datasource 기반 트랜잭션 매니저
	protected TransactionStatus status; // 트랜잭션 상태
	protected boolean doCommit; // commit 여부

	public CommonServiceImpl() {
		definition = new DefaultTransactionDefinition();
	}

	public void setDoRollBack() {
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}

	/*
	 * 2020-07-30 jbhwang 추가
	 * 트랜잭션의 commit 여부 설정
	 */
	public void setDoCommit(boolean doCommit) {
		if (!doCommit) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	protected void startTransaction() {
		definition.setName("ncomDefinition");
		resetPropagationBehavior();
		status = transactionManager.getTransaction(definition);
		doCommit = true;

	}

	protected void endTransaction() {

		endTransaction(doCommit);
	}

	protected void endTransaction(boolean doCommit) {
		try {
			if (doCommit)
				commitTransaction();
			else
				rollbackTransaction();
		} finally {
			rollbackTransaction();
			resetPropagationBehavior();
		}
	}

	protected void rollbackTransaction() {
		if (!status.isCompleted()) {
			transactionManager.rollback(status);
		}
	}

	protected void commitTransaction() {
		if (!status.isCompleted()) {
			transactionManager.commit(status);
		}
	}

	protected void resetPropagationBehavior() {
		definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	}

	protected void setParamInfo(Map<String, Object> param) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String userName = "";
		TmsUser userDetails = null;
		HashMap<String, Object> logMap = new HashMap<String, Object>();
		HashMap<String, Object> pMap = new HashMap<String, Object>();
		if (auth == null) {
			userName = "anonymousUser";
			logMap.put("sysMenuId", param.get("message"));
		} else {
			userDetails = (TmsUser) auth.getPrincipal();
			userName = userDetails.getUserId();
			logMap.put("sysMenuId", param.get("sysMenuId"));
		}

		String sysUserIp = IUtility.parseNull(IUtility.getRemoteAddr(), "");
		param.put("sysUserId", userName);
		param.put("sysUserIp", sysUserIp);

		pMap.put("url", IUtility.parseNull(IUtility.getUrl(), ""));
		pMap.put("param", param.toString());
		String content = pMap.toString();

		logMap.put("userId", userName);
		logMap.put("cnntBrwr", IUtility.parseNull(IUtility.getClientBrowser(), ""));
		logMap.put("cnntOps", IUtility.parseNull(IUtility.getClientOs(), ""));
		logMap.put("cnntIp", sysUserIp);
		logMap.put("useHisCntn", content);

		logMap.put("sysUserIp", sysUserIp);

		authDao.saveUserLog(logMap);

	}

	protected void setParamInfo1(Map<String, Object> param) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String userName = "";
		TmsUser userDetails = null;
		HashMap<String, Object> logMap = new HashMap<String, Object>();
		HashMap<String, Object> pMap = new HashMap<String, Object>();
		if (auth == null) {
			userName = "anonymousUser";
			logMap.put("sysMenuId", param.get("message"));
		} else {
			userDetails = (TmsUser) auth.getPrincipal();
			userName = userDetails.getUserId();
			logMap.put("sysMenuId", param.get("sysMenuId"));
		}

		String sysUserIp = IUtility.parseNull(IUtility.getRemoteAddr(), "");
		param.put("sysUserId", userName);
		param.put("sysUserIp", sysUserIp);

		pMap.put("url", IUtility.parseNull(IUtility.getUrl(), ""));
		// pMap.put("param", param.toString());
		// String content = pMap.toString();

		logMap.put("userId", userName);
		logMap.put("cnntBrwr", IUtility.parseNull(IUtility.getClientBrowser(), ""));
		logMap.put("cnntOps", IUtility.parseNull(IUtility.getClientOs(), ""));
		logMap.put("cnntIp", sysUserIp);
		// logMap.put("useHisCntn", content);

		logMap.put("sysUserIp", sysUserIp);

		authDao.saveUserLog(logMap);

	}
}
