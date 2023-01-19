package com.example.homework1_springboot.model;

import java.util.Objects;

public class Employee {
    private String name;
    private String lastname;
    private Integer departament;
    private Integer salary;
    private static int counter;
    private final Integer id;

    public Employee(String name, String lastname, Integer departament, Integer salary) {
        this.name = name;
        this.lastname = lastname;
        this.departament = departament;
        this.salary = salary;
        this.id = counter++;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getDepartament() {
        return departament;
    }

    public Integer getSalary() {
        return salary;
    }


    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDepartament(Integer departament) {
        this.departament = departament;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(lastname, employee.lastname) && Objects.equals(departament, employee.departament) && Objects.equals(salary, employee.salary) && Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, departament, salary, id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", departament='" + departament + '\'' +
                ", salary=" + salary +
                ", id=" + id +
                '}';
    }
}
