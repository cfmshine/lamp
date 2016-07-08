package com.tust.lamp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tust.lamp.entity.User;
import com.tust.lamp.mapper.UserMapper;
import com.tust.lamp.orm.Page;
import com.tust.lamp.orm.PropertyFilter;
import com.tust.lamp.utils.DataUtils;

@Service
public class UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Transactional(readOnly=true)
	public User login(String name, String password) {
		return userMapper.login(name, password);
	}

	@Transactional(readOnly=true)
	public Page<User> getPage(Integer pageNo, Map<String, Object> params) {
		List<PropertyFilter> filters = DataUtils.parseHandlerParamsToPropertyFilters(params);
		Map<String, Object> myBatisParmas = DataUtils.parsePropertyFiltersToMyBatisParmas(filters);
		
		long totalElements = userMapper.getTotalElements(myBatisParmas);
		
		Page<User> page = new Page<>();
		page.setPageNo(pageNo);
		page.setTotalElements((int)totalElements);
		
		int fromIndex = (page.getPageNo()-1) * page.getPageSize();
		if(fromIndex < 0) {
			fromIndex = 0;
		}
		
		myBatisParmas.put("fromIndex", fromIndex);
		myBatisParmas.put("pageSize", page.getPageSize());
		
		List<User> content = userMapper.getContent(myBatisParmas);
		page.setContent(content);
		
		return page;
	}

	@Transactional
	public void delete(Integer id) {
		userMapper.delete(id);
	}

	@Transactional
	public void give(Integer id) {
		userMapper.give(id);
	}

	@Transactional(readOnly=true)
	public User getByUserName(String username) {
		return userMapper.getByName(username);
	}

}
