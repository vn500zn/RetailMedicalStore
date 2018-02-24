/**
 * Copyright (c) 2016, Rms and/or its affiliates. All rights reserved.
 */
package com.rmswebservice.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.JoinTable;

import javassist.CtBehavior;
import javassist.tools.framedump;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.descriptor.java.JavaTypeDescriptorRegistry.FallbackJavaTypeDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rmsutil.domain.Medicine;
import com.rmsutil.domain.MedicineType;
import com.rmsutil.domain.SearchMedicineParams;
import com.rmsutil.domain.SearchMedicineResults;
import com.rmsutil.util.HQLConstants;
/**
 * 
 * @author Sathish.Bandi
 * @since 11-03-2016
 * @version 1.0
 * @purpose the class used to perform persistence operations MedicineMaster
 */
@Repository
@Transactional(readOnly=false)
public class MedicineMasterDAOImpl
implements MedicineMasterDAO {
	@Autowired
private SessionFactory sessionFactory;
	@Autowired

private HibernateTemplate hibernateTemplate;
	/**the method is used to add the medicine into db
	 * @param medicine
	 * @return Integer
	 */
	public Long addMedicine(Medicine medicine) {
          Long medicineId=(Long)hibernateTemplate.save(medicine);
		return medicineId;
	}

	
	public boolean checkBatchNumber(String batchNumber) {
boolean flag=false;
		Session session=sessionFactory.openSession();
	Query query=session.createQuery(HQLConstants.HQL_CHECK_BATCH_NUMBER);
	query.setParameter(0,batchNumber.toUpperCase());
	Long count=(Long)query.uniqueResult();

	if(count!=null && count>0){
		flag=true;
	}
	session.close();
		return flag;
	}


public List<String> 
medicineNameAutoComplete(String medicineName) {
Session session=sessionFactory.openSession();	
	Query query=session.createQuery(HQLConstants.HQL_MEDICINE_NAME_AUTO_COMPLETE);
	query.setParameter(0, medicineName.toUpperCase()+"%");
	List<String> medicineNamesList=query.list();
session.close();
		return medicineNamesList;
	}


@Override
public List<MedicineType> getMedicinesTypes() {
	List<MedicineType> medicineTypesList=
			(List<MedicineType>) hibernateTemplate.find(HQLConstants.HQL_GET_MEDICINE_TYPES);
	
	return medicineTypesList;
}



public List<SearchMedicineResults> searchMedicines
(SearchMedicineParams searchMedicineParams) {
	List<SearchMedicineResults> searchMedicineResultsList=
			new ArrayList<SearchMedicineResults>();
	
Session session=sessionFactory.openSession();
Criteria criteria=session.createCriteria(Medicine.class);
criteria.createAlias("stock","stocks");
criteria.createAlias("medicineType","medicineTypes");
criteria.createAlias("medicalStore","medicalStore");
Projection medicineIdProj=Projections.property("medicineId");
Projection rateProj=Projections.property("rate");
Projection expDateProj=Projections.property("expDate");
Projection mfgDateProj=Projections.property("mfgDate");
Projection mfgByProj=Projections.property("mfgBy");
Projection stockProj=Projections.property("stocks.stock");
Projection dosageProj=Projections.property("dosage");
Projection batchNumberProj=Projections.property("batchNumber");
Projection medicineTypeProj=Projections.property("medicineTypes.medicineType");

ProjectionList plist=Projections.projectionList();
plist.add(medicineIdProj);
plist.add(rateProj);
plist.add(expDateProj);
plist.add(mfgDateProj);
plist.add(mfgByProj);
plist.add(stockProj);
plist.add(dosageProj);
plist.add(batchNumberProj);
plist.add(medicineTypeProj);

criteria.setProjection(plist);

Criterion medicineNameCri=Restrictions.ilike
("medicineName",searchMedicineParams.getMedicineName());

Criterion medicineTypeIdCri=Restrictions.eq("medicineTypes.medicineTypeId", searchMedicineParams.getMedicineTypeId());

Criterion medicialStoreIdCri=
Restrictions.eq("medicalStore.medicalStoreId",searchMedicineParams.getMedicalStoreId());

Criterion expDateCri=Restrictions.ge("expDate",new Date());

criteria.add(medicineNameCri);
criteria.add(medicialStoreIdCri);
criteria.add(medicineTypeIdCri);
criteria.add(expDateCri);
criteria.addOrder(Order.asc("expDate"));
 List<Object[]> list=criteria.list();
 for(Object[] obj:list){
SearchMedicineResults medicineResults=new SearchMedicineResults();    
	 medicineResults.setMedicineId((Long)obj[0]);
	 medicineResults.setRate((Double)obj[1]);
     medicineResults.setExpDate((Date)obj[2]);
     medicineResults.setMfgDate((Date)obj[3]);
     medicineResults.setMfgBy((String)obj[4]);
     medicineResults.setStock((Integer)obj[5]);
     medicineResults.setDosage((String)obj[6]);
     medicineResults.setBatchNumber((String)obj[7]);
     medicineResults.setMedicineType((String)obj[8]);
     medicineResults.setMedicineName(searchMedicineParams.getMedicineName());
    medicineResults.setMedicineTypeId(searchMedicineParams.getMedicineTypeId());
    searchMedicineResultsList.add(medicineResults);
 }


session.close();
	return searchMedicineResultsList;
}	
}














