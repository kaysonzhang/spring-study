package com.kayson.api.model.Service;

import com.kayson.api.model.dao.PermissionDao;
import com.kayson.api.model.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by kayson
 * @data 2018/7/15 10:10
 * @description
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    public Permission findById(long id){
        try {
            return permissionDao.findById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(Permission permission){
        try {
            permissionDao.insert(permission);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
