package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Admin;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CanteenAdminMapper {
    List<CanteenAdmin> selectAll();
    void insertCanteenAdmin(CanteenAdmin canteenAdmin);

    CanteenAdmin selectById(@Param("canteenAdminId") int canteenAdminId);

    List<Reply> selectReplyById(@Param("id") int id);

    void updateCanteenData(Canteen canteen);

    int selectByName(@Param("name") String name);
    int deleteById(@Param("canteenAdminId") int canteenAdminId);

    void updateCanteenAdmin(CanteenAdmin canteenAdmin);
}
