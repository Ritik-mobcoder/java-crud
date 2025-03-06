package com.example.employeeapi.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@Entity
@Table(name = "employee")
@ApiModel(description = "Employee entity")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    @ApiModelProperty(notes = "Unique identifier of the employee")
    private Integer employeeId;
    
    @Column(name = "first_name", length = 50, nullable = false)
    @ApiModelProperty(notes = "First name of the employee", required = true)
    private String firstName;
    
    @Column(name = "last_name", length = 50, nullable = false)
    @ApiModelProperty(notes = "Last name of the employee", required = true)
    private String lastName;
    
    @Column(name = "department", length = 50)
    @ApiModelProperty(notes = "Department of the employee")
    private String department;
    
    @Column(name = "salary", precision = 10, scale = 2)
    @ApiModelProperty(notes = "Salary of the employee")
    private BigDecimal salary;
    
    @Column(name = "hire_date")
    @ApiModelProperty(notes = "Hire date of the employee")
    private LocalDate hireDate;
} 