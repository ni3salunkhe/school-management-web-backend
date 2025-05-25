package com.api.service.account;

import java.util.List;

import com.api.entity.account.AccountType;

public interface AccountTypeService {
	public AccountType post(AccountType accountType);
	
	public List<AccountType> getbyudiseno(long udiseNo);
	
	public AccountType getbyid(int id);
}
