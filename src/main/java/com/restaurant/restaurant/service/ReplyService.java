package com.restaurant.restaurant.service;

import com.restaurant.restaurant.mapper.CanteenAdminMapper;
import com.restaurant.restaurant.mapper.CommentMapper;
import com.restaurant.restaurant.mapper.ReplyMapper;
import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.ReplyShow;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.Reply;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.Constants;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class ReplyService {


    public List<ReplyShow> getAllReplyById(String commentId){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            ReplyMapper replyMapper = sqlSession.getMapper(ReplyMapper.class);
            CanteenAdminMapper canteenAdminMapper = sqlSession.getMapper(CanteenAdminMapper.class);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Reply> replies = replyMapper.selectByCommentId(Integer.parseInt(commentId));
            if (replies == null){
                return null;
            }
            List<ReplyShow> replyShowList = new ArrayList<>();
            for(Reply reply : replies){
                Integer replyId = reply.getReplyId();
                String body = reply.getBody();
                Integer userId = reply.getUserId();
                Integer canteenAdminId = reply.getCanteenAdminId();

                // 说明是食堂管理员回复的
                if (userId == null){
                    CanteenAdmin canteenAdmin = canteenAdminMapper.selectById(canteenAdminId);
                    String canteenAdminName = canteenAdmin.getUsername();
                    ReplyShow replyShow = new ReplyShow(replyId,Integer.parseInt(commentId),canteenAdminName,body);
                    replyShowList.add(replyShow);
                }

                // 说明是师生回复的
                else{
                    User user = userMapper.selectById(userId);
                    String username = user.getUsername();
                    ReplyShow replyShow = new ReplyShow(replyId,Integer.parseInt(commentId),username,body);
                    replyShowList.add(replyShow);
                }
            }
            return replyShowList;
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return null;
        }
    }

    public void addReply(String id,String commentId,String body){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            ReplyMapper replyMapper = sqlSession.getMapper(ReplyMapper.class);
            if (id.length() == 6){
                Reply reply = new Reply(Integer.parseInt(commentId),null,Integer.parseInt(id),body);
                replyMapper.insertAdminReply(reply);
                sqlSession.commit();
                sqlSession.close();
            }
            else {
                Reply reply = new Reply(Integer.parseInt(commentId),Integer.parseInt(id),null,body);
                replyMapper.insertUserReply(reply);
                sqlSession.commit();
                sqlSession.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
        }

        // 对于用户，经验+3
        if (id.length() != 6) {
            ExperienceCaculateService experienceCaculateService = new ExperienceCaculateService();
            experienceCaculateService.caculateExperience(Integer.parseInt(id), Constants.REPLY_EXP);
        }
    }
}
