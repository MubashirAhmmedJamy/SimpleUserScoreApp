package com.riseuplabs.SimpleUserScoreApp.controllers;

import com.riseuplabs.SimpleUserScoreApp.models.User;
import com.riseuplabs.SimpleUserScoreApp.pojo.response.ApiResponse;
import com.riseuplabs.SimpleUserScoreApp.services.user.UserService;
import com.riseuplabs.SimpleUserScoreApp.utils.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Mappings.REST)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = Mappings.CREATE_USER, method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user){
        ApiResponse response = new ApiResponse();
        String messageCode;

        user = userService.create(user);

        if(user != null){
            response.setData(user);
            messageCode = "api.create.success";
            response.setMessage(messageSource.getMessage(messageCode, null, null));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            response.setSuccess(false);
            messageCode = "api.create.failed";
            response.setMessage(messageSource.getMessage(messageCode, null, null));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
