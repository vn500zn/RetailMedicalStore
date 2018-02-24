package com.rmswebservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rmswebservice.service.MedicalStoreService;

@RestController
public class MedicalStoreController {
	@Autowired
private MedicalStoreService medicalStoreService;
private static final String REST_REGISTER_MEDICAL_STORE="registerMedicalStore";
private static final String
REST_IS_REGISTERED_MEDICAL_STORE=
"isRegisteredMedicalStore/{regNumber}";
@ResponseBody
@RequestMapping(value=REST_IS_REGISTERED_MEDICAL_STORE,method=RequestMethod.GET)
public String 
isRegisteredMedicalStore(
		@PathVariable("regNumber") String regNumber){
	String jsonResponse=
	medicalStoreService.isRegisteredMedicalStore(regNumber);
	return jsonResponse;
}






@ResponseBody
@RequestMapping(value=REST_REGISTER_MEDICAL_STORE,method=RequestMethod.POST)
public String 
registerMedicalStore(@RequestBody String jsonMedicalStoreDTO){
	String jsonResponse=
medicalStoreService.registerMedicalStore(jsonMedicalStoreDTO);
	return jsonResponse;
}
}
