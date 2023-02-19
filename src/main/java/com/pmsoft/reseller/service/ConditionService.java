package com.pmsoft.reseller.service;

import com.pmsoft.reseller.model.entity.Condition;
import com.pmsoft.reseller.model.enums.OfferCondition;
import com.pmsoft.reseller.repository.ConditionRepository;
import org.springframework.stereotype.Service;

@Service
public class ConditionService {

    private final ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public Condition getByName(String name) {
        return this.conditionRepository.findByName(OfferCondition.valueOf(name));
    }
}
