package com.riseuplabs.SimpleUserScoreApp.services.userProgress;

import com.riseuplabs.SimpleUserScoreApp.models.UserProgress;
import com.riseuplabs.SimpleUserScoreApp.pojo.commons.Rank;
import com.riseuplabs.SimpleUserScoreApp.repositories.UserProgressRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProgressServiceImpl implements UserProgressService{
    @Autowired
    private UserProgressRepository userProgressRepository;

    @Override
    public UserProgress create(UserProgress userProgress) throws ServiceException {
        try {
            return userProgressRepository.save(userProgress);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserProgress update(UserProgress userProgress) throws ServiceException {
        return userProgressRepository.save(userProgress);
    }

    @Override
    public UserProgress delete(long id) throws ServiceException {
        return null;
    }

    @Override
    public UserProgress findById(long id) {
        return null;
    }

    @Override
    public List<UserProgress> findAll() {
        return userProgressRepository.findAll();
    }

    @Override
    public List<Rank> getCurrentRanking() throws ServiceException{
        return userProgressRepository.findCurrentRanking();
    }
}
