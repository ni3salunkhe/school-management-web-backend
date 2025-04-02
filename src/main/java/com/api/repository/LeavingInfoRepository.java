package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.api.entity.LeavingInfo;

@Repository
public interface LeavingInfoRepository extends JpaRepository<LeavingInfo, Long> {

}
