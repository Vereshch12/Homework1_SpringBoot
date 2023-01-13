package com.example.homework1_springboot;

import com.example.homework1_springboot.exceptions.EmployeeException;
import com.example.homework1_springboot.model.Employee;
import com.example.homework1_springboot.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeService employeeServiceForTest;

    @Mock
    private Employee employee1 = new Employee ("testName1", "testSurname1", 1, 1000);
    private Employee employee2 = new Employee("testName2", "testSurname2", 2, 2000);
    private Employee employee3 = new Employee("testName3", "testSurname3", 3, 3000);
    private Collection<Employee> actualEmployees = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        employee1 = new Employee("testName1", "testSurname1", 1, 1000);
        actualEmployees.add(employee1);
        actualEmployees.add(employee2);
        actualEmployees.add(employee3);
        when(employeeServiceForTest.getAllEmployees()).thenReturn(actualEmployees);
    }

    @Test
    public void getAllEmployeesTest() {
        Collection<Employee> expectedEmployees = employeeServiceForTest.getAllEmployees();
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test //Когда нет ошибок
    public void addEmployeeTest1 () throws EmployeeException {
        EmployeeService employeeServiceTest = new EmployeeService();
        Employee actualEmployee = employee1;
        Employee employee = new Employee(actualEmployee.getName(), actualEmployee.getLastname(), actualEmployee.getDepartament(), actualEmployee.getSalary());
        Employee expectedEmployee = employeeServiceTest.addEmployee(employee);
        when(employee1.getId()).thenReturn(3);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test //Ошибка нет имени
    public void addEmployeeTest2 () throws EmployeeException {
        EmployeeService employeeServiceTest = new EmployeeService();
        Employee actualEmployee = new Employee("", "123", 4, 1000);
        assertThrows(EmployeeException.class,() -> employeeServiceTest.addEmployee(actualEmployee));
    }
    @Test
    public void getEmployeesWithMinSalaryTest () {
        int minSalary = actualEmployees.stream()
                .min(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        List<Employee> expected =  actualEmployees.stream()
                .filter(e -> e.getSalary() == minSalary)
                .collect(Collectors.toList());
        List<Employee> actual = employeeServiceForTest.getEmployeesWithMinSalary();
        assertEquals(expected, actual);
    }


}
