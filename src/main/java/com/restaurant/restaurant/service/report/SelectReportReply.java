package com.restaurant.restaurant.service.report;

import com.restaurant.restaurant.mapper.ReportMapper;
import com.restaurant.restaurant.mapper.ReportReplyMapper;
import com.restaurant.restaurant.pojo.ReportReplyShow;
import com.restaurant.restaurant.pojo.entity.Report;
import com.restaurant.restaurant.pojo.entity.ReportReply;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletContext;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class SelectReportReply {
    public List<ReportReplyShow> selectReportReply(int userId, ServletContext context) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        ReportReplyMapper reportReplyMapper = sqlSession.getMapper(ReportReplyMapper.class);
        Map<Integer, Integer> reportReplyCounts =
                (Map<Integer,  Integer>) context.getAttribute("reportReplyCounts");

        List<ReportReplyShow> reportList = null;
        try (sqlSession) {
            reportList = reportReplyMapper.selectByUserId(userId);
            reportReplyCounts.replace(userId, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }
}
