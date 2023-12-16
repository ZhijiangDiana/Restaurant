package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Admin;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CanteenAdminMapper {
    void insertCanteenAdmin(CanteenAdmin canteenAdmin);

    CanteenAdmin selectById(@Param("id") int id);

    List<Reply> selectReplyById(@Param("id") int id);

    void updateCanteenData(Canteen canteen);
}
