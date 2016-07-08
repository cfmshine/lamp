package com.tust.lamp.mapper;

import java.util.List;
import java.util.Map;

import com.tust.lamp.entity.Device;

public interface DeviceMapper {

	long getTotalElements(Map<String, Object> myBatisParmas);

	List<Device> getContent(Map<String, Object> myBatisParmas);

	void give(Integer id);

	void delete(Integer id);

	void save(Device device);

	Device getById(Integer id);

	void update(Device device);

}
