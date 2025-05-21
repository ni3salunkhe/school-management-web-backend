package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.School;
import com.api.entity.account.EntryType;

public interface EntryTypeRepository extends JpaRepository<EntryType, Long> {
	List<EntryType> findBySchoolUdiseNo(School udiseNo);
}
