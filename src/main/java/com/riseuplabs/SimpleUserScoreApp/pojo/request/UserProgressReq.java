package com.riseuplabs.SimpleUserScoreApp.pojo.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.riseuplabs.SimpleUserScoreApp.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProgressReq {
    private int level;
    private float score;
    private Long user_id;
}
