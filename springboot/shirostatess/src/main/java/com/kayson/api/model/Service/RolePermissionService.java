package com.kayson.api.model.Service;

import com.kayson.api.model.dao.RolePermissionDao;
import com.kayson.api.model.domain.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by kayson
 * @data 2018/7/15 10:10
 * @description
 */
@Service
public class RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    public List<RolePermission> findByRoleId(long rid){
        if(rid < 0){
            return null;
        }
        try {
            return rolePermissionDao.findByRoleId(rid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(RolePermission rolePermission){
        try {
            rolePermissionDao.insert(rolePermission);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
