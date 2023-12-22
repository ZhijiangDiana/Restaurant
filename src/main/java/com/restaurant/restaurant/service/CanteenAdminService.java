package com.restaurant.restaurant.service;

import com.restaurant.restaurant.mapper.CanteenAdminMapper;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CanteenAdminService {
    public String selectAllCanteenAdmin(){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            CanteenAdminMapper canteenAdminMapper = sqlSession.getMapper(CanteenAdminMapper.class);
            List<CanteenAdmin> canteenAdminList = canteenAdminMapper.selectAll();
            return FrontEndUtils.objectToBody(null,"0",canteenAdminList);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    public String selectCanteenAdminById(String canteenAdminId){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            CanteenAdminMapper canteenAdminMapper = sqlSession.getMapper(CanteenAdminMapper.class);
            CanteenAdmin canteenAdmin = canteenAdminMapper.selectById(Integer.parseInt(canteenAdminId));
            return FrontEndUtils.objectToBody(null,"0",canteenAdmin);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    public String deleteCanteenAdminById(String canteenAdminId){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            CanteenAdminMapper canteenAdminMapper = sqlSession.getMapper(CanteenAdminMapper.class);
            int isSuccess = canteenAdminMapper.deleteById(Integer.parseInt(canteenAdminId));
            sqlSession.commit();
            if (isSuccess == 1){
                return FrontEndUtils.objectToBody("删除成功","0",null);
            }else {
                return FrontEndUtils.objectToBody("删除失败","0",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    public String updateCanteenAdmin(String canteenAdminId,String canteenId,String username,String password){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            CanteenAdminMapper canteenAdminMapper = sqlSession.getMapper(CanteenAdminMapper.class);
            CanteenAdmin canteenAdmin = new CanteenAdmin(Integer.parseInt(canteenAdminId),Integer.parseInt(canteenId),username,password);
            int isSuccess = canteenAdminMapper.updateCanteenAdmin(canteenAdmin);
            sqlSession.commit();
            if (isSuccess == 1){
            return FrontEndUtils.objectToBody("修改成功","0",null);
            }else {
                return FrontEndUtils.objectToBody("修改失败","0",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }
}
