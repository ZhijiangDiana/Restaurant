package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    void insertAdmin(Admin admin);

    Admin selectById(@Param("id") int id);
}
