package com.pmsoft.reseller.model.enums;

public enum OfferCondition {

    EXCELLENT("Excellent"),
    GOOD("Good"),
    ACCEPTABLE("Acceptable");

    private String description;

    OfferCondition(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
