package com.kayson.web.model.dao;

import com.kayson.web.model.domain.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleDao {

    static final String table = "yn_role";
    static final String fields = "id,name,flag";

    @Select("SELECT "+fields+" FROM "+table+" WHERE id = #{id}")
    Role findById(@Param("id") long id);

    @Insert("INSERT INTO  "+table+" ("+fields+") VALUES (#{r.id}, #{r.name}, #{r.flag})")
    int insert(@Param("r") Role role);
}
