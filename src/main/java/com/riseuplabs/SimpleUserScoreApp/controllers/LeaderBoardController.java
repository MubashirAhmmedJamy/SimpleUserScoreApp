package com.riseuplabs.SimpleUserScoreApp.controllers;

import com.riseuplabs.SimpleUserScoreApp.models.User;
import com.riseuplabs.SimpleUserScoreApp.pojo.commons.Leader;
import com.riseuplabs.SimpleUserScoreApp.pojo.commons.Rank;
import com.riseuplabs.SimpleUserScoreApp.services.user.UserService;
import com.riseuplabs.SimpleUserScoreApp.services.userProgress.UserProgressService;
import com.riseuplabs.SimpleUserScoreApp.utils.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Mappings.REST)
public class LeaderBoardController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserProgressService userProgressService;

    @GetMapping(value = Mappings.GET_LEADERS)
    public ResponseEntity<Object> getLeaderBoard(){
        List<Leader> leaderList = new ArrayList<Leader>();

        try {
            for(Rank rank: userProgressService.getCurrentRanking()){
                Leader leader = new Leader();
                leader.setRank(rank.getUser_rank());
                User user = userService.findById(rank.getUser_id());
                leader.setLevel(user.getUserProgress().getLevel());
                leader.setName(user.getName());
                leader.setScore(user.getUserProgress().getScore());
                leaderList.add(leader);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }

        return ResponseEntity.status(HttpStatus.OK).body(leaderList);
    }

    @GetMapping(value = Mappings.GET_USER_SCORE)
    public ResponseEntity<Object> getUserScore(@PathVariable Long id) {
        Leader leader = new Leader();
        try {
            Rank rank = userProgressService.getRankingById(id);

            if(rank == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id "+id);
            }

            leader.setRank(rank.getUser_rank());
            User user = userService.findById(rank.getUser_id());
            leader.setLevel(user.getUserProgress().getLevel());
            leader.setName(user.getName());
            leader.setScore(user.getUserProgress().getScore());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
        return ResponseEntity.status(HttpStatus.OK).body(leader);
    }
}
