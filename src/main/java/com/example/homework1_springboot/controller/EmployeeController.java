package com.example.homework1_springboot.controller;

import com.example.homework1_springboot.exceptions.EmployeeException;
import com.example.homework1_springboot.model.Employee;
import com.example.homework1_springboot.record.EmployeeRequest;
import com.example.homework1_springboot.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees(){
        return this.employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) throws EmployeeException {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employees/salary/sum")
    public int getSalarySum(){
        return this.employeeService.getSalarySum();
    }

    @GetMapping("/employees/salary/min")
    public List<Employee> getEmployeesWithMinSalary(){
        return this.employeeService.getEmployeesWithMinSalary();
    }

    @GetMapping("/employees/salary/max")
    public List<Employee> getEmployeesWithMaxSalary(){
        return this.employeeService.getEmployeesWithMaxSalary();
    }

    @GetMapping("/employees/salary/moreThanAvarage")
    public List<Employee> getEmployeesWhoEarnMoreThanAverageSalary(){
        return this.employeeService.getEmployeesWhoEarnMoreThanAverageSalary();
    }
}
