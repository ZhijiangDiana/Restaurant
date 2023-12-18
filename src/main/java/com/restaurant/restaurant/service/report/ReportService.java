package com.restaurant.restaurant.service.report;

import com.restaurant.restaurant.mapper.CanteenMapper;
import com.restaurant.restaurant.mapper.ReportMapper;
import com.restaurant.restaurant.mapper.ReportReplyMapper;
import com.restaurant.restaurant.pojo.entity.Report;
import com.restaurant.restaurant.pojo.entity.ReportReply;
import com.restaurant.restaurant.utils.Pair;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class ReportService {
    // 已改try-with-resources
    public boolean reportCanteen(Report report) {
        boolean isSuccess;
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        ReportMapper reportMapper = sqlSession.getMapper(ReportMapper.class);
        CanteenMapper canteenMapper = sqlSession.getMapper(CanteenMapper.class);

        try (sqlSession) {
            reportMapper.insertReport(report);
            canteenMapper.increaseReportById(report.getCanteenId());
            sqlSession.commit();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean replyReport(ReportReply reportReply) {
        boolean isSuccess;
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        ReportReplyMapper reportReplyMapper = sqlSession.getMapper(ReportReplyMapper.class);

        try (sqlSession) {
            reportReplyMapper.insertReportReply(reportReply);
            sqlSession.commit();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            isSuccess = false;
        }

        return isSuccess;
    }

    public Pair<Report, ReportReply> getReportAndReply(int reportId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();

        try (sqlSession) {
            ReportMapper reportMapper = sqlSession.getMapper(ReportMapper.class);
            ReportReplyMapper reportReplyMapper = sqlSession.getMapper(ReportReplyMapper.class);
            Report report = reportMapper.selectById(reportId);
            ReportReply reportReply = reportReplyMapper.selectByReportId(reportId);
            return new Pair<>(report, reportReply);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
