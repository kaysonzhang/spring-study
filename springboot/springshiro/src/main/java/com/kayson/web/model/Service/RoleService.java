package com.kayson.web.model.Service;

import com.kayson.web.model.dao.RoleDao;
import com.kayson.web.model.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by kayson
 * @data 2018/7/15 10:10
 * @description
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role findById(long id){
        try {
            return roleDao.findById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(Role role){
        try {
            roleDao.insert(role);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
