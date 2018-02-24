package com.rmswebservice.dao;

import java.util.List;

import javassist.tools.framedump;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rmsutil.domain.Customer;
import com.rmsutil.domain.CustomerDTO;
import com.rmsutil.domain.MedicalStore;
import com.rmsutil.util.HQLConstants;
@Repository
@Transactional(readOnly=false)
public class CustomerMasterDAOImpl implements CustomerMasterDAO{
    @Autowired
	private HibernateTemplate hibernateTemplate;

	
	public Long addCustomer(CustomerDTO customerDTO) {
	Customer customer=new Customer();
	customer.setCustomerName(customerDTO.getCustomerName());
	customer.setMobile(customerDTO.getMobile());
	customer.setCity(customerDTO.getCity());
	customer.setState(customerDTO.getState());
	MedicalStore medicalStore=new MedicalStore();
	medicalStore.setMedicalStoreId(customerDTO.getMedicalStoreId());
	customer.setMedicalStore(medicalStore);
	  Long customerId=(Long)hibernateTemplate.save(customer);
	  
	return customerId;
	}


public CustomerDTO 
searchCustomer(String mobile, Integer medicalStoreId) {
CustomerDTO customerDTO=null;
	List<?> list=hibernateTemplate.find(HQLConstants.HQL_SEARCH_CUSTOMER,mobile,medicalStoreId);
   if(list!=null && list.size()>0){
	Object[] obj=(Object[])list.get(0);
	 customerDTO=new CustomerDTO();
	 customerDTO.setCustomerId((Long)obj[0]);
	 customerDTO.setCustomerName((String)obj[1]);
	 customerDTO.setCity((String)obj[2]);
	 customerDTO.setState((String)obj[3]);
	 customerDTO.setMobile(mobile);
   }
		return customerDTO;
	}

}
