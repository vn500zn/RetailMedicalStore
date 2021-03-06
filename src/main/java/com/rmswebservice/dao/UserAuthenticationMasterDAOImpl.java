package com.rmswebservice.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rmsutil.domain.MedicalStore;
import com.rmsutil.domain.Roles;
import com.rmsutil.domain.UserAuthentication;
import com.rmsutil.util.HQLConstants;
@Repository
public class UserAuthenticationMasterDAOImpl implements UserAuthenticationMasterDAO{
    @Autowired
	private SessionFactory sessionFactory;

	public UserAuthentication login(UserAuthentication userAuthentication) {
  Session session=sessionFactory.openSession();
  Query query=session.createQuery(HQLConstants.HQL_LOGIN);
  query.setParameter(0,userAuthentication.getUserName());
  query.setParameter(1,userAuthentication.getPassword());
  Object[] obj=(Object[])query.uniqueResult();
  if(obj!=null && obj.length>0){
	  userAuthentication.setPassword(null);
	  userAuthentication.setUserId((Long)obj[0]);
	  Roles roles=new Roles();
	  roles.setUserRoleId((Integer)obj[1]);
      roles.setUserRole((String)obj[2]);
      userAuthentication.setRoles(roles);
  String hql="select m.medicalStoreId,m.medicalStoreName,m.phoneNumber1,m.emailId,m.addressLine1,m.city,m.state from com.rmsutil.domain.EmployeeMaster as e inner join e.medicalStore as m where e.empId=?";
  query=session.createQuery(hql);
  query.setParameter(0,userAuthentication.getUserId());
  obj=(Object[])query.uniqueResult();
  System.out.println(obj);
  if(obj!=null && obj.length>0){
	  System.out.println(obj[0]);
  MedicalStore medicalStore=new MedicalStore();
  medicalStore.setMedicalStoreId((Integer)obj[0]);
  medicalStore.setMedicalStoreName((String)obj[1]);
  medicalStore.setPhoneNumber1((String)obj[2]);
  medicalStore.setEmailId((String)obj[3]);
  medicalStore.setAddressLine1((String)obj[4]);
  medicalStore.setCity((String)obj[5]);
  medicalStore.setState((String)obj[6]);
  userAuthentication.setMedicalStore(medicalStore);
  }
}
		return userAuthentication;
	}

	@Override
	public int changePassword(String userName, 
			String oldPassword, 
			String newPassword) {
		int count=0;
String HQL_CHANGE_PASSWORD="update com.rmsutil.domain.UserAuthentication as u set u.password=:newPass where u.userName=:userName and u.password=:oldPass";
Session session=sessionFactory.openSession();
Query query=session.createQuery(HQL_CHANGE_PASSWORD);
Transaction tx=session.beginTransaction();
query.setParameter("newPass",newPassword);
query.setParameter("userName",userName);
query.setParameter("oldPass",oldPassword);
  count=query.executeUpdate();
  tx.commit();
  session.close();
		return count;
	}
	

}
