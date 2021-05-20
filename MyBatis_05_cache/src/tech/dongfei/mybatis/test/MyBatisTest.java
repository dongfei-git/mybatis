package tech.dongfei.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import tech.dongfei.mybatis.bean.Department;
import tech.dongfei.mybatis.bean.Employee;
import tech.dongfei.mybatis.dao.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MyBatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 测试一级缓存
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        SqlSession openSession02 = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee emp01 = mapper.getEmployeeById(1);
            System.out.println(emp01);

            EmployeeMapper mapper02 = openSession02.getMapper(EmployeeMapper.class);
            Employee emp02 = mapper02.getEmployeeById(1);
            System.out.println(emp02);
        } finally {
            openSession.close();
            openSession02.close();
        }
    }

    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee emp01 = mapper.getEmployeeById(1);
            System.out.println(emp01);

            Employee emp02 = mapper.getEmployeeById(3);
            System.out.println(emp02);
        } finally {
            openSession.close();
        }
    }

    /**
     * 测试二级缓存
     * @throws IOException
     */
    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        Employee emp01 = mapper.getEmployeeById(1);
        System.out.println(emp01);
        openSession.close();

        SqlSession openSession2 = sqlSessionFactory.openSession();
        EmployeeMapper mapper2 = openSession2.getMapper(EmployeeMapper.class);
        Employee emp02 = mapper2.getEmployeeById(1);
        System.out.println(emp02);
        openSession2.close();
    }

}
