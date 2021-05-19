package tech.dongfei.mybatis.dao;

import tech.dongfei.mybatis.bean.Department;

public interface DepartmentMapper {
    public Department getDeptById(Integer id);

    public Department getDeptByIdStep(Integer id);
}
