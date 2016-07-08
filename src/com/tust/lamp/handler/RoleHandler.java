package com.tust.lamp.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tust.lamp.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleHandler {
	
	@Autowired
	private RoleService roleService;

}
