package com.tust.lamp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tust.lamp.entity.Bespeak;
import com.tust.lamp.mapper.BespeakMapper;
import com.tust.lamp.orm.Page;
import com.tust.lamp.orm.PropertyFilter;
import com.tust.lamp.utils.DataUtils;

@Service
public class BespeakService {
	
	@Autowired
	private BespeakMapper bespeakMapper;

	public List<Bespeak> getByDeviceId(Integer id) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateStr = format.format(date);
		return bespeakMapper.getByDeviceId(id, dateStr);
	}

	public void save(Bespeak bespeak) {
		bespeakMapper.save(bespeak);
	}

	public Bespeak checking(String bespeakDateStr, Integer stage) {
		return bespeakMapper.checking(bespeakDateStr, stage);
	}

	public Page<Bespeak> getPage(Integer pageNo, Map<String, Object> params) {
		List<PropertyFilter> filters = DataUtils.parseHandlerParamsToPropertyFilters(params);
		Map<String, Object> myBatisParmas = DataUtils.parsePropertyFiltersToMyBatisParmas(filters);
		
		long totalElements = bespeakMapper.getTotalElements(myBatisParmas);
		
		Page<Bespeak> page = new Page<>();
		page.setPageNo(pageNo);
		page.setTotalElements((int)totalElements);
		
		int fromIndex = (page.getPageNo()-1) * page.getPageSize();
		if(fromIndex < 0) {
			fromIndex = 0;
		}
		
		myBatisParmas.put("fromIndex", fromIndex);
		myBatisParmas.put("pageSize", page.getPageSize());
		
		List<Bespeak> content = bespeakMapper.getContent(myBatisParmas);
		page.setContent(content);
		
		return page;
	}

	

}
