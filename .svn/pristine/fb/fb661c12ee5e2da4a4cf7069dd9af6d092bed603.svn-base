package com.rmswebservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rmswebservice.service.MedicineService;

@RestController
public class MedicineController {
	@Autowired
	private MedicineService medicineService;

	private static final String SERVICE_CHECK_BATCH_NUMBER = "checkBatchNumber/{batchNumber}";
   private static final String SERVICE_MEDICINE_NAME_AUTO_COMPLETE="medicineNameAutoComplete/{medicineName}";
	
   private static final String SERVICE_GET_MEDICINE_TYPES="getMedicineTypes";
   private static final String SERVICE_SEARCH_MEDICINES="searchMedicines";
   private static final String SERVICE_ADD_CUSTOMER="addCustomer";
   private static final String SERVICE_SEARCH_CUSTOMER="searchCustomer/{mobile}/{medicalStoreId}";
   
   
	@ResponseBody
	@RequestMapping(value = "addMedicine", method = RequestMethod.POST)
	public String addMedicine(@RequestBody String jsonMedicineStr) {
		String jsonResponseStr = medicineService.addMedicine(jsonMedicineStr);
		return jsonResponseStr;
	}

	@ResponseBody
	@RequestMapping(value = SERVICE_CHECK_BATCH_NUMBER, method = RequestMethod.GET)
	public String checkBatchNumber(@PathVariable("batchNumber") String batchNumber) {

		String jsonResponseStr = medicineService.checkBatchNumber(batchNumber);
		return jsonResponseStr;
	}
	@ResponseBody
	@RequestMapping(value=SERVICE_MEDICINE_NAME_AUTO_COMPLETE,method=RequestMethod.GET)
	public String medicineNameAutoComplete(@PathVariable("medicineName") String medicineName){
		
		String jsonMedicineNamesList=medicineService.medicineNameAutoComplete(medicineName);
		return jsonMedicineNamesList;
	}
	
	@ResponseBody
	@RequestMapping(value=SERVICE_GET_MEDICINE_TYPES,method=RequestMethod.GET)
	public String getMedicineTypes(){
String jsonMedicineTypes=medicineService.getMedicineTypes();
		return jsonMedicineTypes;
	}
	@ResponseBody
	@RequestMapping(value =SERVICE_SEARCH_MEDICINES, method = RequestMethod.POST)
	public String searchMedicines(@RequestBody String jsonSearchMedicineParams) {
		String jsonSearchMedicineResultsList = medicineService.searchMedicines(jsonSearchMedicineParams);
		return jsonSearchMedicineResultsList;
	}
	@ResponseBody
	@RequestMapping(value=SERVICE_ADD_CUSTOMER,method=RequestMethod.POST)
public String addCustomer(@RequestBody String jsonCustomerDTO){
	  String jsonResponse=medicineService.addCustomer(jsonCustomerDTO);
	  return jsonResponse;
			  
}
	@ResponseBody
	@RequestMapping(value=SERVICE_SEARCH_CUSTOMER,method=RequestMethod.GET)
	public String searchCustomer(
	@PathVariable("mobile") String mobile
   ,@PathVariable("medicalStoreId") Integer medicalStoreId){
		String jsonCustomerDTO=medicineService.searchCustomer(mobile,medicalStoreId);
		 return jsonCustomerDTO;
		
	}
}



