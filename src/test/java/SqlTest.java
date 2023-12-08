import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SqlTest {
    @Test
    public void UserMapperTest() {
        // DAO层封装食用方法：
        // 使用SqlSessionFactoryUtils创建SqlSession对象，SqlSessionFactory必须单例！！！
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        // 获取表对应的Mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

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
}
