package com.pmsoft.reseller.web;

import com.pmsoft.reseller.model.dto.user.UserLoginDto;
import com.pmsoft.reseller.model.dto.user.UserRegisterDto;
import com.pmsoft.reseller.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute(name = "userRegisterDto")
    public UserRegisterDto getUserRegisterDto() {
        return new UserRegisterDto();
    }

    @ModelAttribute(name = "userLoginDto")
    public UserLoginDto getUserLoginDto() {
        return new UserLoginDto();
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid UserLoginDto userLoginDto,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                .addFlashAttribute("userLoginDto", userLoginDto)
                .addFlashAttribute("org.springframework.validation.BindingResult.userLoginDto", bindingResult);

            return "redirect:login";
        }

        this.authService.login(userLoginDto);

        return "redirect:/home";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterDto userRegisterDto,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                .addFlashAttribute("userRegisterDto", userRegisterDto)
                .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);

            return "redirect:register";
        }

        this.authService.register(userRegisterDto);

        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logout() {

        this.authService.logout();

        return "redirect:/";
    }
}
