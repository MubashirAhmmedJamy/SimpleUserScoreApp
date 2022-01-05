package com.riseuplabs.SimpleUserScoreApp.controllers;

import com.riseuplabs.SimpleUserScoreApp.models.User;
import com.riseuplabs.SimpleUserScoreApp.pojo.response.ApiResponse;
import com.riseuplabs.SimpleUserScoreApp.services.user.UserService;
import com.riseuplabs.SimpleUserScoreApp.utils.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Mappings.REST)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = Mappings.GET_USER)
    public ResponseEntity<Object> getUser(@PathVariable Long id){
        ApiResponse response = new ApiResponse();
        String messageCode;

        System.out.println("Working");

        try{
            User user = userService.findById(id);

            if(user != null){
                response.setData(user);
                messageCode = "api.list.success";
                response.setMessage(messageSource.getMessage(messageCode, null, null));
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                response.setSuccess(false);
                messageCode = "user.not.found";
                response.setMessage(messageSource.getMessage(messageCode, null, null));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            response.setSuccess(false);
            messageCode = "api.list.failed";
            response.setMessage(messageSource.getMessage(messageCode, null, null));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

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
