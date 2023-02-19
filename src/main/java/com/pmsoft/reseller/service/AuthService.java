package com.pmsoft.reseller.service;

import com.pmsoft.reseller.mapper.UserMapper;
import com.pmsoft.reseller.model.dto.user.UserLoginDto;
import com.pmsoft.reseller.model.dto.user.UserRegisterDto;
import com.pmsoft.reseller.model.entity.User;
import com.pmsoft.reseller.repository.UserRepository;
import com.pmsoft.reseller.util.LoggedIn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final LoggedIn loggedIn;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository userRepository, LoggedIn loggedIn, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.loggedIn = loggedIn;
        this.encoder = encoder;
    }

    public void register(UserRegisterDto userRegisterDto) {
        userRegisterDto.setPassword(encoder.encode(userRegisterDto.getPassword()));
        User user = UserMapper.INSTANCE.userRegisterDtoToUser(userRegisterDto);

        this.userRepository.saveAndFlush(user);
    }

    public void login(UserLoginDto userLoginDto) {
        User user = this.userRepository.findByUsername(userLoginDto.getUsername());

        this.loggedIn.setId(user.getId());
    }

    public String logout() {
        if (this.loggedIn.isLoggedIn()) {
            this.loggedIn.clearUser();

            return "redirect:index";
        }

        return "redirect:login";
    }
}
