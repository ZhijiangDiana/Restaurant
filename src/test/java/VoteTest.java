import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.mapper.VoteMapper;
import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class VoteTest {
    @Test
    public void jsonTest() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        VoteMapper voteMapper = sqlSession.getMapper(VoteMapper.class);

        Vote vote = voteMapper.selectById(1);
        System.out.println(vote);
        Map<String, Object> map = JSON.parseObject(vote.getResult());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
        //    System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        sqlSession.close();
    }
}
