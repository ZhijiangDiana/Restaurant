package com.restaurant.restaurant.utils;

import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.entity.User;
import org.apache.ibatis.session.SqlSession;

/**
 * 取名依托 不知道叫啥好
 * 过滤器，做两件事。
 * 1：判断你要说的话是不是带有敏感词汇
 * 2：你是不是之前被手动封禁过或者节操值太高——结果被ban发言
 *
 */
public class LegalSpeakFilter {
    private LegalSpeakFilter() {}
    private static final int INCREASE_ONCE = 1;

    /**
     * 发言前先调这个，检查这条评论是否能发出去
     * @param text
     * @param userId
     * @return pair，first是用户有没有被ban，second是发言有没有问题
     */
    public static Pair<Boolean, Boolean> isBanOrSensitive(String text, int userId) {
        Pair<Boolean, Boolean> res = new Pair<>();
        res.first = banFromSpeaking(userId);
        res.second = filterSensitiveWords(text);
        if (res.second)
            increaseIllegality(INCREASE_ONCE, userId);
        return res;
    }

    /**
     * 过滤言论
     * @param text
     * @return true代表有问题 false没问题
     */
    public static Boolean filterSensitiveWords(String text){
        return Constants.containSensitive(text);
    }

    /**
     *
     * @param id 当前准备狗叫的用户id
     * @return true禁止发言 false没问题
     */
    public static Boolean banFromSpeaking(Integer id) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectById(id);
        Integer illegality = user.getIllegality();
        Boolean forbidden = user.getForbidden();
        sqlSession.close();
        if (forbidden || illegality >= 10)
            return true;
        return false;
    }

    private static void increaseIllegality(int increasePoints, int id) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateIllegalityById(increasePoints, id);
        sqlSession.commit();
        sqlSession.close();
    }
}
