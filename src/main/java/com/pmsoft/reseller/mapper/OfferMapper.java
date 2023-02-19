package com.pmsoft.reseller.mapper;

import com.pmsoft.reseller.model.dto.offer.OfferDto;
import com.pmsoft.reseller.model.entity.Offer;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OfferMapper {

    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

    @Mapping(target = "condition", ignore = true)
    Offer offerDtoToOffer(OfferDto offerDto);

    @Mapping(target = "conditionName", source = "condition.name")
    @Mapping(target = "username", source = "createdBy.username")
    OfferDto offerToOfferDto(Offer song);

    @Mapping(target = "conditionName", source = "condition.name")
    List<OfferDto> offersToOfferDtos(List<Offer> offers);
}
