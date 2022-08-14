package com.fifzu.logisticWebApp.domain.repositories;

import com.fifzu.logisticWebApp.domain.entities.Department;
import com.fifzu.logisticWebApp.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByActiveOrderByIdDesc(boolean active);
    Optional<Employee> findByIdAndActive(Integer id, boolean active);
}
