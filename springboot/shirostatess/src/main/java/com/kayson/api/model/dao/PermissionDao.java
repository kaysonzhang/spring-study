package com.kayson.api.model.dao;

import com.kayson.api.model.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PermissionDao {

    static final String table = "yn_permission";
    static final String fields = "id,name,url";

    @Select("SELECT "+fields+" FROM "+table+" WHERE id = #{id}")
    Permission findById(@Param("id") long id);

    @Insert("INSERT INTO  "+table+" ("+fields+") VALUES (#{p.id}, #{p.name}, #{p.url})")
    int insert(@Param("p") Permission permission);

}
