package com.pmsoft.reseller.model.entity;

import com.pmsoft.reseller.model.enums.OfferCondition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "conditions")
@NoArgsConstructor
@Getter
@Setter
public class Condition extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private OfferCondition name;

    @Column(nullable = false)
    private String description;
}
