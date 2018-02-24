package com.rmswebservice.service;

public interface MedicalStoreService {

	String registerMedicalStore(String jsonMedicalStoreDTO );

	String isRegisteredMedicalStore(String regNumber);

}
