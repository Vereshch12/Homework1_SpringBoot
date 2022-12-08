package com.example.homework1_springboot.model;

public class Employee {
    private String name;
    private String lastname;
    private String departament;
    private int salary;
    private static int counter;
    private final int id;

    public Employee(String name, String lastname, String departament, int salary) {
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

    public String getDepartament() {
        return departament;
    }

    public int getSalary() {
        return salary;
    }


    public int getId() {
        return id;
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
