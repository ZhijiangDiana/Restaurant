package com.restaurant.restaurant.service.canteen_manager;


import com.restaurant.restaurant.controller.canteen_manager.GetReport;
import com.restaurant.restaurant.mapper.ReportMapper;
import com.restaurant.restaurant.pojo.entity.Report;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class SelectReport {
    public List<Report> SelectReport() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        ReportMapper reportMapper = sqlSession.getMapper(ReportMapper.class);

        List<Report> reportList = new ArrayList<>();
        reportList = reportMapper.getAllReports();
        return reportList;
    }
}
