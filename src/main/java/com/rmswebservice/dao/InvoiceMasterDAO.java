package com.rmswebservice.dao;

import java.util.Set;

import com.rmsutil.domain.InvoiceDTO;

public interface InvoiceMasterDAO {

	Long saveCustomerPurchages(Set<InvoiceDTO> set, Long customerId, Long createdBy, Integer medicalStoreId);

}
