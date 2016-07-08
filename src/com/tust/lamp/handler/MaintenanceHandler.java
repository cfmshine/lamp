package com.tust.lamp.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tust.lamp.entity.Maintenance;
import com.tust.lamp.service.MaintenanceService;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceHandler {
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public String update(Maintenance maintenance) {
		maintenanceService.update(maintenance);
		return "redirect:/maintenance/list";
	}
	
	@RequestMapping("/toEditUI/{id}")
	public String toEditUI(@PathVariable("id") Integer id, Map<String, Object> map) {
		Maintenance maintenance = maintenanceService.getById(id);
		map.put("maintenance", maintenance);
		return "maintenance/input";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Maintenance maintenance) {
		maintenanceService.save(maintenance);
		return "redirect:/maintenance/list";
	}
	
	@RequestMapping("/toAddUI")
	public String toAddUI(Map<String, Object> map) {
		Maintenance maintenance = new Maintenance();
		map.put("maintenance", maintenance);
		return "maintenance/input";
	}
	
	@RequestMapping("/list")
	public String list(Map<String, Object> map) {
		List<Maintenance> maintenances = maintenanceService.getList();
		map.put("maintenances", maintenances);
		return "maintenance/list";
	}

}
