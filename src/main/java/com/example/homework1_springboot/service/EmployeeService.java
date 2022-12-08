package com.example.homework1_springboot.service;

import com.example.homework1_springboot.exceptions.EmployeeException;
import com.example.homework1_springboot.model.Employee;
import com.example.homework1_springboot.record.EmployeeRequest;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    private boolean checkEmployee(String line){
        return !StringUtils.isEmpty(line) && !StringUtils.isBlank(line);
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) throws EmployeeException {
        if (!checkEmployee(employeeRequest.getName())) throw new EmployeeException("У сотрудника нет имени!");
        if (!checkEmployee(employeeRequest.getLastname())) throw new EmployeeException("У сотрудника нет фамилии!");
        if (!checkEmployee(employeeRequest.getDepartament())) throw new EmployeeException("У сотрудника не указан отдел!");
        if (employeeRequest.getSalary() <= 0) throw new EmployeeException("У сотрудника неправильно указана зарплата!");
        Employee employee = new Employee(StringUtils.capitalize(employeeRequest.getName()),
                StringUtils.capitalize(employeeRequest.getLastname()),
                employeeRequest.getDepartament(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum(){
        return employees.values().stream()
                .mapToInt(e -> e.getSalary())
                .sum();
    }

    public Stream<Employee> getEmployeesWithMinSalary(){
        int minSalary = employees.values().stream()
                .min(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        return employees.values().stream()
                .filter(e -> e.getSalary() == minSalary);
    }

    public Stream<Employee> getEmployeesWithMaxSalary(){
        int maxSalary = employees.values().stream()
                .max(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        return employees.values().stream()
                .filter(e -> e.getSalary() == maxSalary);
    }

    public Stream<Employee> getEmployeesWhoEarnMoreThanAverageSalary(){
        int averageSalary = (int) ((employees.values().stream()
                .mapToInt(e -> e.getSalary())
                .sum()) / (employees.values().stream()
                .count()));
        return employees.values().stream()
                .filter(e -> e.getSalary() > averageSalary);
    }
}
