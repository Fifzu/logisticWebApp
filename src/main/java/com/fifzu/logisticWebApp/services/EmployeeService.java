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

        Employee joker = new Employee();
        joker.setName("Volker Vertrieb");
        joker.setId(2);
        joker.setEmail("volker.vertrieb@logistics.com");
        joker.setDepartment(Department.VERTRIEB);
        joker.setEntryDate(java.sql.Date.valueOf(LocalDate.now()));
        joker.setActive(true);
        createEmployee(joker);

        Employee batman = new Employee();
        batman.setName("Georg Geschäftsführer");
        batman.setId(3);
        batman.setEmail("georg.geschaeftsfuehrung@logistics.com");
        batman.setDepartment(Department.GESCHÄFTSFÜHRUNG);
        batman.setEntryDate(java.sql.Date.valueOf(LocalDate.now()));
        batman.setActive(true);
        createEmployee(batman);
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

