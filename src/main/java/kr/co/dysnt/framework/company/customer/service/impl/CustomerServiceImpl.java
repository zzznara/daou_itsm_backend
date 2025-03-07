package kr.co.dysnt.framework.company.customer.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.company.customer.dao.CustomerDao;
import kr.co.dysnt.framework.company.customer.service.CustomerService;
import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;

/**
 * <pre>
 * Class Name: CustomerServiceImpl.java
 * Description: 관리 ServiceImpl
 * </pre>
 *
 * @author 전현우
 * @since 2022. 01. 26.
 * @version 1.0
 * @see
 */
@Service("CustomerService")
public class CustomerServiceImpl extends CommonServiceImpl implements CustomerService {

	@Resource(name = "customerDao")
	private CustomerDao customerDao;

	@Override
	public List<EgovMap> searchCustomerList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return customerDao.searchCustomerList(param);
	}

	@Override
	public List<EgovMap> searchMidVendorList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return customerDao.searchMidVendorList(param);
	}
}
