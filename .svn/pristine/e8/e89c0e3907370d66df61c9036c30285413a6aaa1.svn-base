/**
 * Copyright (c) 2016, Rms and/or its affiliates. All rights reserved.
 */
package com.rmswebservice.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rmsutil.domain.EmployeeMaster;
import com.rmsutil.domain.MedicalStore;
import com.rmsutil.domain.MedicalStoreDTO;
import com.rmsutil.domain.Roles;
import com.rmsutil.domain.UserAuthentication;
import com.rmsutil.util.HQLConstants;
/**
 * 
 * @author Sathish.Bandi
 * @since 11-03-2016
 * @version 1.0
 * @purpose the class used to perform persistence operations MedicalStoreMaster
 */
@Repository
@Transactional(readOnly=false)
public class MedicalStoreMasterDAOImpl
implements MedicalStoreMasterDAO {
	@Autowired
private HibernateTemplate hibernateTemplate;
	@Autowired
	private SessionFactory sessionFactory;
	public Integer registerMedicalStore(
			MedicalStoreDTO medicalStoreDTO) {
	MedicalStore medicalStore=new 
			MedicalStore();
medicalStore.setMedicalStoreName(medicalStoreDTO.getMedicalStoreName());
medicalStore.setAddressLine1(medicalStoreDTO.getAddressLine1());
medicalStore.setAddressLine2(medicalStoreDTO.getAddressLine2());
medicalStore.setCity(medicalStoreDTO.getCity());
medicalStore.setCountry(medicalStoreDTO.getCountry());
medicalStore.setCreatedBy(medicalStoreDTO.getCreatedBy());
medicalStore.setCreatedDate(medicalStoreDTO.getCreatedDate());
medicalStore.setEmailId(medicalStoreDTO.getEmailId());
medicalStore.setPhoneNumber1(medicalStoreDTO.getPhoneNumber1());
medicalStore.setPhoneNumber2(medicalStoreDTO.getPhoneNumber2());
medicalStore.setRegistrationNumber(medicalStoreDTO.getRegistrationNumber());
medicalStore.setState(medicalStoreDTO.getState());
medicalStore.setZipcode(medicalStoreDTO.getZipcode());

Roles roles=new Roles();
roles.setUserRoleId(medicalStoreDTO.getUserRoleId());
UserAuthentication userAuthen=new
UserAuthentication();
userAuthen.setPassword(medicalStoreDTO.getPassword());
userAuthen.setUserName(medicalStoreDTO.getUserName());
userAuthen.setRoles(roles);

EmployeeMaster empMaster=new EmployeeMaster();
empMaster.setMedicalStore(medicalStore);
empMaster.setRoles(roles);
empMaster.setUserAuthentication(userAuthen);
empMaster.setCreatedBy(medicalStoreDTO.getCreatedBy());
empMaster.setCreatedDate(medicalStoreDTO.getCreatedDate());

Integer medicalStoreId=(Integer)hibernateTemplate.save(medicalStore);
	if(medicalStoreId!=null && medicalStoreId>0){	
		Long userId=(Long)hibernateTemplate.save(userAuthen);
		if(userId!=null && userId>0){
			userId=(Long)hibernateTemplate.save(empMaster);
		}
	
		
	}
	return medicalStoreId;
	}
	
	public boolean 
	isRegisteredMedicalStore(String regNumber) {
		boolean flag=false;
	Session session=sessionFactory.openSession();
	Query query=session.createQuery(HQLConstants.HQL_IS_REGSITERED_MEDICAL_STORE);
	query.setParameter(0, regNumber);
	Long count=(Long)query.uniqueResult();
	
	if(count!=null && count>0){
		flag=true;
	}
	session.close();
		return flag;
	}
	
}
