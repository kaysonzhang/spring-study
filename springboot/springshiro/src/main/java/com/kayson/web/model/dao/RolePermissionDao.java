package com.kayson.web.model.dao;

import com.kayson.web.model.domain.RolePermission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RolePermissionDao {
    static final String table = "yn_role_permission";
    static final String fields = "rid,pid";

    @Select("SELECT r.rid,r.pid,p.name as pName,p.url as pUrl FROM "+table+" as r left join yn_permission as p on r.pid = p.id WHERE r.rid = #{rid}")
    List<RolePermission> findByRoleId(@Param("rid") long rid);

    @Insert("INSERT INTO  "+table+" ("+fields+") VALUES (#{rp.rid}, #{urp.pid})")
    int insert(@Param("rp") RolePermission rolePermission);
}