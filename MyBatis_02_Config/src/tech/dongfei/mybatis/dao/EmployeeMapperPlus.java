package tech.dongfei.mybatis.dao;

import tech.dongfei.mybatis.bean.Employee;

public interface EmployeeMapperPlus {

    public Employee getEmpById(Integer id);

    public Employee getEmpAndDept(Integer id);

    public Employee getEmpByIdStep(Integer id);

}
