package com.akash.service;

import com.akash.DTO.EmployeeDTO;
import com.akash.DTO.LoginDTO;
import com.akash.Response.LoginMesage;

public interface EmployeeService {
	
    String addEmployee(EmployeeDTO employeeDTO);
    
    LoginMesage loginEmployee(LoginDTO loginDTO);
    


}
