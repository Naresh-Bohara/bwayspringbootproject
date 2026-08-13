package com.bway.springbootproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bway.springbootproject.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(
        value = "SELECT DISTINCT e.* " +
                "FROM employee_tbl e " +
                "JOIN employee_department ed ON e.id = ed.employee_id " +
                "JOIN department_tbl d ON ed.department_id = d.id " +
                "WHERE d.dpt_name = :dptName",
        nativeQuery = true
    )
    List<Employee> findByDepartmentName(
        @Param("dptName") String dptName
    );
}