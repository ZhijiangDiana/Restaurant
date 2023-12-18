package com.restaurant.restaurant.controller.admin.communityManage;

import com.restaurant.restaurant.mapper.ReplyMapper;
import com.restaurant.restaurant.pojo.entity.Reply;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/showReplyById")
public class ShowReply extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commentId = request.getParameter("commentId");
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try(sqlSession){
            ReplyMapper replyMapper = sqlSession.getMapper(ReplyMapper.class);
            List<Reply> replyList = replyMapper.selectByCommentId(Integer.parseInt(commentId));
            sqlSession.close();
            response.getWriter().print(FrontEndUtils.objectToBody(null,"0",replyList));
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
            response.getWriter().print(FrontEndUtils.objectToBody("系统繁忙","1",null));
        }
    }
}
