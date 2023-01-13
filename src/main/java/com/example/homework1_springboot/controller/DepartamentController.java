package com.example.homework1_springboot.controller;

import com.example.homework1_springboot.model.Employee;
import com.example.homework1_springboot.service.DepartamentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("departament")
public class DepartamentController {
    private final DepartamentService departamentService;

    public DepartamentController(DepartamentService departamentService) {
        this.departamentService = departamentService;
    }

    @GetMapping("{id}/employees")
    public Collection<Employee> getAllEmployeesOfDepartament(@PathVariable("id") Integer departamentId){
        return departamentService.getAllEmployeesOfDepartament(departamentId);
    }

    @GetMapping("{id}/employees/salary/sum")
    public Integer getSalarySumOfDepartament(@PathVariable("id")Integer departamentId) {
        return departamentService.getSalarySumOfDepartament(departamentId);
    }

    @GetMapping("{id}/employees/salary/max")
    public List<Employee> getEmployeesWithMaxSalaryOfDepartament(@PathVariable("id")Integer departamentId) {
        return departamentService.getEmployeesWithMaxSalaryOfDepartament(departamentId);
    }

    @GetMapping("{id}/employees/salary/min")
    public List<Employee> getEmployeesWithMinSalaryOfDepartament(@PathVariable("id")Integer departamentId) {
        return departamentService.getEmployeesWithMinSalaryOfDepartament(departamentId);
    }

    @GetMapping("employees")
    public Map<Integer, Collection<Employee>> getEmployeesAcrossAllDepartments (){
        return departamentService.getEmployeesAcrossAllDepartments();
    }
}
