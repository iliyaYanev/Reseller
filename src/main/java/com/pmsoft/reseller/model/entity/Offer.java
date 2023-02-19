package com.pmsoft.reseller.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "offers")
@NoArgsConstructor
@Getter
@Setter
public class Offer extends BaseEntity {

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    private Condition condition;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User owner;
}
