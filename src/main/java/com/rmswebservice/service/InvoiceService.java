package com.rmswebservice.service;

public interface InvoiceService {

	String saveCustomerPurchages(String jsonsetOfMedicines, Long customerId, Long createdBy, Integer medicalStoreId);

}
