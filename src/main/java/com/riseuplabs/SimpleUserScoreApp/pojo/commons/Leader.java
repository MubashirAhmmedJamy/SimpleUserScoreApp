package com.riseuplabs.SimpleUserScoreApp.pojo.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leader {
    Long rank;
    int level;
    String name;
    float score;
}
