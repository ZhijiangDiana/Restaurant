import com.restaurant.restaurant.mapper.*;
import com.restaurant.restaurant.pojo.entity.Admin;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

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
        List<User> userList = userMapper.selectAll();
        for (User u : userList) {
            System.out.println(u);
        }
        User diana = userMapper.selectById(1);
        System.out.println(diana);
        User xianBei = userMapper.selectById(114514);
        System.out.println(xianBei);

        sqlSession.close();
    }

    @Test
    public void CommentMapperTest(){
        CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
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
        mapper.insertAdmin(admin);
        sqlSession.commit();
    }

    @Test
    public void CanteenAdminMapperTest(){
        CanteenAdminMapper mapper = sqlSession.getMapper(CanteenAdminMapper.class);
//        CanteenAdmin canteenAdmin = new CanteenAdmin(111111,1,"拟跌","123456");
//        mapper.insertCanteenAdmin(canteenAdmin);
//        sqlSession.commit();
        CanteenAdmin canteenAdmin = mapper.selectById(111111);
        System.out.println(canteenAdmin);
    }
}
