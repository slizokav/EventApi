package com.HaEventApi.EventApi.controllers;


import com.HaEventApi.EventApi.models.User;
import com.HaEventApi.EventApi.services.UserDetailsServiceSecurity;
import com.HaEventApi.EventApi.util.LoginInvalidException;
import com.HaEventApi.EventApi.util.UserErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController()
@RequestMapping("/api")
public class MainController {

    private final UserDetailsServiceSecurity userDetailsServiceSecurity;

    @Autowired
    public MainController(UserDetailsServiceSecurity userDetailsServiceSecurity) {
        this.userDetailsServiceSecurity = userDetailsServiceSecurity;
    }


    @PostMapping("/registration")
    public HttpEntity<HttpStatus> create(@RequestBody @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new LoginInvalidException();
        }

        userDetailsServiceSecurity.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(LoginInvalidException loginInvalidException){
        UserErrorResponse userErrorResponse = new UserErrorResponse("Неправильно введены данные пользователя");
        return new ResponseEntity<>(userErrorResponse, HttpStatus.BAD_REQUEST);
    }



}
