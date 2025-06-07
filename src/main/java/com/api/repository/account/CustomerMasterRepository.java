package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.entity.School;
import com.api.entity.account.CustomerMaster;

@Repository
public interface CustomerMasterRepository extends JpaRepository<CustomerMaster, Long> {

	List<CustomerMaster> findBySchoolUdise(School school);

	@Query(value = """
		    SELECT * FROM customer_master c
		    WHERE NOT EXISTS (
		        SELECT 1 FROM bank_master b WHERE b.cust_id = c.cust_id
		    )
		    AND c.school_udise = :udiseNo
		""", nativeQuery = true)
		List<CustomerMaster> findCustomersWithoutBankByUdiseNative( long udiseNo);
	
	@Query("SELECT c FROM CustomerMaster c WHERE c.custName = :custName AND c.schoolUdise.udiseNo = :udiseNo")
	CustomerMaster findCashInHandByUdise( String custName, long udiseNo);


}
