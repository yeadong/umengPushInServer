package com.wap.dao;

import java.util.Map;

public interface KxfCustomerMapper {
	/**
	 * 添加用户 返回id
	 * 
	 * @param maps
	 * @return
	 */
	Map<String, Object> addCustomerGetId(Map<String, Object> maps);

	/**
	 * 餐桌是否使用中返回orderid
	 * @param maps
	 * @return
	 */
	Map<String, Object> excitOrderId(Map<String, Object> maps);
}
