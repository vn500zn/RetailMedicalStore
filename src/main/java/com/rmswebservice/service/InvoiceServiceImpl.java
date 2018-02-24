package com.rmswebservice.service;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmsutil.domain.InvoiceDTO;
import com.rmsutil.domain.Response;
import com.rmsutil.util.JsonUtil;

import com.rmswebservice.dao.InvoiceMasterDAO;

@Service
public class InvoiceServiceImpl implements InvoiceService{
	 private static Logger logger=LogManager.getLogger(InvoiceServiceImpl.class);

	@Autowired
	private InvoiceMasterDAO invoiceMasterDAO;
   public String saveCustomerPurchages(
		String jsonsetOfMedicines, 
		Long customerId, Long createdBy,
			Integer medicalStoreId) {
	logger.info("Entered into saveCustomerPurchages() ::"+jsonsetOfMedicines+" "+customerId+" "+medicalStoreId+" "+createdBy);
Response response=new Response();
response.setMessage("Invoice could not be generated");	
Set<InvoiceDTO> set=JsonUtil.convertJsonToSet(jsonsetOfMedicines,InvoiceDTO.class);
	Long billNum=invoiceMasterDAO.saveCustomerPurchages(set,customerId,createdBy,medicalStoreId);
if(billNum!=null && billNum>0){	 
	response.setStatus((byte)1);
	response.setMessage("Invoice Generated Successfully");
	response.setData(billNum.toString());
}
String jsonResponse=JsonUtil.convertJavaToJson(response);
logger.info("Response of saveCustomerPurchages() ::"+jsonResponse);	
return jsonResponse;
	}

}
