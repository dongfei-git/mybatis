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

    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperAnnotation employeeMapperAnnotation = openSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = employeeMapperAnnotation.getEmpById(1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }

    /**
     * 测试增删改
     * @throws IOException
     */
    @Test
    public void test04() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();  //获取到的SqlSession不会自动提交
        // SqlSession openSession = sqlSessionFactory.openSession(true);  //获取到的SqlSession会自动提交

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            Employee employee = new Employee(null, "jerry", "jerry@localhost", "F");
            mapper.addEmp(employee);
            System.out.println(employee.getId());

//            mapper.updateEmp(employee);
//            Long aLong = mapper.deleteEmpById(2);
//            System.out.println(aLong);

            openSession.commit();  // 手动提交
        } finally {
            openSession.close();
        }

    }

    @Test
    public void test05() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpByIdAndLastName(3, "jerry");
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test06() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            Map<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("lastName", "jerry");

            Employee employee = mapper.getEmpByMap(map);
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test07() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            List<Employee> employeeList = mapper.getEmpsByLastNameLike("%e%");
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test08() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = mapper.getEmpByIdReturnMap(1);
            System.out.println(map);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test09() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Map<Integer, Employee> map = mapper.getEmpByLastNameLikeReturnMap("%e%");
            System.out.println(map);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test10() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            Employee emp = mapper.getEmpById(1);
            System.out.println(emp);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test11() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmpAndDept(1);
            System.out.println(employee);
            System.out.println(employee.getDepartment());
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test12() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            Employee empAndDept = mapper.getEmpByIdStep(3);
            System.out.println(empAndDept.getLastName());
            System.out.println(empAndDept.getDepartment().getDepartmentName());
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test13() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
            Department deptByIdStep = mapper.getDeptByIdStep(3);
            System.out.println(deptByIdStep);
            System.out.println(deptByIdStep.getEmployees());
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test14() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapperDynamicsSQL mapper = openSession.getMapper(EmployeeMapperDynamicsSQL.class);
            Employee employee = new Employee(null, "%e%", "jerry@localhost", null);
            List<Employee> emps = mapper.getEmpsByConditionIf(employee);
            for (Employee emp : emps) {
                System.out.println(emp);
            }
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test15() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapperDynamicsSQL mapper = openSession.getMapper(EmployeeMapperDynamicsSQL.class);
            Employee employee = new Employee(null, "%e%", "jerry@localhost", null);
            List<Employee> emps = mapper.getEmpsByConditionTrim(employee);
            for (Employee emp : emps) {
                System.out.println(emp);
            }
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test16() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapperDynamicsSQL mapper = openSession.getMapper(EmployeeMapperDynamicsSQL.class);
            Employee employee = new Employee(null, null, null, null);
            List<Employee> emps = mapper.getEmpsByConditionChoose(employee);
            for (Employee emp : emps) {
                System.out.println(emp);
            }
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test17() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeMapperDynamicsSQL mapper = openSession.getMapper(EmployeeMapperDynamicsSQL.class);
            Employee employee = new Employee(1, "jack", null, "M");
            mapper.updateEmp(employee);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test18() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeMapperDynamicsSQL mapper = openSession.getMapper(EmployeeMapperDynamicsSQL.class);
            List<Employee> emps = mapper.getEmpsByConditionForeach(Arrays.asList(1, 3));
            for (Employee emp : emps) {
                System.out.println(emp);
            }
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test19() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeMapperDynamicsSQL mapper = openSession.getMapper(EmployeeMapperDynamicsSQL.class);

            List<Employee> emps = new ArrayList<>();
            emps.add(new Employee(null, "smith", "smith@163.com","F",new Department(1)));
            emps.add(new Employee(null, "allen", "allen@163.com","F",new Department(2)));

            mapper.addEmps(emps);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test20() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        try {
            EmployeeMapperDynamicsSQL mapper = openSession.getMapper(EmployeeMapperDynamicsSQL.class);
            List<Employee> emps = mapper.getEmpsTestInnerParameter(new Employee(null,"e",null,null));
            for (Employee emp : emps) {
                System.out.println(emp);
            }
        } finally {
            openSession.close();
        }
    }
}
