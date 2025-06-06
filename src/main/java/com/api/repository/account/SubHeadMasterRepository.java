package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.entity.School;
import com.api.entity.account.SubHeadMaster;

@Repository
public interface SubHeadMasterRepository extends JpaRepository<SubHeadMaster, Long>{
	
	List<SubHeadMaster> findBySchoolUdise(School udiseNo);

	@Query("SELECT COALESCE(MAX(g.subheadId), 0) FROM SubHeadMaster g")
	Long findMaxId();
	
	SubHeadMaster findBysubheadId(long id);
	

}
