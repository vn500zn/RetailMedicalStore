package com.rmswebservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rmswebservice.service.InvoiceService;

@RestController
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;
private static final String REST_SAVE_CUSTOMER_PURCHAGES="saveCustomerPurchages/{customerId}/{createdBy}/{medicalStoreId}";
@RequestMapping(value=REST_SAVE_CUSTOMER_PURCHAGES,method=RequestMethod.POST)
@ResponseBody
public String saveCustomerPurchages(
@PathVariable("customerId") Long customerId,
@PathVariable("medicalStoreId") Integer medicalStoreId,
@PathVariable("createdBy") Long createdBy,
@RequestBody String JsonsetOfMedicines){
String jsonResponse=
invoiceService.saveCustomerPurchages(JsonsetOfMedicines,customerId,createdBy,medicalStoreId);
	
	return jsonResponse;
}
}
