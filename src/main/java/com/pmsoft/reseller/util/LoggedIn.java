package com.pmsoft.reseller.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.servlet.ModelAndView;

@NoArgsConstructor
@Getter
@Setter
public class LoggedIn {

    private Long id;

    public boolean isLoggedIn() {
        return this.id != null;
    }
    public void clearUser() {
        this.id = null;
    }
    public String authGuard(String navigateTo) {
        return isLoggedIn() ? navigateTo :
            "redirect:/auth/login";
    }
    public ModelAndView authGuard(ModelAndView navigateTo) {
        return isLoggedIn() ? navigateTo :
            new ModelAndView("redirect:/auth/login");
    }
}
