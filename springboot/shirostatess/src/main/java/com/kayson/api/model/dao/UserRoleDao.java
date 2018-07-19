package com.kayson.api.model.dao;

import com.kayson.api.model.domain.UserRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRoleDao {

    static final String table = "yn_user_role";
    static final String fields = "uid,rid";

    @Select("SELECT u.uid,u.rid,r.name as roleName,r.flag as roleType FROM "+table+" u LEFT JOIN yn_role r ON u.rid=r.id WHERE u.uid = #{uid}")
    List<UserRole> findByUid(@Param("uid") long uid);

    @Insert("INSERT INTO  "+table+" ("+fields+") VALUES (#{u.uid}, #{u.rid})")
    int insert(@Param("u") UserRole userRole);
    /*<resultMap id="queryForListMap" type="com.sica.domain.User">

        <id column="id" property="id" jdbcType="VARCHAR"/>

        <result column="username" property="username" jdbcType="VARCHAR"/>

        <result column="password" property="password" jdbcType="VARCHAR"/>

        <collection property="roles" javaType="java.util.List" ofType="com.sica.domain.Role">

            <id column="r_id" property="id" jdbcType="VARCHAR" />

            <result column="r_name" property="name" jdbcType="VARCHAR" />

            <result column="r_jsms" property="jsms" jdbcType="VARCHAR" />

            <result column="r_bz" property="bz" jdbcType="VARCHAR" />

            <result column="r_jlzt" property="jlzt" jdbcType="INTEGER" />

            <result column="r_glbm" property="glbm" jdbcType="VARCHAR" />

        </collection>

    </resultMap>*/

}
