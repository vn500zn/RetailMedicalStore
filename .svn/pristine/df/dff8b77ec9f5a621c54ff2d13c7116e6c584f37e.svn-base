/**
 * Copyright (c) 2016, Rms and/or its affiliates. All rights reserved.
 */
package com.rmswebservice.dao;

import java.util.List;

import com.rmsutil.domain.Medicine;
import com.rmsutil.domain.MedicineType;
import com.rmsutil.domain.SearchMedicineParams;
import com.rmsutil.domain.SearchMedicineResults;

/**
 * 
 * @author Sathish.Bandi
 * @since 11-03-2016
 * @version 1.0
 * @purpose the interface used to perform persistence operations MedicineMaster
 */
public interface MedicineMasterDAO {
	/**
	 * the method is used to add the medicine into db
	 * @param medicine
	 * @return Integer
	 */
public Long addMedicine(Medicine medicine);

	public boolean checkBatchNumber(String batchNumber);

	public List<String> medicineNameAutoComplete(String medicineName);
public List<MedicineType> getMedicinesTypes();

public List<SearchMedicineResults> searchMedicines(SearchMedicineParams searchMedicineParams);
}
