package com.HaEventApi.EventApi.services;

import com.HaEventApi.EventApi.models.User;
import com.HaEventApi.EventApi.repository.UserRepository;
import com.HaEventApi.EventApi.util.LoginInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void save(User user){
        Optional<User> findByUser = userRepository.findByUsername(user.getUsername());

        if (findByUser.isPresent()) {
            throw new LoginInvalidException("");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
