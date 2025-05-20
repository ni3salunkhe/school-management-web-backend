package com.api.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.account.SubHeadMaster;

@Repository
public interface SubHeadMasterRepository extends JpaRepository<SubHeadMaster, Long>{

}
