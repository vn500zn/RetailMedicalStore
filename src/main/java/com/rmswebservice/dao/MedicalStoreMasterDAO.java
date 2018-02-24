/**
 * Copyright (c) 2016, Rms and/or its affiliates. All rights reserved.
 */
package com.rmswebservice.dao;

import com.rmsutil.domain.MedicalStoreDTO;

/**
 * 
 * @author Sathish.Bandi
 * @since 11-03-2016
 * @version 1.0
 * @purpose the interface used to perform persistence operations MedicalStoreMaster
 */
public interface MedicalStoreMasterDAO {

	Integer registerMedicalStore(MedicalStoreDTO medicalStoreDTO);

	boolean isRegisteredMedicalStore(String regNumber);

}
