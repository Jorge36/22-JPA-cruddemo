package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	// quick and dirty: inject employee dao (use constructor injection)
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	

	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		
		return employeeService.findAll();
		
	}
	
	// add mapping for GET /employees/{employeeId}
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		
		
		Employee employee = employeeService.findById(employeeId);
		
		if (employee == null) {
			
			throw new RuntimeException("Employee id not found - " + employeeId);
			
		}
		
		return employee;
		
	}
	
	// add mapping for POST /employees/ - save new employee
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		
		// also just in case we pass an id in json, set id to 0
		employee.setId(0);
		
		employeeService.save(employee);
		
		return employee;
		
	}

	// add mapping for PUT /employees/ - update employee

	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
				
		employeeService.save(employee);
		
		return employee;
		
	}
	
	// add mapping for DELETE /employees/{employeeId} - delete employee

	
	@DeleteMapping("/employees/{employeeId}")
	public String updateEmployee(@PathVariable int employeeId) {
		
		Employee employee = employeeService.findById(employeeId);
		
		if (employee == null) {
			
			throw new RuntimeException("Employee id not found - " + employeeId);
			
		}
		
		employeeService.deleteById(employeeId);
		
		return "deleted employee id - " + employeeId;
		
	}
	
	
	
	
}
