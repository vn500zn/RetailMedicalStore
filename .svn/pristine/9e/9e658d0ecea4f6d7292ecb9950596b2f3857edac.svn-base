package com.rmswebservice.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmsutil.domain.CustomerDTO;
import com.rmsutil.domain.Medicine;
import com.rmsutil.domain.MedicineType;
import com.rmsutil.domain.Response;
import com.rmsutil.domain.SearchMedicineParams;
import com.rmsutil.domain.SearchMedicineResults;
import com.rmsutil.util.JsonUtil;
import com.rmswebservice.dao.CustomerMasterDAO;
import com.rmswebservice.dao.MedicineMasterDAO;

@Service
public  class MedicineServiceImpl implements MedicineService{
private static Logger logger=LogManager.getLogger(MedicineServiceImpl.class);
	@Autowired
private MedicineMasterDAO medicineMasterDAO;
   @Autowired
	private CustomerMasterDAO customerMasterDAO;
	
	public String addMedicine(String jsonMedicine) {
	logger.info("Entered into addMedicine() ::"+jsonMedicine);
	Response response=new Response();
	response.setMessage("Medicine not added successfully!Please Try Again");
	try{
	Medicine medicine=JsonUtil.convertJsonToJava(jsonMedicine,Medicine.class);
	Long medicineId=medicineMasterDAO.addMedicine(medicine);
	if(medicineId!=null && medicineId>0){
		response.setMessage("Medicine added successfully");
		response.setStatus((byte)1);
		response.setData(medicineId.toString());
	}
	}catch(Exception e){
logger.error("Exception Occured while saving the medicine :: "+e.getMessage());
	}
	String jsonResponse=JsonUtil.convertJavaToJson(response);
	logger.info("result of addMedicine() ::"+jsonResponse);
	
	return jsonResponse;
	}
	
	
	
	public String checkBatchNumber(String batchNumber) {
		String jsonResponseStr="";
		if(batchNumber!=null &&
				batchNumber.trim().length()>0){
			logger.info("Entered into checkBatchNumber ::"+batchNumber);
Response response=new Response();
			boolean flag=medicineMasterDAO.checkBatchNumber(batchNumber);
			if(flag==true){
		response.setStatus((byte)1);
		response.setMessage("Medicine already existed.Please Update the Medicine ");
			}
			else{
		response.setStatus((byte)0);
		response.setMessage("Medicine not existed ");
			}
	jsonResponseStr=JsonUtil.convertJavaToJson(response);
		logger.info("response of checkBatchNumber ::"+jsonResponseStr);
		}
		return jsonResponseStr;
	}
public String medicineNameAutoComplete
(String medicineName) {
	String jsonMedicineNamesList="";	
	if(medicineName!=null && medicineName.trim().length()>0){
logger.info("Entered into MedicineNameAutoComplete ::"+medicineName);
		List<String> medicineNamesList=medicineMasterDAO.medicineNameAutoComplete(medicineName);
		jsonMedicineNamesList=JsonUtil.convertJavaToJson(medicineNamesList);
	logger.info("response of Medicine Names Autocomplete ::"+jsonMedicineNamesList);
	}
		return jsonMedicineNamesList;
	}



@Override
public String getMedicineTypes() {
	  List<MedicineType> medicineTypesList=medicineMasterDAO.getMedicinesTypes();
String jsonMedicineTypes=JsonUtil.convertJavaToJson(medicineTypesList);
logger.info("response of getMedicineTypes ::"+jsonMedicineTypes);
return jsonMedicineTypes;
}

public String searchMedicines
(String jsonSearchMedicineParams) {
	
logger.info("Entered into search Medicines ::"+jsonSearchMedicineParams);
     SearchMedicineParams searchMedicineParams=JsonUtil.convertJsonToJava(jsonSearchMedicineParams,SearchMedicineParams.class);
List<SearchMedicineResults> searchMedicineResultsList
=medicineMasterDAO.searchMedicines(searchMedicineParams);
String jsonSearchMedicineResultsList=
JsonUtil.convertJavaToJson(searchMedicineResultsList);
logger.info("Response of SearchMedicines ::"+searchMedicineResultsList);	
return jsonSearchMedicineResultsList;
}

public String addCustomer(String jsonCustomerDTO) {
	logger.info("Entered into addCustomer() ::"+jsonCustomerDTO);
	Response response=new Response();
	response.setMessage("Customer not added successfully!Please Try Again");
	try{
CustomerDTO customerDTO=JsonUtil.convertJsonToJava(jsonCustomerDTO,CustomerDTO.class);
	Long customerId=customerMasterDAO.addCustomer(customerDTO);
	
	if(customerId!=null && customerId>0){
		response.setMessage("Customer added successfully");
		response.setStatus((byte)1);
		response.setData(customerId.toString());
	}
	}catch(Exception e){
logger.error("Exception Occured while saving the customer:: "+e.getMessage());
	}
	String jsonResponse=JsonUtil.convertJavaToJson(response);
	logger.info("result of addCustomer() ::"+jsonResponse);
	
	return jsonResponse;
}




public String searchCustomer(String mobile, Integer medicalStoreId) {
CustomerDTO customerDTO=null;
logger.info("Entered into search customer ::"+mobile+" "+medicalStoreId);
if(mobile!=null && mobile.trim().length()>0 && medicalStoreId!=null && medicalStoreId>0){
	customerDTO=customerMasterDAO.searchCustomer(mobile,medicalStoreId);
}
String jsonCustomerDTO=JsonUtil.convertJavaToJson(customerDTO);
logger.info("response of SearchCustomer ::"+jsonCustomerDTO);
return jsonCustomerDTO;
}
}






/**
 * to convert java object into json and
 * json into java object different vendor's are 
 * given implementations
 * Jackson-API
 * GSON-API
 * Simple JSON API
 * 
 * Jackson-API
 * ------------
 * two versions 1.x and 2.x version 
 * to convert java object into json and json into java obj
 * we can use ObjectMapper class method from JacksonAPI
 * 
 * writeValueAsString(-):-
 * this method is used to convert java object into json str
 * readValue(-):-
 * this method is used to convert json into java object
 * 
 */





