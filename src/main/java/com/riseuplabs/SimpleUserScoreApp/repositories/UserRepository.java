package com.riseuplabs.SimpleUserScoreApp.repositories;

import com.riseuplabs.SimpleUserScoreApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
