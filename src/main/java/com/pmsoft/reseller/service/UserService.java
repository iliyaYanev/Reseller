package com.pmsoft.reseller.service;

import com.pmsoft.reseller.mapper.UserMapper;
import com.pmsoft.reseller.model.dto.user.UserDto;
import com.pmsoft.reseller.model.entity.User;
import com.pmsoft.reseller.repository.UserRepository;
import com.pmsoft.reseller.util.LoggedIn;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final LoggedIn loggedIn;

    public UserService(UserRepository userRepository, LoggedIn loggedIn) {
        this.userRepository = userRepository;
        this.loggedIn = loggedIn;
    }

    public User getById(Long id) {
        return this.userRepository.findById(id).get();
    }

    public UserDto getByUsername(String username) {
        User user = this.userRepository.findByUsername(username);

        return UserMapper.INSTANCE.userToUserDto(user);
    }

    public UserDto getByEmail(String email) {
        User user = this.userRepository.findByEmail(email);

        return UserMapper.INSTANCE.userToUserDto(user);
    }
}
