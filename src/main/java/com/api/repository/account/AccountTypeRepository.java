package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.School;
import com.api.entity.account.AccountType;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
	List<AccountType> findBySchoolUdiseNo(School udiseNo);
}
