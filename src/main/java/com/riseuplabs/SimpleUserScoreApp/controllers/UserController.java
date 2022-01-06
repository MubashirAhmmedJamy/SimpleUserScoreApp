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

import java.util.List;

@RestController
@RequestMapping(value = Mappings.REST)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = Mappings.GET_USER_LIST)
    public ResponseEntity<Object> getUserList() {
        ApiResponse response = new ApiResponse();
        String messageCode;

        try {
            List<User> userList = userService.findAll();
            response.setData(userList);
            messageCode = "api.list.success";
            response.setMessage(messageSource.getMessage(messageCode, null, null));
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            response.setSuccess(false);
            messageCode = "api.list.failed";
            response.setMessage(messageSource.getMessage(messageCode, null, null));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping(value = Mappings.GET_USER)
    public ResponseEntity<Object> getUser(@PathVariable Long id){
        ApiResponse response = new ApiResponse();
        String messageCode;

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


    @RequestMapping(value = Mappings.CREATE_USER_INCLUDING_PROGRESS, method = RequestMethod.POST)
    public ResponseEntity<Object> createUserIncludingProgress(@RequestBody User user){
        ApiResponse response = new ApiResponse();
        String messageCode;

        if(user.getUserProgress() == null){
            response.setSuccess(false);
            messageCode = "mandatory.field.missing";
            response.setMessage(messageSource.getMessage(messageCode, null, null) + ": userProgress");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            user.getUserProgress().setUser(user);
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
        }catch (Exception e){
            response.setSuccess(false);
            messageCode = "api.create.failed";
            response.setMessage(messageSource.getMessage(messageCode, null, null));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @RequestMapping(value = Mappings.UPDATE_USER_INCLUDING_PROGRESS, method = RequestMethod.POST)
    public ResponseEntity<Object> updateUserIncludingProgress(@RequestBody User user){
        ApiResponse response = new ApiResponse();
        String messageCode;

        if(user.getUserProgress() == null){
            response.setSuccess(false);
            messageCode = "mandatory.field.missing";
            response.setMessage(messageSource.getMessage(messageCode, null, null) + ": userProgress");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if(user.getId() == null){
            response.setSuccess(false);
            messageCode = "mandatory.field.missing";
            response.setMessage(messageSource.getMessage(messageCode, null, null) + ": id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            User existing = userService.findById(user.getId());

            if(existing == null){
                response.setSuccess(false);
                messageCode = "user.not.found";
                response.setMessage(messageSource.getMessage(messageCode, null, null) + " with id " + user.getId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            existing.setName(user.getName());
            existing.setCountry(user.getCountry());
            user.getUserProgress().setId(existing.getUserProgress().getId());
            user.getUserProgress().setUser(existing);
            existing.setUserProgress(user.getUserProgress());

            user = userService.update(existing);

            if(user != null){
                response.setData(user);
                messageCode = "api.update.success";
                response.setMessage(messageSource.getMessage(messageCode, null, null));
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                response.setSuccess(false);
                messageCode = "api.update.failed";
                response.setMessage(messageSource.getMessage(messageCode, null, null));
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }catch (Exception e){
            response.setSuccess(false);
            messageCode = "api.update.failed";
            response.setMessage(messageSource.getMessage(messageCode, null, null));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @RequestMapping(value = Mappings.UPDATE_USER, method = RequestMethod.POST)
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        ApiResponse response = new ApiResponse();
        String messageCode;

        try {
            User userItem = userService.findById(user.getId());
            if(userItem != null){
                userItem.setName(user.getName());
                userItem.setCountry(user.getCountry());
                response.setData(userService.update(userItem));

                messageCode = "api.update.success";
                response.setMessage(messageSource.getMessage(messageCode, null, null));
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                response.setSuccess(false);
                messageCode = "user.not.found";
                response.setMessage(messageSource.getMessage(messageCode, null, null) + " with id " + user.getId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            response.setSuccess(false);
            messageCode = "api.update.failed";
            response.setMessage(messageSource.getMessage(messageCode, null, null));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
