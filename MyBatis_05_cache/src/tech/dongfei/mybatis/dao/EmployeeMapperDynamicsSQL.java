package tech.dongfei.mybatis.dao;

import org.apache.ibatis.annotations.Param;
import tech.dongfei.mybatis.bean.Employee;

import java.util.List;

public interface EmployeeMapperDynamicsSQL {

    public List<Employee> getEmpsByConditionIf(Employee employee);

    public List<Employee> getEmpsByConditionTrim(Employee employee);

    public List<Employee> getEmpsByConditionChoose(Employee employee);

    public void updateEmp(Employee employee);

    public List<Employee> getEmpsByConditionForeach(@Param("ids")List<Integer> ids);

    public void addEmps(@Param("emps")List<Employee> emps);

    public List<Employee> getEmpsTestInnerParameter(Employee employee);
}
