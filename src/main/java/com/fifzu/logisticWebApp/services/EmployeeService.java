package com.fifzu.logisticWebApp.services;

import com.fifzu.logisticWebApp.domain.entities.Department;
import com.fifzu.logisticWebApp.domain.entities.Employee;
import com.fifzu.logisticWebApp.domain.repositories.EmployeeRepository;
import com.fifzu.logisticWebApp.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<Employee> listEmployeesByDepartment(Department department) {
        List<Employee> allEmployees = getEmployees();
        List<Employee> employees = new ArrayList<>();

        for (Employee employee: allEmployees)
        {
            if(employee.getDepartment().equals(department)){
                employees.add(employee);
            }
        }
        return employees;
    }

    public void createDummyEmployees() {

        Employee ludwig = new Employee();
        ludwig.setName("Ludwig Lagerarbeiter");
        ludwig.setId(1);
        ludwig.setEmail("ludwig.lagerarbeiter@logistics.com");
        ludwig.setDepartment(Department.LAGER);
        ludwig.setEntryDate(java.sql.Date.valueOf(LocalDate.now()));
        ludwig.setActive(true);
        createEmployee(ludwig);

        Employee volker = new Employee();
        volker.setName("Volker Vertrieb");
        volker.setId(2);
        volker.setEmail("volker.vertrieb@logistics.com");
        volker.setDepartment(Department.VERTRIEB);
        volker.setEntryDate(java.sql.Date.valueOf(LocalDate.now()));
        volker.setActive(true);
        createEmployee(volker);

        Employee georg = new Employee();
        georg.setName("Georg Geschäftsführer");
        georg.setId(3);
        georg.setEmail("georg.geschaeftsfuehrer@logistics.com");
        georg.setDepartment(Department.GESCHÄFTSFÜHRUNG);
        georg.setEntryDate(java.sql.Date.valueOf(LocalDate.now()));
        georg.setActive(true);
        createEmployee(georg);
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

