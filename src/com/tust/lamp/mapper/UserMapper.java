package com.tust.lamp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tust.lamp.entity.User;

public interface UserMapper {

	User login(@Param("name")String name, @Param("password")String password);

	long getTotalElements(Map<String, Object> myBatisParmas);

	List<User> getContent(Map<String, Object> myBatisParmas);

	void delete(Integer id);

	void give(Integer id);

	User getByName(String username);

}
