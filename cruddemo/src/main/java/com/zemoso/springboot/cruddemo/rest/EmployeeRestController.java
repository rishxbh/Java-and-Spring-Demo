package com.zemoso.springboot.cruddemo.rest;

import java.util.List;

import com.zemoso.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zemoso.springboot.cruddemo.dao.EmployeeDAO;
import com.zemoso.springboot.cruddemo.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId){
		Employee employee=employeeService.findById(employeeId);
		if( employee==null){
			throw new RuntimeException("Employee Id not found- "+employeeId);
		}
		return employee;
	}

	@GetMapping("/yo")
	public Employee get3rd(){
		Employee employee=employeeService.findById(3);
		return employee;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee){
		employee.setId(0);
		employeeService.save(employee);
		return employee;
	}
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee){
		employeeService.save(employee);
		return employee;
	}
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId){
		Employee employee=employeeService.findById(employeeId);
		if(employee==null){
			throw new RuntimeException("Employee ID not found "+ employeeId);
		}
		employeeService.deleteById(employeeId);
		return "Deleted";
	}
	
}










