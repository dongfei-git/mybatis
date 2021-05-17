package tech.dongfei.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import tech.dongfei.mybatis.bean.Employee;
import tech.dongfei.mybatis.dao.EmployeeMapper;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //获取sqlSession实例，能直接执行依据映射的sql语句
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            Employee employee = openSession.selectOne("tech.dongfei.mybatis.dao.EmployeeMapper.getEmployeeById", 1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployeeById(1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }
}
