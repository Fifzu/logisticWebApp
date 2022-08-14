package com.fifzu.logisticWebApp.controllers;
import com.fifzu.logisticWebApp.domain.entities.Department;
import com.fifzu.logisticWebApp.domain.entities.Employee;
import com.fifzu.logisticWebApp.services.EmployeeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class LogisticsController {

    private final EmployeeService employeeService;

    public LogisticsController(EmployeeService employeeService)
    {
        this.employeeService = employeeService;
    }
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getEmployees(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        if (employees.size()==0){
            employeeService.createDummyEmployees();
            employees = employeeService.getEmployees();
        }
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        return "employees";
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public RedirectView createEmployee(RedirectAttributes redirectAttributes, @ModelAttribute Employee employee) {
        employeeService.createEmployee(employee);
        String message = "Mitarbeiter <b>" + employee.getName() + " wurde erzeugt </b> ✨.";
        RedirectView redirectView = new RedirectView("/", true);
        redirectAttributes.addFlashAttribute("userMessage", message);
        return redirectView;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getEmployee(Model model, @PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        return "edit";
    }
    @RequestMapping(path = "/search/", method = RequestMethod.POST)
    public String filterEmployeesByDepartment(Model model,@RequestParam(name = "Department",required = false )Department department) {
        List<Employee> employees = new ArrayList<>();
        String url;
        if (department != null) {
            employees = employeeService.listEmployeesByDepartment(department);
            url="employees";
        }
            else
        {
            employees=employeeService.getEmployees();
            url= "redirect:/";

        }
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        return url;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public RedirectView updateEmployee(RedirectAttributes redirectAttributes, @PathVariable("id") Integer id, @ModelAttribute Employee employee) {
        employeeService.updateEmployee(id, employee);
        String message = " Mitarbeiter <b>" + employee.getName() + "</b> wurde " +  (employee.isActive() ? "bearbeitet " : "gelöscht ")  + " ✨.";
        RedirectView redirectView = new RedirectView("/", true);
        redirectAttributes.addFlashAttribute("userMessage", message);
        return redirectView;
    }
}
