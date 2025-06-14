package com.api.service.account;

import java.util.List;

import com.api.entity.School;
import com.api.entity.account.ContraPayment;

public interface ContraPaymentService {

	ContraPayment post(ContraPayment contraPayment);
	
	List<ContraPayment> getbyudise(School udiseNo);
	
	ContraPayment getbyid(long id);
}
