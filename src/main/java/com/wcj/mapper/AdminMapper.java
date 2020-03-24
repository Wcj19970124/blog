package com.wcj.mapper;

import com.wcj.pojo.Admin;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 管理员表Mapper
 *
 * @author wcj
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Component
public interface AdminMapper {

    /**
     * 根据用户名查询管理员信息
     * @param userName
     * @return
     */
    @Select("select name,signature,username,password from bl_admin where username=#{username}")
    Admin getAdminByUserName(String userName);
}
