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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController()
@RequestMapping("/api")
public class MainController {

    private final UserDetailsServiceSecurity userDetailsServiceSecurity;

    @Autowired
    public MainController(UserDetailsServiceSecurity userDetailsServiceSecurity) {
        this.userDetailsServiceSecurity = userDetailsServiceSecurity;
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError fieldError : errors){
                stringBuilder.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append(";");
            }
            throw new LoginInvalidException(stringBuilder.toString());
        }

        userDetailsServiceSecurity.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/people")
    public List<User> getPeople(){
        return userDetailsServiceSecurity.findAll();
    }

    @GetMapping("/test")
    public String test(){
        return "it's test";
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(LoginInvalidException loginInvalidException){
        UserErrorResponse userErrorResponse = new UserErrorResponse(loginInvalidException.getMessage());
        return new ResponseEntity<>(userErrorResponse, HttpStatus.BAD_REQUEST);
    }



}
