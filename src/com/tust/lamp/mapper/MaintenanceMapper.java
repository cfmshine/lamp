package com.tust.lamp.mapper;

import java.util.List;

import com.tust.lamp.entity.Maintenance;

public interface MaintenanceMapper {

	List<Maintenance> getList();

	void save(Maintenance maintenance);

	Maintenance getById(Integer id);

	void update(Maintenance maintenance);

	List<Maintenance> getAll();

}
