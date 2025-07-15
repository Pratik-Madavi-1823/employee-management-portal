package com.pm.EmployeeManagementPortal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.EmployeeManagementPortal.dao.MainDao;
import com.pm.EmployeeManagementPortal.entity.Country;
import com.pm.EmployeeManagementPortal.entity.Employee;
import com.pm.EmployeeManagementPortal.entity.Registration;

@Service
public class MainService {
	
	@Autowired
	MainDao dao;
	

	public String addCountry(Country c) {		
		
		String msg= dao.addCountry(c);
		
		if(Objects.isNull(msg)) {
			msg="Country is not addedd...";
		}
		
		return msg;
		
		
	}


	public String updateCountry(int id, Country c) {
		
		String msg= dao.updateCountry(id,c);
		if(Objects.isNull(msg)) {
			msg="Country is not Updatated..";
		}
		
		return msg;
		
	}
	
	
	public List<Country> getAllCountry() {

		List<Country> list = dao.getAllCountry();

		if (Objects.isNull(list)) {
			list = null;
		}
		return list;
	}


	public String addEmployee(Employee emp) {
		
		String msg=dao.addEmployee(emp);
		
		if(Objects.isNull(msg)) {
			msg="Record is not be addedd...";
		}
		return msg;		
		
	}


	public String updateEmployee(Employee emp) {
		
		String msg=dao.updateEmployee(emp);
		if(Objects.isNull(msg)) {
			msg="Record is not be Updatated";
		}
		
		return msg;
	}

	public String deleteEmployee(int id) {
		
		String msg=dao.deleteEmployee(id);
		if(Objects.isNull(msg)) {
			msg="Record is not Deleted..";
		}		
		return msg;
	}
	
	public List<Employee> getAllEmployee() {
		
		List<Employee> list=
				dao.getAllEmployee();
		
		if(Objects.isNull(list)) {
			list=null;
		}		
		return list;
	}


	public Employee getParticularById(int id) {
		
		Employee emp= dao.getParticularById(id);
		if(Objects.isNull(emp)) {
			emp=null;
		}
		
		return emp;
	}


	public List<Employee> getEmployeesByStatus(String status) {
	
			List<Employee> list= dao.getEmployeesByStatus(status);
			
			if(Objects.isNull(list)) {
				list=null;
			}
			
		return list;
	}
	
	
	public HashMap loginUser(Registration reg) {
		Registration r= dao.loginUser(reg);
		
		HashMap map=new HashMap();
		
		if(Objects.isNull(r)) {
			map.put("msg", "Invalid User");
			map.put("user", r);
		}else {
			r.setPassword("*****");		// Hiding the password in returned object.
			map.put("msg", "Valid User");
			map.put("user", r);
		}
		return map;		
			
	}
	
	
	public List<Employee> salaryGt(double sal){
		List<Employee> list=dao.salaryGt(sal);
		return list;
	}
	
	
	
}
