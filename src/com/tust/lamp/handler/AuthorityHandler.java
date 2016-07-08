package com.tust.lamp.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tust.lamp.service.AuthorityService;

@Controller
@RequestMapping("/authority")
public class AuthorityHandler {
	
	@Autowired
	private AuthorityService authorityService;

}
