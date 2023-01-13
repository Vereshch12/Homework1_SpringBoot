package com.example.homework1_springboot.model;

public class Employee {
    private String name;
    private String lastname;
    private Integer departament;
    private int salary;
    private static int counter;
    private final Integer id;

    public Employee(String name, String lastname, Integer departament, int salary) {
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

    public int getSalary() {
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
