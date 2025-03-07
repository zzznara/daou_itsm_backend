package kr.co.dysnt.framework.common.menu.service;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * Class Name: MenuService.java
 * Description: 샘플 Service Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 02. 25.
 * @version 1.0
 * @see
 *
 */
public interface MenuService {

	List<EgovMap> searchMenuList(Map<String, Object> param) throws Exception;

	void deleteMenuList(Map<String, Object> param) throws Exception;

	int saveMenuList(List<Map<String, Object>> param) throws Exception;

	List<EgovMap> searchUserMenuList(Map<String, Object> param) throws Exception;

	List<EgovMap> searchMenuAuthInfo(Map<String, Object> param) throws Exception;

}
