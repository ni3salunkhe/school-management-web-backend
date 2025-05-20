package com.api.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.account.HeadMaster;

@Repository
public interface HeadMasterRepository extends JpaRepository<HeadMaster, Long>{

}
