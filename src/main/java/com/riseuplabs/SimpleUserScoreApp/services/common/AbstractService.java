package com.riseuplabs.SimpleUserScoreApp.services.common;
import org.hibernate.service.spi.ServiceException;
import java.util.List;

public interface AbstractService<T> {
    T create(T t) throws ServiceException;
    T update(T t) throws ServiceException;
    T delete(long id) throws ServiceException;
    T findById(long id);
    List<T> findAll();
}
