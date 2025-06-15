package com.api.repository.account;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.dto.account.GeneralLedgerDto;
import com.api.entity.School;
import com.api.entity.account.GeneralLedger;
import com.api.entity.account.HeadMaster;
import com.api.entity.account.SubHeadMaster;

public interface GeneralLedgerRepository extends JpaRepository<GeneralLedger, Long> {

	List<GeneralLedger> findByShopId(School udiseNo);

	List<GeneralLedger> findBySubhead(SubHeadMaster subheadid);

	@Query("SELECT g FROM GeneralLedger g WHERE g.subhead.subheadId = :subheadId AND g.shopId.udiseNo = :shopId")
	List<GeneralLedger> findBySubheadAndShop( long subheadId, long shopId);

	@Query("SELECT gl FROM GeneralLedger gl " + "JOIN gl.headId h " + "JOIN h.bookSideMaster bsm "
			+ "WHERE bsm.booksideName = :booksideName AND gl.shopId.udiseNo = :shopId")
	List<GeneralLedger> findByBooksideNameAndShopId(String booksideName, Long shopId);

	List<GeneralLedger> findByHeadId(HeadMaster headMaster);

	@Query("SELECT g FROM GeneralLedger g WHERE g.entrydate <= :entrydate AND g.shopId.udiseNo = :shopId")
	List<GeneralLedger> getLedgersUpToDate(Date entrydate, long shopId);

}
