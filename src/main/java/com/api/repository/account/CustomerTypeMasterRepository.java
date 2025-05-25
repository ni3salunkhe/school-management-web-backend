package com.api.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.account.CustomerTypeMaster;

@Repository
public interface CustomerTypeMasterRepository extends JpaRepository<CustomerTypeMaster, Long>{

}
