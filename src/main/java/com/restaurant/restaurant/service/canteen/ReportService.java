package com.restaurant.restaurant.service.canteen;

import com.restaurant.restaurant.mapper.CanteenMapper;
import com.restaurant.restaurant.mapper.ReportMapper;
import com.restaurant.restaurant.mapper.ReportReplyMapper;
import com.restaurant.restaurant.pojo.entity.Report;
import com.restaurant.restaurant.pojo.entity.ReportReply;
import com.restaurant.restaurant.utils.Pair;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class ReportService {
    public void reportCanteen(Report report) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        ReportMapper reportMapper = sqlSession.getMapper(ReportMapper.class);
        CanteenMapper canteenMapper = sqlSession.getMapper(CanteenMapper.class);

        reportMapper.insertReport(report);
        canteenMapper.increaseReportById(report.getCanteenId());

        sqlSession.commit();
        sqlSession.close();
    }

    public void replyReport(ReportReply reportReply) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        ReportReplyMapper reportReplyMapper = sqlSession.getMapper(ReportReplyMapper.class);

        reportReplyMapper.insertReportReply(reportReply);
        sqlSession.commit();

        sqlSession.close();
    }

    public Pair<Report, ReportReply> getReportAndReply(int reportId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        ReportMapper reportMapper = sqlSession.getMapper(ReportMapper.class);
        ReportReplyMapper reportReplyMapper = sqlSession.getMapper(ReportReplyMapper.class);

        Report report = reportMapper.selectById(reportId);
        ReportReply reportReply = reportReplyMapper.selectByReportId(reportId);

        sqlSession.close();
        return new Pair<>(report, reportReply);
    }
}
