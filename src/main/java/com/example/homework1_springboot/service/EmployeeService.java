package com.example.homework1_springboot.service;

import com.example.homework1_springboot.exceptions.EmployeeException;
import com.example.homework1_springboot.model.Employee;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    private boolean checkEmployee(String line){
        return !StringUtils.isEmpty(line) && !StringUtils.isBlank(line);
    }

    public Employee addEmployee(Employee employee) throws EmployeeException {
        if (!checkEmployee(employee.getName())) throw new EmployeeException("У сотрудника нет имени!");
        if (!checkEmployee(employee.getLastname())) throw new EmployeeException("У сотрудника нет фамилии!");
        if (employee.getDepartament() == null) throw new EmployeeException("У сотрудника не указан отдел!");
        if (employee.getSalary() <= 0 || employee.getSalary() == null) throw new EmployeeException("У сотрудника неправильно указана зарплата!");
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum(){
        return employees.values().stream()
                .mapToInt(e -> e.getSalary())
                .sum();
    }

    public List<Employee> getEmployeesWithMinSalary(){
        int minSalary = employees.values().stream()
                .min(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        return employees.values().stream()
                .filter(e -> e.getSalary() == minSalary)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesWithMaxSalary(){
        int maxSalary = employees.values().stream()
                .max(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        return employees.values().stream()
                .filter(e -> e.getSalary() == maxSalary)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesWhoEarnMoreThanAverageSalary(){
        int averageSalary = (int) ((employees.values().stream()
                .mapToInt(e -> e.getSalary())
                .sum()) / (employees.values().stream()
                .count()));
        return employees.values().stream()
                .filter(e -> e.getSalary() > averageSalary)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeService that = (EmployeeService) o;
        return Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employees);
    }
}
