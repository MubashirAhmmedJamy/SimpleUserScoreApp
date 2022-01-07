package com.riseuplabs.SimpleUserScoreApp.services.userProgress;

import com.riseuplabs.SimpleUserScoreApp.models.UserProgress;
import com.riseuplabs.SimpleUserScoreApp.pojo.commons.Rank;
import com.riseuplabs.SimpleUserScoreApp.services.common.AbstractService;

import java.util.List;

public interface UserProgressService extends AbstractService<UserProgress> {
    List<Rank> getCurrentRanking();
    Rank getRankingById(Long id);
}
