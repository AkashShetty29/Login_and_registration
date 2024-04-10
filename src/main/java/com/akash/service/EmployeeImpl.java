package com.akash.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.akash.DTO.EmployeeDTO;
import com.akash.DTO.LoginDTO;
import com.akash.Repo.EmployeeRepo;
import com.akash.Response.LoginMesage;
import com.akash.entity.Employee;

@Service
public class EmployeeImpl implements EmployeeService{
	
    @Autowired
    private EmployeeRepo employeeRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public String addEmployee(EmployeeDTO employeeDTO) {
		      Employee employee = new Employee(
				
		        employeeDTO.getEmployeeid(),
		        employeeDTO.getEmployeename(),
		        employeeDTO.getEmail(),
		       this.passwordEncoder.encode(employeeDTO.getPassword())
		);
		
		employeeRepo.save(employee);
		return employee.getEmployeename();
	}
	
	EmployeeDTO employeeDTO;

	@Override
	public LoginMesage loginEmployee(LoginDTO loginDTO) {
		
		String msg = "";
	      Employee employee1 = employeeRepo.findByEmail(loginDTO.getEmail());
	      
	      if (employee1 != null) {
	      	
	          String password = loginDTO.getPassword();
	          String encodedPassword = employee1.getPassword();
	          Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
	          
	          if (isPwdRight) {
	              Optional<Employee> employee = employeeRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
	              
	              if (employee.isPresent()) {               	
	                  return new LoginMesage("Login Success", true);                    
	              } 
	              else {
	                  return new LoginMesage("Login Failed", false);
	              }
	              
	          } 
	          else {
	              return new LoginMesage("password Not Match", false);
	          }
	      }
	      
	      else {
	          return new LoginMesage("Email not exits", false);
	      }
	  
		
	}

 
	
	

}
