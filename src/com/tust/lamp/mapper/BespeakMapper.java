package com.tust.lamp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tust.lamp.entity.Bespeak;

public interface BespeakMapper {

	List<Bespeak> getByDeviceId(@Param("id")Integer id, @Param("dateStr")String dateStr);

	void save(Bespeak bespeak);

	Bespeak checking(@Param("bespeakDateStr")String bespeakDateStr, @Param("stage")Integer stage);

	long getTotalElements(Map<String, Object> myBatisParmas);

	List<Bespeak> getContent(Map<String, Object> myBatisParmas);

}
