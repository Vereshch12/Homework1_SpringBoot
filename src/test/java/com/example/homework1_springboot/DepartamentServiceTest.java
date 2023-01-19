package com.example.homework1_springboot;

import com.example.homework1_springboot.model.Employee;
import com.example.homework1_springboot.service.DepartamentService;
import com.example.homework1_springboot.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartamentServiceTest {
    @Mock
    private EmployeeService employeeServiceForTest;
    @InjectMocks
    private DepartamentService departamentServiceForTest;

    private Collection<Employee> actualEmployees;

    @BeforeEach
    public void setUp(){
        Employee employee1 = new Employee ("testName1", "testSurname1", 1, 1000);
        Employee employee2 = new Employee("testName2", "testSurname2", 1, 2000);
        Employee employee3 = new Employee("testName3", "testSurname3", 1, 1000);
        Employee employee4 = new Employee("testName4", "testSurname4", 2, 4000);
        Employee employee5 = new Employee("testName5", "testSurname5", 3, 5000);
        actualEmployees = new ArrayList<>(List.of(employee1,employee2,employee3,employee4,employee5));
        when(employeeServiceForTest.getAllEmployees()).thenReturn(actualEmployees);
    }

    @Test
    public void getAllEmployeesOfDepartamentTest(){
        Collection<Employee> actual = actualEmployees.stream().
                filter(employee -> employee.getDepartament() == 1).collect(Collectors.toList());
        Collection<Employee> expected = departamentServiceForTest.getAllEmployeesOfDepartament(1);
        assertEquals(expected,actual);
    }

    @Test
    public void getSalarySumOfDepartamentTest(){
        Integer actual = (actualEmployees.stream().
                filter(employee -> employee.getDepartament() == 1).collect(Collectors.toList())).stream().
                mapToInt(e -> e.getSalary()).sum();
        Integer expected = departamentServiceForTest.getSalarySumOfDepartament(1);
        assertEquals(expected,actual);
    }

    @Test
    public void getEmployeesWithMaxSalaryOfDepartamentTest(){
        int maxSalary = (actualEmployees.stream().
                filter(employee -> employee.getDepartament() == 1).collect(Collectors.toList())).stream()
                .max(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        List<Employee> actual = (actualEmployees.stream().
                filter(employee -> employee.getDepartament() == 1).collect(Collectors.toList())).stream()
                .filter(e -> e.getSalary() == maxSalary)
                .collect(Collectors.toList());
        List<Employee> expected = departamentServiceForTest.getEmployeesWithMaxSalaryOfDepartament(1);
        assertEquals(expected,actual);
    }

    @Test
    public void getEmployeesWithMinSalaryOfDepartamentTest(){
        int minSalary = (actualEmployees.stream().
                filter(employee -> employee.getDepartament() == 1).collect(Collectors.toList())).stream()
                .min(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        List<Employee> actual = (actualEmployees.stream().
                filter(employee -> employee.getDepartament() == 1).collect(Collectors.toList())).stream()
                .filter(e -> e.getSalary() == minSalary)
                .collect(Collectors.toList());
        List<Employee> expected = departamentServiceForTest.getEmployeesWithMinSalaryOfDepartament(1);
        assertEquals(expected,actual);
    }

    @Test
    public void getEmployeesAcrossAllDepartmentsTest(){
        Set<Integer> allDepartaments = actualEmployees.stream().map(Employee::getDepartament).collect(Collectors.toSet());
        Map<Integer, Collection<Employee>> actual = allDepartaments.stream().collect(Collectors.toMap(d -> d,
                d -> actualEmployees.stream().filter(e -> e.getDepartament() == d).collect(Collectors.toList())));
        Map<Integer, Collection<Employee>> expected = departamentServiceForTest.getEmployeesAcrossAllDepartments();
        assertEquals(expected,actual);
    }
}
