package com.restaurant.restaurant.service.report;


import com.restaurant.restaurant.mapper.ReportMapper;
import com.restaurant.restaurant.pojo.entity.Report;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class SelectReportService {
    // 已改try-with-resources
    public List<Report> selectReport(int canteenId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        ReportMapper reportMapper = sqlSession.getMapper(ReportMapper.class);

        List<Report> reportList = null;
        try (sqlSession) {
            reportList = reportMapper.selectNoReplyByCanteenId(canteenId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }
}
