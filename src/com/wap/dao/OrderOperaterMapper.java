package com.wap.dao;

import java.util.Map;

import com.wap.entity.OrderOperaterEntity;

//操作员
public interface OrderOperaterMapper {

	OrderOperaterEntity loginOperater(String moblie, String pwd);

	Map<String, Object>  updateOperater(Map<String, Object> maps);

//	Map<String, Object> getCustomerId(Map<String, Object> maps);
	

}
