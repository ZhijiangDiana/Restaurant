package com.restaurant.restaurant.service;

import com.restaurant.restaurant.mapper.CanteenAdminMapper;
import com.restaurant.restaurant.mapper.CommentMapper;
import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.ShowAllPublishedInfo;
import com.restaurant.restaurant.pojo.entity.*;
import com.restaurant.restaurant.utils.Constants;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SensitiveCommentFilter;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * 评论展示业务逻辑,分别对应默认、按热度、升序以及降序。
 */
public class CommentService {
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    CanteenAdminMapper canteenAdminMapper = sqlSession.getMapper(CanteenAdminMapper.class);
    public String getCommentList(String type) {
        sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        mapper = sqlSession.getMapper(CommentMapper.class);
        List<Comment> comments = null;
        // 默认情况
        if (Constants.COMMENTS_DEFAULT.equals(type))
            comments = mapper.selectAll();

        // 按照热度降序
        else if (Constants.COMMENTS_LIKES.equals(type))
            comments = mapper.selectAllByLikes();

        // 按照时间降序
        else if (Constants.COMMENTS_TIME_DESC.equals(type))
            comments = mapper.selectAllDesc();

        // 按照时间升序
        else if (Constants.COMMENTS_TIME_ASC.equals(type))
            comments = mapper.selectAllAsc();

        if (comments == null)
            return FrontEndUtils.objectToBody("暂无评论","1",null);
        else
            return FrontEndUtils.objectToBody(null,"0",comments);
    }

    public String insertComment(User user, String title, String body, String img){
        /**
         * 要改 userid从session里拿
         */
        user = new User();
        user.setForbidden(false);
        user.setUserId(2);
        if(user.getForbidden() == true){
            return FrontEndUtils.objectToBody("由于过往潜在不文明行为被禁止评论","1",null);
        }
        else {
            if (title == null || body == null || title == "" || body == ""){
                return FrontEndUtils.objectToBody("内容为空,请补充完整","1",null);
            }
            else {
                if (SensitiveCommentFilter.commentFilter(body) == true){
                    return FrontEndUtils.objectToBody("涉及敏感发言，请谨言慎行","1",null);
                }
                else {
                    Integer userId = user.getUserId();
                    if (img == null){
                        Comment comment = new Comment(userId,title,body,null,new Date(),0);
                        mapper.insertComment(comment);
                        // 更新经验
                        ExperienceCaculateService experienceCaculateService = new ExperienceCaculateService();
                        Integer experience = experienceCaculateService.caculateExperience(userId,Constants.COMMUNITY_EXP);

                        sqlSession.commit();
                        return FrontEndUtils.objectToBody("发布成功","0",comment);
                    }
                    img = img.substring(img.indexOf(",") + 1);
                    byte[] imageBytes = Base64.getDecoder().decode(img);
                    Comment comment = new Comment(userId,title,body,imageBytes,new Date(),0);
                    mapper.insertComment(comment);
                    sqlSession.commit();
                    return FrontEndUtils.objectToBody("发布成功","0",null);
                }
            }
        }
    }

    public String getUserInfoById(String id){
        ShowAllPublishedInfo showAllPublishedInfo = null;
        if(id.length() == 6){
            CanteenAdmin canteenAdmin = canteenAdminMapper.selectById(Integer.parseInt(id));
            List<Reply> replies = canteenAdminMapper.selectReplyById(Integer.parseInt(id));
            showAllPublishedInfo = new ShowAllPublishedInfo(canteenAdmin,replies);
        }
        else {
            User user = userMapper.selectById(Integer.parseInt(id));
            List<Comment> comments = userMapper.selectCommentById(Integer.parseInt(id));
            List<Reply> replies = userMapper.selectReplyById(Integer.parseInt(id));
            List<DishComment> dishComments = userMapper.selectDishCommentById(Integer.parseInt(id));
            showAllPublishedInfo = new ShowAllPublishedInfo(user,comments,replies,dishComments);
        }
        return FrontEndUtils.objectToBody("此人信息","0",showAllPublishedInfo);
    }

    // TODO:还没考虑一直点 不过不考虑也可以 跟贴吧一样

    /**
     * 点赞
     * @param flag 0代表取消 1代表增加
     */
    public String thumbup(String flag,String commentId){
        if ("1".equals(flag)){
            Comment comment = mapper.selectById(Integer.parseInt(commentId));
            Integer likes = comment.getLikes();
            likes += 1;
            System.out.println(likes);
            mapper.updateLikeById(likes,Integer.parseInt(commentId));
            sqlSession.commit();
            return FrontEndUtils.objectToBody("点赞成功","0",null);
        }
        else if ("0".equals(flag)){
            Comment comment = mapper.selectById(Integer.parseInt(commentId));
            Integer likes = comment.getLikes();
            likes -= 1;
            mapper.updateLikeById(likes,Integer.parseInt(commentId));
            sqlSession.commit();
            return FrontEndUtils.objectToBody("取消成功","0",null);
        }
        else {
            return FrontEndUtils.objectToBody("别想通过url动坏脑筋","1",null);
        }
    }

}
