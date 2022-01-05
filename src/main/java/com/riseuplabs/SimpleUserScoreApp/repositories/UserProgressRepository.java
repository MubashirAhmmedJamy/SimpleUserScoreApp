package com.riseuplabs.SimpleUserScoreApp.repositories;

import com.riseuplabs.SimpleUserScoreApp.models.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
}
