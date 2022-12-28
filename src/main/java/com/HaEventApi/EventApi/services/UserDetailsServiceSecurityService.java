package com.HaEventApi.EventApi.services;

import com.HaEventApi.EventApi.models.User;
import com.HaEventApi.EventApi.repository.UserRepository;
import com.HaEventApi.EventApi.security.UserDetailsSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Метод для поиска пользователя в БД
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailsSecurity(user.get());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
