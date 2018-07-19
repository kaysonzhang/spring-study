package com.kayson.web.model.dao;

import com.kayson.web.model.domain.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by kayson
 * @data 2018/7/16 13:47
 * @description
 */
@Mapper
@Repository
public interface UserPermissionDao {

    @Select("select a.uid,a.rid,c.name as pName,c.url as pUrl from  yn_user_role a left join yn_role_permission b on a.rid = b.rid " +
            "left join yn_permission c on b.pid=c.id where a.uid = #{uid}")
    List<UserPermission> findUserPermissionByUid(@Param("uid") long uid);
}
