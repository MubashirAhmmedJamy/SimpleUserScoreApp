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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Mappings.REST)
public class UserProgressController {
    @Autowired
    private UserProgressService userProgressService;

    @Autowired
    private UserService userService;

    @Autowired
    MessageSource messageSource;

    @GetMapping(value = Mappings.GET_USER_PROGRESS)
    public ResponseEntity<Object> getUserProgress(@PathVariable Long id){
        ApiResponse response = new ApiResponse();
        String messageCode;

        try{
            User user = userService.findById(id);
            if(user != null){
                UserProgress userProgress = user.getUserProgress();
                response.setData(userProgress);
                messageCode = "api.list.success";
                response.setMessage(messageSource.getMessage(messageCode, null, null));
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                response.setSuccess(false);
                messageCode = "user.not.found";
                response.setMessage(messageSource.getMessage(messageCode, null, null) + " with id "+id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            response.setSuccess(false);
            messageCode = "api.list.failed";
            response.setMessage(messageSource.getMessage(messageCode, null, null));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


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

    @RequestMapping(value = Mappings.UPDATE_USER_PROGRESS, method = RequestMethod.POST)
    public ResponseEntity<Object> updateUserProgress(@RequestBody UserProgressReq userProgressReq){
        ApiResponse response = new ApiResponse();
        String messageCode;
        User user = userService.findById(userProgressReq.getUser_id());
        UserProgress userProgress = new UserProgress();

        if(user != null){
            try {
                userProgress.setUser(user);
                userProgress.setLevel(userProgressReq.getLevel());
                userProgress.setScore(userProgressReq.getScore());

                if(user.getUserProgress() != null){
                    userProgress.setId(user.getUserProgress().getId());
                }

                UserProgress userProgressItem = userProgressService.update(userProgress);

                if(userProgressItem != null){
                    response.setData(userProgressItem);
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
        }else{
            response.setSuccess(false);
            messageCode = "user.not.found";
            response.setMessage(messageSource.getMessage(messageCode, null, null)+" with id "+userProgressReq.getUser_id());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
