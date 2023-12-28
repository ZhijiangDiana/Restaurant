package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.ReportReplyShow;
import com.restaurant.restaurant.pojo.entity.ReportReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportReplyMapper {
    List<ReportReply> selectAll();
    ReportReply selectById(@Param("id") int id);
    ReportReply selectByReportId(@Param("id") int id);
    List<ReportReplyShow> selectByUserId(@Param("id") int id);
    void insertReportReply(ReportReply reportReply);
}
