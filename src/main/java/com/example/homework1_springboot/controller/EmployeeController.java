package com.example.homework1_springboot.controller;

import com.example.homework1_springboot.exceptions.EmployeeException;
import com.example.homework1_springboot.model.Employee;
import com.example.homework1_springboot.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Collection<Employee> getAllEmployees(){
        return this.employeeService.getAllEmployees();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) throws EmployeeException {
        return this.employeeService.addEmployee(employee);
    }

    @GetMapping("salary/sum")
    public int getSalarySum(){
        return this.employeeService.getSalarySum();
    }

    @GetMapping("salary/min")
    public List<Employee> getEmployeesWithMinSalary(){
        return this.employeeService.getEmployeesWithMinSalary();
    }

    @GetMapping("salary/max")
    public List<Employee> getEmployeesWithMaxSalary(){
        return this.employeeService.getEmployeesWithMaxSalary();
    }

    @GetMapping("salary/moreThanAvarage")
    public List<Employee> getEmployeesWhoEarnMoreThanAverageSalary(){
        return this.employeeService.getEmployeesWhoEarnMoreThanAverageSalary();
    }
}
