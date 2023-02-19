package com.pmsoft.reseller.service;

import com.pmsoft.reseller.mapper.OfferMapper;
import com.pmsoft.reseller.mapper.UserMapper;
import com.pmsoft.reseller.model.dto.offer.OfferDto;
import com.pmsoft.reseller.model.dto.user.UserWithOffersDto;
import com.pmsoft.reseller.model.entity.Condition;
import com.pmsoft.reseller.model.entity.Offer;
import com.pmsoft.reseller.model.entity.User;
import com.pmsoft.reseller.repository.OfferRepository;
import com.pmsoft.reseller.util.LoggedIn;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    private final ConditionService conditionService;

    private final UserService userService;

    private final LoggedIn loggedIn;

    public OfferService(OfferRepository offerRepository, ConditionService conditionService,
        UserService userService, LoggedIn loggedIn) {
        this.offerRepository = offerRepository;
        this.conditionService = conditionService;
        this.userService = userService;
        this.loggedIn = loggedIn;
    }


    public void addOffer(OfferDto offerDto) {
        Offer offer = OfferMapper.INSTANCE.offerDtoToOffer(offerDto);
        Condition condition = this.conditionService.getByName(offerDto.getConditionName());
        User user = this.userService.getById(this.loggedIn.getId());

        offer.setCondition(condition);
        offer.setCreatedBy(user);
        this.offerRepository.saveAndFlush(offer);
    }

    public void buyOffer(Long id) {
        Offer offer = this.offerRepository.findById(id).get();
        User user = this.userService.getById(this.loggedIn.getId());

        offer.setCreatedBy(null);
        offer.setOwner(user);

        this.offerRepository.saveAndFlush(offer);
    }

    public void deleteOffer(Long id) {
        this.offerRepository.deleteById(id);
    }

    public UserWithOffersDto getUserOffers() {
        User user = this.userService.getById(this.loggedIn.getId());

        UserWithOffersDto userWithOffersDto = new UserWithOffersDto();
        userWithOffersDto.setUser(UserMapper.INSTANCE.userToUserDto(user));

        user.getOffers().forEach(offer -> {
            OfferDto offerDto = OfferMapper.INSTANCE.offerToOfferDto(offer);
            userWithOffersDto.getOffers().add(offerDto);
        });

        user.getBoughtOffers().forEach(offer -> {
            OfferDto offerDto = OfferMapper.INSTANCE.offerToOfferDto(offer);
            userWithOffersDto.getBoughtOffers().add(offerDto);
        });

        return userWithOffersDto;
    }

    public List<OfferDto> getOtherOffers() {
        List<Offer> offers = this.offerRepository.findByCreatedBy_IdNot(this.loggedIn.getId());


        return OfferMapper.INSTANCE.offersToOfferDtos(offers);
    }
}
