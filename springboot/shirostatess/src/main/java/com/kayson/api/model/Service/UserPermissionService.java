package com.kayson.api.model.Service;

import com.kayson.api.model.dao.UserPermissionDao;
import com.kayson.api.model.domain.UserPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by kayson
 * @data 2018/7/16 13:47
 * @description
 */
@Service
public class UserPermissionService {

    @Autowired
    private UserPermissionDao userPermissionDao;

    public List<UserPermission> findUserPermissionByUid(long uid){
        try {
            return userPermissionDao.findUserPermissionByUid(uid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
