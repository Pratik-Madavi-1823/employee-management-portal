package com.pm.EmployeeManagementPortal.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pm.EmployeeManagementPortal.entity.Country;
import com.pm.EmployeeManagementPortal.entity.Employee;
import com.pm.EmployeeManagementPortal.entity.Registration;
import com.pm.EmployeeManagementPortal.service.FileUploadUtil;
import com.pm.EmployeeManagementPortal.service.MainService;
import com.pm.EmployeeManagementPortal.utility.FileUploadResponse;

@CrossOrigin
@RestController
@RequestMapping("api")
public class MainController {
	
	@Autowired
	MainService service;
	
	@Autowired
	FileUploadUtil fileuploadservice;
	
	@PostMapping("addcountry")
	public String addCountry(@RequestBody Country c) {
		
		String msg= service.addCountry(c);
		return msg;
		
	}
	
	@PutMapping("updatecountry/{id}")
	public String updateCountry(@PathVariable int id,@RequestBody Country c ) {
		
		String msg= service.updateCountry(id,c);
		return msg;
		
	}
	
	@GetMapping("/getAllCountry")
	public List<Country> getAllCountry(){
		
		List<Country> list=service.getAllCountry();
		return list;
		
	}
	
	@PostMapping("/addEmp")
	public String addEmployee(@RequestBody Employee emp) {
		
		String msg= service.addEmployee(emp);
		return msg;
		
	}
	
	@PutMapping("/updateEmp")
	public String updateEmployee(@RequestBody Employee emp) {
		
		String msg=service.updateEmployee(emp);
		
		return msg;
		
	}
	
	@DeleteMapping("/deleteEmp/{id}")
	public String deleteEmployee(@PathVariable int id) {
		
		String msg=service.deleteEmployee(id);
		return msg;
		
	}	
	
	@GetMapping("getAllEmp")
	public List<Employee> getAllEmployee(){
		
		List<Employee> list=service.getAllEmployee();
		return list;
		
	}
	
	@GetMapping("getbyId/{id}")
	public Employee getParticularById(@PathVariable int id) {
		
		Employee emp=service.getParticularById(id);
		
		return emp;
		
	}
	
	@GetMapping("getempbystatus/{status}")
	public List<Employee> getEmployeesByStatus(@PathVariable String status){
		
		List<Employee> list= service.getEmployeesByStatus(status);
		
		return list;
		
	}
	
	
	@PostMapping("/login")
	public HashMap loginUser(@RequestBody Registration reg) {
		
		HashMap map= service.loginUser(reg);
		return map;
		
	}
	
	@GetMapping("salgt/{sal}")
	public List<Employee> salaryGt(@PathVariable double sal){
		
		List<Employee> list=service.salaryGt(sal);
		return list;
	}
	
	// Code for uploading image file ************************************************
	@PostMapping("/uploadImageFile")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file") MultipartFile multipartFile)
                    throws IOException {
	
         System.out.println("in Upload file API....");
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
         
        String filecode = fileuploadservice.saveFile(fileName, multipartFile);
         
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri("/downloadFile/" + filecode);
        response.setMsg("File Upload Successfully..");
         
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	// ********************************************************************************
	
	
}
