package com.pmsoft.reseller.repository;

import com.pmsoft.reseller.model.entity.Condition;
import com.pmsoft.reseller.model.enums.OfferCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

    Condition findByName(OfferCondition name);
}
