package com.riseuplabs.SimpleUserScoreApp.controllers;

import com.riseuplabs.SimpleUserScoreApp.models.User;
import com.riseuplabs.SimpleUserScoreApp.models.UserProgress;
import com.riseuplabs.SimpleUserScoreApp.pojo.request.UserProgressReq;
import com.riseuplabs.SimpleUserScoreApp.pojo.response.ApiResponse;
import com.riseuplabs.SimpleUserScoreApp.services.user.UserService;
import com.riseuplabs.SimpleUserScoreApp.services.userProgress.UserProgressService;
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
@RequestMapping(Mappings.REST)
public class UserProgressController {
    @Autowired
    private UserProgressService userProgressService;

    @Autowired
    private UserService userService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = Mappings.CREATE_USER_PROGRESS, method = RequestMethod.POST)
    public ResponseEntity<Object> createUserProgress(@RequestBody UserProgressReq userProgressReq){
        ApiResponse response = new ApiResponse();
        String messageCode;
        User user = userService.findById(userProgressReq.getUser_id());

        if(user != null){
            UserProgress userProgress = new UserProgress();
            userProgress.setUser(user);
            userProgress.setLevel(userProgressReq.getLevel());
            userProgress.setScore(userProgressReq.getScore());

            if(user.getUserProgress() != null){
                userProgress.setId(user.getUserProgress().getId());
            }

            UserProgress userProgressItem = userProgressService.create(userProgress);

            if(userProgressItem != null){
                response.setData(userProgressItem);
                messageCode = "api.create.success";
                response.setMessage(messageSource.getMessage(messageCode, null, null));
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }else{
                response.setSuccess(false);
                messageCode = "api.create.failed";
                response.setMessage(messageSource.getMessage(messageCode, null, null));
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }else{
            response.setSuccess(false);
            messageCode = "user.not.found";
            response.setMessage(messageSource.getMessage(messageCode, null, null)+" with id "+userProgressReq.getUser_id());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
