package com.atguigu.mybatis.test;


import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 1、接口式编程
 * 	原生：		Dao		====>  DaoImpl
 * 	mybatis：	Mapper	====>  xxMapper.xml
 *
 * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
 * 3、SqlSession和connection一样她都是非线程安全。每次使用都应该去获取新的对象。
 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
 * 		（将接口和xml进行绑定）
 * 		EmployeeMapper empMapper =	sqlSession.getMapper(EmployeeMapper.class);
 * 5、两个重要的配置文件：
 * 		mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
 * 		sql映射文件：保存了每一个sql语句的映射信息：
 * 					将sql抽取出来。
 *
 *
 * @author lfy
 *
 */
public class MybatisTest {

    /**
     * 1.根据xml配置文件(全局配置文件)创建一个SqlSessionFactory对象
     * @throws IOException
     */
    @Test
    public void test() throws IOException  {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取sqlSession实例，能够直接执行已经映射的sql语句
        SqlSession sqlSession =sqlSessionFactory.openSession();
        //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.selectEmp(1);
        System.out.println(employee);
        sqlSession.close();
    }

}
