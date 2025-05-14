package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Bonafide;

@Repository
public interface BonafideRepository extends JpaRepository<Bonafide, Long>{

}
