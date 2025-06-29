package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.entity.ModuleMaster;
import com.api.entity.School;
import com.api.entity.Subscription;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("SELECT s FROM Subscription s WHERE s.schoolUdiseNo.udiseNo = :udiseNo")
    Optional<Subscription> findByUdiseNumber(long udiseNo);
    @Query("SELECT s FROM Subscription s WHERE s.schoolUdiseNo.udiseNo = :udiseNo AND s.subscriptionEndDate = :endDate")
    Subscription findByUdiseNoAndEndDate(@Param("udiseNo") long udiseNo, @Param("endDate") LocalDate endDate);
    
    Subscription findBySchoolUdiseNoAndModuleId(School udise, ModuleMaster moduleId);
    
    List<Subscription> findAllBySchoolUdiseNoUdiseNo(long udiseNo);

}

