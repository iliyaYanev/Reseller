package com.pmsoft.reseller.web;

import com.pmsoft.reseller.model.dto.offer.OfferDto;
import com.pmsoft.reseller.service.OfferService;
import com.pmsoft.reseller.util.LoggedIn;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    private final LoggedIn loggedIn;

    public OfferController(OfferService offerService, LoggedIn loggedIn) {
        this.offerService = offerService;
        this.loggedIn = loggedIn;
    }

    @ModelAttribute(name = "offerDto")
    public OfferDto getOfferDto() {
        return new OfferDto();
    }

    @GetMapping("/add")
    public String getAddSongs() {
        return this.loggedIn.authGuard("offer-add");
    }

    @PostMapping("/add")
    public String addProduct(@Valid OfferDto offerDto,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes) {

        if (this.loggedIn.getId() == null) {
            return this.loggedIn.authGuard("redirect:/login");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes
                .addFlashAttribute("offerDto", offerDto)
                .addFlashAttribute("org.springframework.validation.BindingResult.offerDto", bindingResult);

            return this.loggedIn.authGuard("offer-add");
        }

        this.offerService.addOffer(offerDto);

        return "redirect:/home";
    }

    @GetMapping("/remove")
    public String deleteOffer(@PathParam("id") Long id) {
        this.offerService.deleteOffer(id);

        return loggedIn.authGuard("redirect:/home");
    }

    @GetMapping("/buy")
    public String buy(@PathParam("id") Long id) {
        this.offerService.buyOffer(id);

        return loggedIn.authGuard("redirect:/home");
    }
}
