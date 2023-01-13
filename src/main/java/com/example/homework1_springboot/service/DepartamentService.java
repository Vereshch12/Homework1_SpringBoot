package com.example.homework1_springboot.service;

import com.example.homework1_springboot.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartamentService {
    private final EmployeeService employeeService;

    public DepartamentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Collection<Employee> getAllEmployeesOfDepartament (Integer departamentId){
        return employeeService.getAllEmployees().stream().
                filter(employee -> employee.getDepartament() == departamentId).collect(Collectors.toList());
    }

    public Integer getSalarySumOfDepartament(Integer departamentId){
        return this.getAllEmployeesOfDepartament(departamentId).stream()
                .mapToInt(e -> e.getSalary())
                .sum();
    }

    public List<Employee> getEmployeesWithMaxSalaryOfDepartament(Integer departamentId){
        int maxSalary = this.getAllEmployeesOfDepartament(departamentId).stream()
                .max(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        return this.getAllEmployeesOfDepartament(departamentId).stream()
                .filter(e -> e.getSalary() == maxSalary)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesWithMinSalaryOfDepartament(Integer departamentId){
        int minSalary = this.getAllEmployeesOfDepartament(departamentId).stream()
                .min(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        return this.getAllEmployeesOfDepartament(departamentId).stream()
                .filter(e -> e.getSalary() == minSalary)
                .collect(Collectors.toList());
    }



    public Map<Integer, Collection<Employee>> getEmployeesAcrossAllDepartments (){
        return getAllDepartments().stream().collect(Collectors.toMap(d -> d, this::getAllEmployeesOfDepartament));
    }

    private Set<Integer> getAllDepartments(){
        return employeeService.getAllEmployees().stream().
                map(Employee::getDepartament).
                collect(Collectors.toSet());
    }

}
