package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.ModuleMaster;

@Repository
public interface ModuleMasterRepository extends JpaRepository<ModuleMaster, Long>{

}
