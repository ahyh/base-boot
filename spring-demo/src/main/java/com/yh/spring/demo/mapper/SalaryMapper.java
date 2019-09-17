package com.yh.spring.demo.mapper;

import com.yh.spring.demo.pojo.Salary;
import com.yh.spring.demo.pojo.SalaryQuery;

import java.util.List;

/**
 * SalaryMapper
 *
 * @author yanhuan
 */
public interface SalaryMapper {

    Integer insert(Salary salary);

    Integer update(Salary salary);

    Integer delete(Long id);

    List<Salary> findSalaryByCondition(SalaryQuery salaryQuery);
}
