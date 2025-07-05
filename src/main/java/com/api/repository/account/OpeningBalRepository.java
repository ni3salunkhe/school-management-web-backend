package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.entity.School;
import com.api.entity.account.CustomerMaster;
import com.api.entity.account.OpeningBal;

@Repository
public interface OpeningBalRepository extends JpaRepository<OpeningBal, Long> {

	@Query("SELECT SUM(o.drAmt), SUM(o.crAmt) FROM OpeningBal o WHERE o.subHeadId.subheadName NOT IN ('Opening Balance', 'Current Period')")
	Object getTotalCreditAndDebit();

	@Query("SELECT SUM(o.drAmt), SUM(o.crAmt) FROM OpeningBal o " + "WHERE o.schoolUdise.udiseNo = :udiseNumber "
			+ "AND o.subHeadId.subheadName NOT IN ('Opening Balance', 'Current Period')")
	Object getTotalCreditAndDebitByUdise(long udiseNumber);

	OpeningBal findBySubHeadIdSubheadNameAndSchoolUdiseUdiseNo(String headName, long udise);

	List<OpeningBal> findByHeadIdHeadNameAndSchoolUdise(String headName, School udise);
	
	List<OpeningBal> findBySchoolUdiseUdiseNo(long udiseNo);
}
