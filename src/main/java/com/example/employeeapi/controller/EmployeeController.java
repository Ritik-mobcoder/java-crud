package com.example.employeeapi.controller;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
@Api(tags = "Employee Management API", description = "APIs for managing employee data")
public class EmployeeController {
    
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @ApiOperation("Get all employees")
    @ApiResponse(code = 200, message = "Successfully retrieved all employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        logger.info("Getting all employees");
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    @ApiOperation("Get employee by ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Successfully retrieved employee"),
        @ApiResponse(code = 404, message = "Employee not found")
    })
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        logger.info("Getting employee with id: {}", id);
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation("Update employee details")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Successfully updated employee"),
        @ApiResponse(code = 404, message = "Employee not found")
    })
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employeeDetails) {
        logger.info("Received PUT request for employee id: {}", id);
        logger.info("Request body: {}", employeeDetails);
        
        try {
            Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
            logger.info("Employee updated successfully: {}", updatedEmployee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Error updating employee: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete employee")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Successfully deleted employee"),
        @ApiResponse(code = 404, message = "Employee not found")
    })
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Integer id) {
        logger.info("Deleting employee with id: {}", id);
        try {
            Integer deletedId = employeeService.deleteEmployee(id);
            logger.info("Employee deleted successfully with id: {}", deletedId);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "id=" + deletedId + " data deleted successfully");
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Error deleting employee: {}", e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Employee not found with id: " + id);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ApiOperation("Create new employee")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Successfully created employee"),
        @ApiResponse(code = 400, message = "Invalid input data")
    })
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        logger.info("Creating new employee: {}", employee);
        try {
            Employee createdEmployee = employeeService.createEmployee(employee);
            logger.info("Employee created successfully with id: {}", createdEmployee.getEmployeeId());
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "data inserted successfully");
            response.put("employee", createdEmployee);
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating employee: {}", e.getMessage());
            return new ResponseEntity<>("Error creating employee: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 