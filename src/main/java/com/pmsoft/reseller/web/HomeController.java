package com.pmsoft.reseller.web;

import com.pmsoft.reseller.model.dto.offer.OfferDto;
import com.pmsoft.reseller.model.dto.user.UserWithOffersDto;
import com.pmsoft.reseller.service.OfferService;
import com.pmsoft.reseller.util.LoggedIn;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    private final OfferService offerService;

    private final LoggedIn loggedIn;

    public HomeController(OfferService offerService, LoggedIn loggedIn) {
        this.offerService = offerService;
        this.loggedIn = loggedIn;
    }

    @GetMapping
    public String getIndex() {
        return "index";
    }

    @RequestMapping("/home")
    public ModelAndView getHome(ModelAndView modelAndView) {
        if (this.loggedIn.getId() == null) {
            return this.loggedIn.authGuard(modelAndView);
        }

        UserWithOffersDto userOffers = this.offerService.getUserOffers();
        List<OfferDto> otherOffers = this.offerService.getOtherOffers();

        modelAndView.setViewName("home");
        modelAndView.addObject("userOffers", userOffers);
        modelAndView.addObject("otherOffers", otherOffers);

        return this.loggedIn.authGuard(modelAndView);
    }
}
