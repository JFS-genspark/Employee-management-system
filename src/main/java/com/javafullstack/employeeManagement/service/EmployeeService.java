package com.javafullstack.employeeManagement.service;

import com.javafullstack.employeeManagement.employee.Employee;
import com.javafullstack.employeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    public final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findEmployeeById(Long id) {
        Optional<Employee> matchingEmployee = employeeRepository.findById(id);
        if(matchingEmployee.isEmpty()) {
            throw new IllegalStateException("Not a valid id");
        }
        return matchingEmployee;
    }

    public void registerANewEmployee(Employee employee) {

        Optional<Employee> existingEmployee = employeeRepository.findEmployeeByEmail(employee.getEmployeeEmail());
        System.out.println(existingEmployee);
        if(existingEmployee.isPresent()) {
            throw new IllegalStateException("Email already exist");
        }

        employeeRepository.save(employee);
    }
}
