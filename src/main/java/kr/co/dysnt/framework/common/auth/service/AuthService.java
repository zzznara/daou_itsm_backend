package kr.co.dysnt.framework.common.auth.service;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * Class Name: AuthService.java
 * Description: 권한 Service Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 03. 14.
 * @version 1.0
 * @see
 *
 */
public interface AuthService {

	List<EgovMap> searchAuthList(Map<String, Object> param) throws Exception;

	int deleteAuthList(Map<String, Object> param) throws Exception;

	int saveAuthList(List<Map<String, Object>> param) throws Exception;

	List<EgovMap> searchAuthMenuList(Map<String, Object> param) throws Exception;

	int deleteAuthMenuList(Map<String, Object> param) throws Exception;

	int saveAuthMenuList(List<Map<String, Object>> param) throws Exception;

	List<EgovMap> searchAuthGroupList(Map<String, Object> param) throws Exception;

	int deleteAuthGroupList(Map<String, Object> param) throws Exception;

	int saveAuthGroupList(List<Map<String, Object>> param) throws Exception;
}
