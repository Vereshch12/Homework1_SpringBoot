package com.example.homework1_springboot;

import com.example.homework1_springboot.exceptions.EmployeeException;
import com.example.homework1_springboot.model.Employee;
import com.example.homework1_springboot.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    private EmployeeService employeeServiceForTest = new EmployeeService();

    private Employee employee1 = new Employee ("testName1", "testSurname1", 1, 1000);
    private Employee employee2 = new Employee("testName2", "testSurname2", 2, 2000);
    private Employee employee3 = new Employee("testName3", "testSurname3", 3, 3000);
    private Collection<Employee> actualEmployees;


    @BeforeEach
    public void setUp() throws EmployeeException {
        actualEmployees = new ArrayList<>(List.of(employee1,employee2,employee3));
        employeeServiceForTest.addEmployee(employee1);
        employeeServiceForTest.addEmployee(employee2);
        employeeServiceForTest.addEmployee(employee3);
    }

    @Test
    public void getAllEmployeesTest() {
        Collection<Employee> expectedEmployees = employeeServiceForTest.getAllEmployees();
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test //Когда нет ошибок
    public void addEmployeeTest1 () throws EmployeeException {
        Employee actualEmployee = employee1;
        Employee expectedEmployee = employeeServiceForTest.addEmployee(employee1);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test //Ошибка нет имени
    public void addEmployeeTest2 (){
        EmployeeService employeeServiceTest = new EmployeeService();
        Employee actualEmployee = new Employee("", "123", 4, 1000);
        EmployeeException thrown = assertThrows(EmployeeException.class, () -> employeeServiceTest.addEmployee(actualEmployee), "Expected doThing() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contentEquals("У сотрудника нет имени!"));
    }

    @Test //Ошибка нет фамилии
    public void addEmployeeTest3 (){
        EmployeeService employeeServiceTest = new EmployeeService();
        Employee actualEmployee = new Employee("123", "   ", 4, 1000);
        EmployeeException thrown = assertThrows(EmployeeException.class, () -> employeeServiceTest.addEmployee(actualEmployee), "Expected doThing() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contentEquals("У сотрудника нет фамилии!"));
    }

    @Test //Ошибка нет отдела
    public void addEmployeeTest4 (){
        EmployeeService employeeServiceTest = new EmployeeService();
        Employee actualEmployee = new Employee("123", "212", null, 1000);
        EmployeeException thrown = assertThrows(EmployeeException.class, () -> employeeServiceTest.addEmployee(actualEmployee), "Expected doThing() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contentEquals("У сотрудника не указан отдел!"));
    }

    @Test //Ошибка неверно указана зарплата или ее нет
    public void addEmployeeTest5 (){
        EmployeeService employeeServiceTest = new EmployeeService();
        Employee actualEmployee = new Employee("123", "212", 2, -89);
        EmployeeException thrown = assertThrows(EmployeeException.class, () -> employeeServiceTest.addEmployee(actualEmployee), "Expected doThing() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contentEquals("У сотрудника неправильно указана зарплата!"));
    }

    @Test
    public void getSalarySumTest (){
        Integer actual = employee1.getSalary() + employee2.getSalary() + employee3.getSalary();
        Integer expected = employeeServiceForTest.getSalarySum();
        assertEquals(actual, expected);
    }

    @Test
    public void getEmployeesWithMinSalaryTest() throws EmployeeException {
        Employee employeeForTest = new Employee("kdcdl", "cmkd", 4, 1000);
        List<Employee> expected = new ArrayList<>();
        expected.add(employee1);
        expected.add(employeeForTest);
        employeeServiceForTest.addEmployee(employeeForTest);
        List<Employee> actual = employeeServiceForTest.getEmployeesWithMinSalary();
        assertEquals(actual, expected);
    }

    @Test
    public void getEmployeesWithMaxSalaryTest() throws EmployeeException {
        Employee employeeForTest = new Employee("kdcdl", "cmkd", 4, 3000);
        List<Employee> expected = new ArrayList<>();
        expected.add(employee3);
        expected.add(employeeForTest);
        employeeServiceForTest.addEmployee(employeeForTest);
        List<Employee> actual = employeeServiceForTest.getEmployeesWithMaxSalary();
        assertEquals(actual, expected);
    }

    @Test
    public void getEmployeesWhoEarnMoreThanAverageSalary() throws EmployeeException {
        List<Employee> expected = new ArrayList<>();
        expected.add(employee3);
        List<Employee> actual = employeeServiceForTest.getEmployeesWhoEarnMoreThanAverageSalary();
        assertEquals(actual, expected);
    }





}
