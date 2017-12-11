package com.yue.controller;

import com.yue.entity.Staff;
import com.yue.service.StaffService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {
	
	private static Logger log = Logger.getLogger(StaffController.class);

	@Autowired
	private StaffService staffService;
	
	@RequestMapping(value="/index")
	public String index(){
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value="/json")
	public List<String> json() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i<10; i++){
			list.add("xxxxxx" + i);
		}
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public Staff saveUser() {
		log.warn("ทรฮส--------------------" + StaffController.class.getResource("/"));
		Staff staff = new Staff();
		staff.setUsername("mengxy");
		staff.setPassword("mengxy");
		this.staffService.saveStaff(staff);
		return staff;
	}
	@ResponseBody
	@RequestMapping("/findbyid/{id}")
	public Staff findById(@PathVariable long id){
		return staffService.findStaffById(id);
	}
	
	@ResponseBody
	@RequestMapping("/findbyusername/{username}")
	public List<Staff> findByUsername(@PathVariable String username){
		return staffService.findStaffByUsername(username);
	}
}
