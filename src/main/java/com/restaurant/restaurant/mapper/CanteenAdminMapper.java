package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Admin;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import org.apache.ibatis.annotations.Param;

public interface CanteenAdminMapper {
    void insertCanteenAdmin(CanteenAdmin canteenAdmin);

    CanteenAdmin selectById(@Param("id") int id);
}
