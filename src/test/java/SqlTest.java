import com.restaurant.restaurant.mapper.*;
import com.restaurant.restaurant.pojo.entity.*;
import com.restaurant.restaurant.utils.Constants;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class SqlTest {
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    @Test
    public void UserMapperTest() {
        // DAO层封装食用方法：
        // 使用SqlSessionFactoryUtils创建SqlSession对象，SqlSessionFactory必须单例！！！
        // 获取表对应的Mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        // 执行SQL语句
//        List<User> userList = userMapper.selectAll();
//        for (User u : userList) {
//            System.out.println(u);
//        }
//        User diana = userMapper.selectById(1);
//        System.out.println(diana);
//        User xianBei = userMapper.selectById(114514);
//        System.out.println(xianBei);
        userMapper.updateIllegalityById(114514,1);
        sqlSession.commit();
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    @Test
    public void CommentMapperTest(){
        CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
//        mapper.updateLikeById(100,18);
//        sqlSession.commit();
        List<Comment> comments = mapper.selectAll();
        System.out.println(comments);
        for (Comment comment1 : comments){
            System.out.println(comment1);
        }

    }

    @Test
    public void AdminMapperTest(){
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Admin admin = new Admin(1111,"原神哥","123456");
//        mapper.insertAdmin(admin);
//        sqlSession.commit();
        List<Reply> replies = mapper.selectReplyById(1);
        List<DishComment> dishComments = mapper.selectDishCommentById(1);
        for (Reply reply : replies) {
            System.out.println(reply);
        }
        for (DishComment dishComment : dishComments) {
            System.out.println(dishComment);
        }
    }

    @Test
    public void CanteenAdminMapperTest(){
        CanteenAdminMapper mapper = sqlSession.getMapper(CanteenAdminMapper.class);
//        CanteenAdmin canteenAdmin = new CanteenAdmin(111111,1,"拟跌","123456");
//        mapper.insertCanteenAdmin(canteenAdmin);
//        sqlSession.commit();
        List<Reply> replies = mapper.selectReplyById(111111);
        for (Reply reply : replies) {
            System.out.println(reply);
        }
        CanteenAdmin canteenAdmin = mapper.selectById(111111);
        System.out.println(canteenAdmin);
    }

    @Test
    public void ReplyMapperTest(){
        ReplyMapper replyMapper = sqlSession.getMapper(ReplyMapper.class);
        //Reply reply = new Reply(18,1,null,"日你的哥");
        //replyMapper.insertUserReply(reply);
        //sqlSession.commit();

//        Reply reply = new Reply(18,null,111111,"什么时候能几波做完这个b项目");
//        replyMapper.insertAdminReply(reply);
//        sqlSession.commit();
        List<Reply> replies = replyMapper.selectByCommentId(18);
        for (Reply reply1 : replies){
            System.out.println(reply1);
        }
    }

    @Test
    public void sensitiveFilter(){
        Set<String> sensitivewords = Constants.SENSITIVEWORDS;
        String s = "aewf";
        System.out.println(Constants.containSensitive(s));
    }

    @Test
    public void messageTest(){
        MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
        List<Message> messages = mapper.selectById(1);
        for (Message message : messages) {
            System.out.println(message);
        }
    }
}
