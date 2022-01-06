package com.riseuplabs.SimpleUserScoreApp.services.user;

import com.riseuplabs.SimpleUserScoreApp.models.User;
import com.riseuplabs.SimpleUserScoreApp.repositories.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) throws ServiceException {
        try {
            return userRepository.save(user);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public User update(User user) throws ServiceException {
        try {
            return userRepository.save(user);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public User delete(long id) throws ServiceException {
        return null;
    }

    @Override
    public User findById(long id) {
        try {
            return userRepository.findById(id).get();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return userRepository.findAll();
        }catch (Exception e){
            return null;
        }
    }
}
