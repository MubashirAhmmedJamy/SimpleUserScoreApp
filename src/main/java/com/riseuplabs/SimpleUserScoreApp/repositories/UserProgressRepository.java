package com.riseuplabs.SimpleUserScoreApp.repositories;

import com.riseuplabs.SimpleUserScoreApp.models.UserProgress;
import com.riseuplabs.SimpleUserScoreApp.pojo.commons.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    @Query(value = "select * from (SELECT user_id, RANK() OVER (ORDER BY score desc) as user_rank FROM score.user_progress) as t where user_rank <= 10", nativeQuery = true)
    List<Rank> findCurrentRanking();

    @Query(value = "select * from (SELECT user_id, RANK() OVER (ORDER BY score desc) as user_rank FROM score.user_progress) as t where user_id = :id", nativeQuery = true)
    Rank findRankingById(Long id);
}
