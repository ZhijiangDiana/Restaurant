import com.restaurant.restaurant.mapper.CommentMapper;
import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.mapper.UserMapper;
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
}
