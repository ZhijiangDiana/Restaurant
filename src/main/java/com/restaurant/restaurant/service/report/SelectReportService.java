package com.restaurant.restaurant.service.report;


import com.restaurant.restaurant.mapper.ReportMapper;
import com.restaurant.restaurant.pojo.entity.Report;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletContext;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelectReportService {
    // 已改try-with-resources
    public List<Report> selectReport(int canteenId, ServletContext context) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        ReportMapper reportMapper = sqlSession.getMapper(ReportMapper.class);
        Map<Integer, Map<Integer, Report>> reportNotif =
                (Map<Integer,  Map<Integer, Report>>) context.getAttribute("reportNotif");

        List<Report> reportList = null;
        try (sqlSession) {
            reportList = reportMapper.selectNoReplyByCanteenId(canteenId);
            Map<Integer, Report> canteenReportMap = reportNotif.get(canteenId);
            canteenReportMap.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }
}
