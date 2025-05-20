package com.api.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.account.OpeningBal;

@Repository
public interface OpeningBalRepository extends JpaRepository<OpeningBal, Long>{

}
