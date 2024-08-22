package com.example.EmployeeApp;

import com.example.EmployeeApp.model.Employee;
import com.example.EmployeeApp.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// this class represents this app's controller
// this class will be responsible for handling the API calls
// this class will interact with the service to make changes to the DB
// controllers ideally should not contain much logic
@RestController
@RequestMapping("/employee")
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // maps GET requests to this method
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
        // ResponseEntity is a class that represents the entire HTTP response incl body, headers, etc.
        // HttpStatus.OK returns a 200 status
    }

    // this method is called when a GET request is sent to a URL with /find/{id}
    // the @PathVariable annotation binds the URL variable with the id parameter
    @GetMapping ("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    //@RequestBody takes the request body and converts into an object
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    //PUT requests are used for updating as opposed to POST requests
    // difference is that calling the same PUT request multiple times will produce the same result
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteEmployee (@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
