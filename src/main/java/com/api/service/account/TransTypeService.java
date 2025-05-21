package com.api.service.account;

import java.util.List;

import com.api.entity.account.TransType;

public interface TransTypeService {

	public TransType post(TransType transType);
	
	public List<TransType> getbyudiseno(long udiseno);
	
	public void deletebyid(int id);
	
	public List<TransType> getdata();
}
