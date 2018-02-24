package com.rmswebservice.service;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmsutil.domain.EmployeeMaster;
import com.rmsutil.domain.MedicalStore;
import com.rmsutil.domain.MedicalStoreDTO;
import com.rmsutil.domain.Response;
import com.rmsutil.domain.UserAuthentication;
import com.rmsutil.util.JsonUtil;
import com.rmswebservice.dao.MedicalStoreMasterDAO;

@Service
public class MedicalStoreServiceImpl implements MedicalStoreService{
private static Logger logger=LogManager.getLogger(MedicalStoreServiceImpl.class);
 @Autowired
private MedicalStoreMasterDAO medicalStoreMasterDAO;
@Autowired
 private UserService userService;

public String
registerMedicalStore(String jsonMedicalStoreDTO) {
	logger.info("Entered into registerMedicalStore ::"+jsonMedicalStoreDTO);
	
	Response response=new Response();	
	MedicalStoreDTO medicalStoreDTO=JsonUtil.convertJsonToJava(jsonMedicalStoreDTO, MedicalStoreDTO.class);

medicalStoreDTO.setPassword(medicalStoreDTO.getUserName().substring(0,2)+new Random().nextInt(1000000));
       Integer	medicalStoreId=medicalStoreMasterDAO.registerMedicalStore(medicalStoreDTO);
	if(medicalStoreId!=null && medicalStoreId>0){
		response.setData(medicalStoreId.toString());
		response.setMessage("MedicalStore Registered successfully .And UserName,Password Sent to Register Mobile.");
		response.setStatus((byte)1);
		String message="Dear :"+medicalStoreDTO.getMedicalStoreName()+" Your Registration Successfully completed And You can login with  this credendionals in future , UserName="+medicalStoreDTO.getUserName()+" password = "+medicalStoreDTO.getPassword()+"";
		
		userService.sendSms(message,medicalStoreDTO.getPhoneNumber1());
	}
	else{
	response.setMessage("MedicalStore Registration is failure.Please Try Again");
	}
	String jsonResponseStr=JsonUtil.convertJavaToJson(response);
	logger.info("Response of registerMedicalStore ::"+jsonResponseStr);
			return jsonResponseStr;
}


public String isRegisteredMedicalStore(String regNumber) {
	Response response=new Response();
	boolean flag=medicalStoreMasterDAO.isRegisteredMedicalStore(regNumber);
	if(flag==true){
		response.setStatus((byte)1);
		response.setMessage("MedicalStore Already Registered");
	}
	String jsonResponse=JsonUtil.convertJavaToJson(response);
	logger.info("Response of isRegisteredMedicalStore ::"+jsonResponse);
	return jsonResponse;
}

}
