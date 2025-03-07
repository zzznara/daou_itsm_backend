package kr.co.dysnt.framework.resultinquery.interfacelist.service;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * Class Name: InterfaceService.java
 * Description: 샘플 Service Class
 * </pre>
 *
 * @author 장태준
 * @since 2024. 08. 23.
 * @version 1.0
 * @see
 *
 */
public interface InterfaceService {

	List<EgovMap> searchInterfaceList(Map<String, Object> param) throws Exception;

}
