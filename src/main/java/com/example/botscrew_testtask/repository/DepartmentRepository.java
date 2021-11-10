package com.example.botscrew_testtask.repository;

import com.example.botscrew_testtask.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department getDepartmentByDepartmentName(String departmentName);

}
