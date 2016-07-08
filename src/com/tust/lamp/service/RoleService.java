package com.tust.lamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tust.lamp.mapper.RoleMapper;

@Service
public class RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

}
