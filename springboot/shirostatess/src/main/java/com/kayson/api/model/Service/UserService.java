package com.kayson.api.model.Service;

import com.kayson.api.model.dao.UserDao;
import com.kayson.api.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService{

    @Autowired
    private UserDao userDao;

    public User findByAccount(String account){
        try {
            return userDao.findByAccount(account);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public User findById(long id){
        if(id < 0){
            return null;
        }
        try {
            return userDao.findById(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(User user){
        if(user == null){
            return false;
        }

        try {
            userDao.insert(user);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<User> getAll(){
        return userDao.findAll();
    }

}
