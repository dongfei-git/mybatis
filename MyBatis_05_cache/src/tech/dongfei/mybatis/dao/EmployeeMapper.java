package tech.dongfei.mybatis.dao;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import tech.dongfei.mybatis.bean.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    public Employee getEmployeeById(Integer id);

    public void addEmp(Employee employee);

    public void updateEmp(Employee employee);

    public Long deleteEmpById(Integer id);

    //    public Employee getEmpByIdAndLastName(Integer id, String lastName);
    public Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    public Employee getEmpByMap(Map<String, Object> map);

    public List<Employee> getEmpsByLastNameLike(String lastName);

    public Map<String, Object> getEmpByIdReturnMap(Integer id);

    @MapKey("id")  // 封装键是这条记录的主键
    public Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String lastName);

}
