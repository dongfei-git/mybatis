package tech.dongfei.mybatis.dao;

import tech.dongfei.mybatis.bean.Employee;

public interface EmployeeMapper {

    public Employee getEmployeeById(Integer id);

}
