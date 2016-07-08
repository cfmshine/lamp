package com.tust.lamp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tust.lamp.entity.Device;
import com.tust.lamp.mapper.DeviceMapper;
import com.tust.lamp.orm.Page;
import com.tust.lamp.orm.PropertyFilter;
import com.tust.lamp.utils.DataUtils;

@Service
public class DeviceService {
	
	@Autowired
	private DeviceMapper deviceMapper;

	@Transactional(readOnly=true)
	public Page<Device> getPage(Integer pageNo, Map<String, Object> params) {
		List<PropertyFilter> filters = DataUtils.parseHandlerParamsToPropertyFilters(params);
		Map<String, Object> myBatisParmas = DataUtils.parsePropertyFiltersToMyBatisParmas(filters);
		
		long totalElements = deviceMapper.getTotalElements(myBatisParmas);
		
		Page<Device> page = new Page<>();
		page.setPageNo(pageNo);
		page.setTotalElements((int)totalElements);
		
		int fromIndex = (page.getPageNo()-1) * page.getPageSize();
		if(fromIndex < 0) {
			fromIndex = 0;
		}
		
		myBatisParmas.put("fromIndex", fromIndex);
		myBatisParmas.put("pageSize", page.getPageSize());
		
		List<Device> content = deviceMapper.getContent(myBatisParmas);
		page.setContent(content);
		
		return page;
	}

	@Transactional
	public void give(Integer id) {
		deviceMapper.give(id);
	}

	@Transactional
	public void delete(Integer id) {
		deviceMapper.delete(id);
	}

	@Transactional
	public void save(Device device) {
		deviceMapper.save(device);
	}

	@Transactional(readOnly=true)
	public Device getById(Integer id) {
		return deviceMapper.getById(id);
	}

	@Transactional
	public void update(Device device) {
		deviceMapper.update(device);
	}

}
