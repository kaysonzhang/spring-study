package com.kayson.api.model.Service;

import com.kayson.api.model.dao.UserRoleDao;
import com.kayson.api.model.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by kayson
 * @data 2018/7/15 10:09
 * @description
 */
@Service
public class UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    public List<UserRole> findByUid(long uid){
        if(uid < 0){
            return null;
        }
        try {
            return userRoleDao.findByUid(uid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(UserRole userRole){
        try {
           userRoleDao.insert(userRole);
           return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
