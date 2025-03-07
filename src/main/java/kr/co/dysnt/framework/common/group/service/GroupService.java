package kr.co.dysnt.framework.common.group.service;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * Class Name: GroupService.java
 * Description: 권한 Service Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 03. 16.
 * @version 1.0
 * @see
 *
 */
public interface GroupService {

	List<EgovMap> searchUserList(Map<String, Object> param) throws Exception;

	List<EgovMap> searchGroupList(Map<String, Object> param) throws Exception;

	int deleteGroupList(Map<String, Object> param) throws Exception;

	int saveGroupList(List<Map<String, Object>> param) throws Exception;

	List<EgovMap> searchGroupUserList(Map<String, Object> param) throws Exception;

	int deleteGroupUserList(Map<String, Object> param) throws Exception;

	int saveGroupUserList(List<Map<String, Object>> param) throws Exception;

}
