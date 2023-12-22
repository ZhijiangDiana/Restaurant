package com.restaurant.restaurant.service;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.mapper.*;
import com.restaurant.restaurant.pojo.entity.*;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class AdminService {

    public String deleteCanteenById(String id){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            CanteenMapper canteenMapper = sqlSession.getMapper(CanteenMapper.class);
            int isSuccess = canteenMapper.deleteCanteenById(Integer.parseInt(id));
            sqlSession.commit();
            List<Canteen> canteenList = canteenMapper.selectAll();
            if (isSuccess == 1){
                sqlSession.commit();
                sqlSession.close();
                return FrontEndUtils.objectToBody("删除成功","0",canteenList);
            }
            else {
                sqlSession.commit();
                sqlSession.close();
                return FrontEndUtils.objectToBody("删除失败","1",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    public String addCanteen(String name, String location, Time startTime, Time endTime, String description, byte[] image){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            CanteenMapper mapper = sqlSession.getMapper(CanteenMapper.class);
            List<Canteen> canteens = mapper.selectAll();
            for (Canteen canteen : canteens) {
                String canteenName = canteen.getName();
                if (canteenName.equals(name))
                    return FrontEndUtils.objectToBody("餐厅姓名重复","0",null);
            }
            Canteen canteen = new Canteen(name,location,startTime,endTime,description,image);
            int isSuccess = mapper.insertCanteen(canteen);
            sqlSession.commit();
            List<Canteen> canteenList = mapper.selectAll();
            sqlSession.close();
            if (isSuccess == 1){
                return FrontEndUtils.objectToBody("创建成功","0",canteenList);
            }
            else {
                return FrontEndUtils.objectToBody("创建失败","1",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }

    }

    public String updateCanteen(String id,String name, String location, Time startTime, Time endTime, String description, byte[] image,String reportCount){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try(sqlSession){
            CanteenMapper mapper = sqlSession.getMapper(CanteenMapper.class);
            List<Canteen> canteens = mapper.selectAll();
            for (Canteen canteen : canteens) {
                String canteenName = canteen.getName();
                if (canteenName.equals(name))
                    return FrontEndUtils.objectToBody("餐厅姓名重复","0",null);
            }
            Canteen canteen = new Canteen(Integer.parseInt(id),name,location,startTime,endTime,description,image,Integer.parseInt(reportCount));
            int isSuccess = mapper.updateCanteen(canteen);
            sqlSession.commit();
            List<Canteen> canteenList = mapper.selectAll();
            sqlSession.close();
            if (isSuccess == 1){
                return FrontEndUtils.objectToBody("修改成功","0",canteenList);
            }
            else {
                return FrontEndUtils.objectToBody("修改失败","1",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    /**
     * 不用再挨个判断是否为空了，如果是空前端会告知
     * @param id
     * @param username
     * @param password
     * @param experience
     * @param isForbidden
     * @param illegality
     * @return
     */
    public String updateUser(String id,String username,String password,String experience,String isForbidden,String illegality){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try(sqlSession){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//            List<User> users = userMapper.selectAll();
//            for (User user : users) {
//                if(user.getUsername().equals(username)){
//                    return FrontEndUtils.objectToBody("姓名不能重复","1",null);
//                }
//            }
            Boolean isForbiddenBool = null;
            if (isForbidden.equals("true")){
                isForbiddenBool = true;
            }
            else
                isForbiddenBool = false;
            User user = new User(Integer.parseInt(id),username,password,Integer.parseInt(experience),isForbiddenBool,Integer.parseInt(illegality));
            int isSuccess = userMapper.updateUser(user);
            sqlSession.commit();
            if (isSuccess == 1){
                List<User> userList = userMapper.selectAll();
                sqlSession.commit();
                return FrontEndUtils.objectToBody("修改成功","0",userList);
            }
            else
                return FrontEndUtils.objectToBody("修改失败","1",null);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    public String deleteUserById(String id){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int isSuccess = userMapper.deleteUserById(Integer.parseInt(id));
            sqlSession.commit();
            sqlSession.close();
            if (isSuccess == 1)
                return FrontEndUtils.objectToBody("删除成功","0",null);
            else
                return FrontEndUtils.objectToBody("删除失败","1",null);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    public String addUser(String id,String username,String password,String experience,String isForbidden,String illegality){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.selectAll();
            for (User user : userList) {
                if (user.getUserId() == Integer.parseInt(id))
                    return FrontEndUtils.objectToBody("重复用户,重新输入","1",null);
            }
            Boolean isForbiddenBool = null;
            if ("是".equals(isForbidden))
                isForbiddenBool = true;
            else
                isForbiddenBool = false;
            User user = new User(Integer.parseInt(id),username,password,Integer.parseInt(experience),isForbiddenBool,Integer.parseInt(illegality));
            userMapper.insertUser(user);
            sqlSession.commit();
            sqlSession.close();
            return FrontEndUtils.objectToBody("增添成功","0",null);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    /**
     * 这里的食堂名字不可能拿空，食堂名字要从数据库拿
     * 先委派成食堂管理员然后在师生中删除该用户
     * @param id
     * @param canteenName
     * @return
     */
    public String setUserToCanteenAdmin(String id,String canteenName){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            CanteenMapper canteenMapper = sqlSession.getMapper(CanteenMapper.class);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            CanteenAdminMapper canteenAdminMapper = sqlSession.getMapper(CanteenAdminMapper.class);
            User user = userMapper.selectById(Integer.parseInt(id));
            List<Canteen> canteenList = canteenMapper.selectAll();
            Integer appointedCanteenId = null;
            for (Canteen canteen : canteenList) {
                if (canteen.getName().equals(canteenName)) {
                    appointedCanteenId = canteen.getCanteenId();
                }
            }
            CanteenAdmin canteenAdmin = new CanteenAdmin(Integer.parseInt(id),appointedCanteenId,user.getUsername(),user.getPassword());
            canteenAdminMapper.insertCanteenAdmin(canteenAdmin);
            int isSuccess = userMapper.deleteUserById(Integer.parseInt(id));
            sqlSession.commit();
            sqlSession.close();
            if (isSuccess == 1)
                return FrontEndUtils.objectToBody("委任成功","0",null);
            else
                return FrontEndUtils.objectToBody("委任失败","1",null);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    public String deleteDishCommentById(String id){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            DishCommentMapper dishMapper = sqlSession.getMapper(DishCommentMapper.class);
            int isSuccess = dishMapper.deletedishCommentById(Integer.parseInt(id));
            sqlSession.commit();
            sqlSession.close();
            if (isSuccess == 1)
                return FrontEndUtils.objectToBody("删除成功","0",null);
            else
                return FrontEndUtils.objectToBody("删除失败","1",null);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    public String updateDishComment(String dishCommentId,String userId,String dishId,String score,String tittle,String body,byte[] image){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            DishCommentMapper dishCommentMapper = sqlSession.getMapper(DishCommentMapper.class);
            DishComment dishComment = new DishComment(Integer.parseInt(dishCommentId),Integer.parseInt(userId),Integer.parseInt(dishId),Double.parseDouble(score),tittle,body,image);
            int isSuccess = dishCommentMapper.updateDishComment(dishComment);
            sqlSession.commit();
            sqlSession.close();
            if (isSuccess == 1)
                return FrontEndUtils.objectToBody("修改成功","0",null);
            else
                return FrontEndUtils.objectToBody("修改失败","1",null);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    public String updateComment(String commentId,String userId,String title,String body,byte[] image,Date publishTime,String likes){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            Comment comment = new Comment(Integer.parseInt(commentId),Integer.parseInt(userId),title,body,image,publishTime,Integer.parseInt(likes));
            CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
            int isSuccess = commentMapper.updateComment(comment);
            sqlSession.commit();
            if (isSuccess == 1)
                return FrontEndUtils.objectToBody("修改成功","0",null);
            else
                return FrontEndUtils.objectToBody("修改失败","1",null);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }
    public String selectCommentById(String commentId){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try {
            CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
            Comment comment = commentMapper.selectById(Integer.parseInt(commentId));
            sqlSession.commit();
            sqlSession.close();

                return FrontEndUtils.objectToBody("查询成功","0",comment);

        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
    }
    }
    public String deleteCommentById(String commentId){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
            int isSuccess = commentMapper.deleteCommentById(Integer.parseInt(commentId));
            sqlSession.commit();
            sqlSession.close();
            if (isSuccess == 1){
                return FrontEndUtils.objectToBody("删除成功","0",null);
            }
            else
                return FrontEndUtils.objectToBody("删除失败","1",null);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }
    
    public String SelectUserById(int id){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        
        try (sqlSession) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectById(id);

            sqlSession.commit();
            return FrontEndUtils.objectToBody("查询成功","0",user);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            return FrontEndUtils.objectToBody("查询失败", "1", null);
        }
    }

    public String updateReply(String replyId,String commentId,String userId,String canteenAdminId,String body){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            ReplyMapper replyMapper = sqlSession.getMapper(ReplyMapper.class);
            Reply reply = null;
            if (userId == null)
                reply = new Reply(Integer.parseInt(replyId),Integer.parseInt(commentId),null,Integer.parseInt(canteenAdminId),body);
            if (canteenAdminId == null)
                reply = new Reply(Integer.parseInt(replyId),Integer.parseInt(commentId),Integer.parseInt(userId),null,body);
            int isSuccess = replyMapper.updateReply(reply);
            sqlSession.commit();
            sqlSession.close();
            if (isSuccess == 1)
                return FrontEndUtils.objectToBody("修改成功","0",null);
            else
                return FrontEndUtils.objectToBody("修改失败","1",null);

        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    public String deleteReplyById(String replyId){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            ReplyMapper replyMapper = sqlSession.getMapper(ReplyMapper.class);
            int isSuccess = replyMapper.deleteReplyById(Integer.parseInt(replyId));
            sqlSession.commit();
            sqlSession.close();
            if (isSuccess == 1)
                return FrontEndUtils.objectToBody("删除成功","0",null);
            else
                return FrontEndUtils.objectToBody("删除失败","1",null);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }
}
