package com.pmsoft.reseller.model.dto.user;

import com.pmsoft.reseller.model.dto.offer.OfferDto;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithOffersDto {

    private UserDto user;

    private List<OfferDto> offers;

    private List<OfferDto> boughtOffers;

    public UserWithOffersDto() {
        this.offers = new ArrayList<>();
        this.boughtOffers = new ArrayList<>();
    }
}
