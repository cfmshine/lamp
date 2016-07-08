package com.tust.lamp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tust.lamp.entity.Maintenance;
import com.tust.lamp.mapper.MaintenanceMapper;

@Service
public class MaintenanceService {
	
	@Autowired
	private MaintenanceMapper maintenanceMapper;

	@Transactional(readOnly=true)
	public List<Maintenance> getList() {
		return maintenanceMapper.getList();
	}

	@Transactional
	public void save(Maintenance maintenance) {
		maintenanceMapper.save(maintenance);
	}

	@Transactional(readOnly=true)
	public Maintenance getById(Integer id) {
		return maintenanceMapper.getById(id);
	}

	@Transactional
	public void update(Maintenance maintenance) {
		maintenanceMapper.update(maintenance);
	}

	@Transactional(readOnly=true)
	public List<Maintenance> getAll() {
		return maintenanceMapper.getAll();
	}

}
