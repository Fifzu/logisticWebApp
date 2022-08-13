package com.fifzu.logisticWebApp.services;

import com.fifzu.logisticWebApp.domain.entities.Employee;
import com.fifzu.logisticWebApp.domain.repositories.EmployeeRepository;
import com.fifzu.logisticWebApp.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployee(Integer id) {
        return employeeRepository.findByIdAndActive(id, true).orElseThrow(NotFoundException::new);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAllByActiveOrderByIdDesc(true);
    }

    public Employee updateEmployee(Integer id, Employee request) {
        Employee fromDb = getEmployee(id);
        fromDb.setName(request.getName());
        fromDb.setDepartment(request.getDepartment());
        fromDb.setEmail(request.getEmail());
        fromDb.setEntryDate(request.getEntryDate());
        fromDb.setActive(request.isActive());
        return employeeRepository.save(fromDb);
    }
}

