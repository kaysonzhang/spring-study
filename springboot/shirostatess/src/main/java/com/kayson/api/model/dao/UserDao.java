package com.kayson.api.model.dao;

import com.kayson.api.model.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 添加了@Mapper注解之后这个接口在编译时会生成相应的实现类
 *
 * 需要注意的是：这个接口中不可以定义同名的方法，因为会生成相同的id
 * 也就是说这个接口是不支持重载的
 */
@Mapper
@Repository
public interface UserDao {

    static final String table = "yn_user";
    static final String fileds = "id,nickname,account,password,salt,createTime,lastLoginTime,status";

    @Select("SELECT "+fileds+" FROM "+table+" WHERE account = #{account}")
    @Results({
            @Result(property = "createTime", column = "create_Time"),
            @Result(property = "lastLoginTime", column = "last_Login_Time")
    })
    User findByAccount(@Param("account") String account);

    @Select("SELECT "+fileds+" FROM "+table+" WHERE id = #{id}")
    /*
    为了解决重复使用，那就要让他变成一个有id的整体，其他地方要用就直接调用
    @ResultMap(“id”)
    @Result中通过id属性引用这个resultMap
    */
    @Results(id="usermap",value = {
            @Result(property = "createTime", column = "create_Time"),
            @Result(property = "lastLoginTime", column = "last_Login_Time")
    })
    User findById(@Param("id") long id);


    @Options(useGeneratedKeys = true, keyProperty = "u.id")
    @Insert("INSERT INTO  "+table+" ("+fileds+") VALUES (#{u.id}, #{u.nickname}, #{u.account}, #{u.password}, #{u.salt}, #{u.createTime}, #{u.lastLoginTime}, #{u.status})")
    //添加这个可以返回自增ID，直接在对象返回
    int insert(@Param("u") User user);


    @Select("SELECT * FROM yn_user")
    List<User> findAll();
/*
    @Update("UPDATE user SET age=#{age} WHERE name=#{name}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

    @Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
    int insertByUser(User user);

    @Insert("INSERT INTO user(name, age) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);
    */
}
