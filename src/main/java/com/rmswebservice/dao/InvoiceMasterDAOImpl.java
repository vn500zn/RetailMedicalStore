package com.rmswebservice.dao;
import java.util.Date;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rmsutil.domain.Invoice;
import com.rmsutil.domain.InvoiceDTO;
@Repository
public class InvoiceMasterDAOImpl implements InvoiceMasterDAO{
    @Autowired
	private SessionFactory sessionFactory;

public Long saveCustomerPurchages
(Set<InvoiceDTO> set, Long customerId,
Long createdBy, Integer medicalStoreId) {
	Long billNum=null;
	int count=0;
	Transaction  tx=null;
	Session session=null;
	try{ 
	 session=sessionFactory.openSession();
	    tx=session.beginTransaction();
	   
	/*Invoice invoice=new Invoice();
	invoice.setCreatedBy(createdBy);
    invoice.setDateOfPurchage(new Date());
    billNum=(Long)session.save(invoice);*/
String HQL_BILL_NUM="select max(i.billNum) from com.rmsutil.domain.Invoice as i";
Query query=session.createQuery(HQL_BILL_NUM);
billNum=(Long)query.uniqueResult();
   if(billNum ==null || billNum==0L){
	   billNum=1L;
   }
   else{
	   billNum=billNum+1;
   }
 String SQL_SAVE_INVOICE="insert into Rms.InvoiceMaster values(?,?,?)";
 SQLQuery sqlQuery=session.createSQLQuery(SQL_SAVE_INVOICE);
 sqlQuery.setParameter(0,billNum);
 sqlQuery.setParameter(1,new Date());
 sqlQuery.setParameter(2, createdBy);
 count=sqlQuery.executeUpdate();
   if(billNum!=null && billNum>0 && count>0){
	 
	   for(InvoiceDTO invoiceDTO:set){
String SQL_SAVE_CUSTOMER_PURCHAGES="insert into Rms.CustomerPurchages values(:billNum,:customerId,:medicalStoreId,:medicineId,:rate,:quantity)";
sqlQuery=session.createSQLQuery(SQL_SAVE_CUSTOMER_PURCHAGES);
sqlQuery.setParameter("billNum",billNum);
sqlQuery.setParameter("customerId",customerId);
sqlQuery.setParameter("medicalStoreId",medicalStoreId);
sqlQuery.setParameter("medicineId",invoiceDTO.getMedicineId());
sqlQuery.setParameter("rate",invoiceDTO.getRate());
sqlQuery.setParameter("quantity",invoiceDTO.getQuantity());
   count=sqlQuery.executeUpdate();
    if(count>0){
String HQL_GET_STOCKID="select s.stockId from com.rmsutil.domain.Medicine as m Inner Join m.stock as s where m.medicineId=?";
 query=session.createQuery(HQL_GET_STOCKID);
 query.setParameter(0,invoiceDTO.getMedicineId());
    Long stockId=(Long)query.uniqueResult();
  String HQL_UPDATE_STOCK="update com.rmsutil.domain.Stock as s set s.stock=s.stock-?,s.lastModifiedBy=?,s.lastModifiedDate=? where s.stockId=?";
   query=session.createQuery(HQL_UPDATE_STOCK);
   query.setParameter(0,invoiceDTO.getQuantity());
   query.setParameter(1,createdBy);
   query.setParameter(2,new Date());
   query.setParameter(3,stockId);
   count=query.executeUpdate();
   
    }
	 }
   }
   if(count==0){
	   billNum=0L;
	   tx.rollback();
   }else{
  tx.commit();
   }}
   catch(Exception e){
	  if(tx!=null){ 
		  tx.rollback();
		  billNum=0L;
	  }
	  if(session!=null){
		  session.close();
	  }
   }
	
		return billNum;
	}
}
