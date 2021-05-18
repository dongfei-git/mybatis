package tech.dongfei.mybatis.dao;

import tech.dongfei.mybatis.bean.Employee;

public interface EmployeeMapper {

    public Employee getEmployeeById(Integer id);

    public void addEmp(Employee employee);

    public void updateEmp(Employee employee);

    public Long deleteEmpById(Integer id);

}
