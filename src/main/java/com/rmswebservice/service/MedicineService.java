package com.rmswebservice.service;
public interface MedicineService {
public String addMedicine(String jsonMedicine);

public String checkBatchNumber(String batchNumber);

public String medicineNameAutoComplete(String medicineName);

public String getMedicineTypes();

public String searchMedicines(String jsonSearchMedicineParams);

public String addCustomer(String jsonCustomerDTO);

public String searchCustomer(String mobile, Integer medicalStoreId);
}
