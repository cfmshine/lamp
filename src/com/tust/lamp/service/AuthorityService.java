package com.tust.lamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tust.lamp.mapper.AuthorityMapper;

@Service
public class AuthorityService {
	
	@Autowired
	private AuthorityMapper authorityMapper;

}
