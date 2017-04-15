package com.wap.dao;

import java.util.ArrayList;
import java.util.Map;

import com.wap.entity.OrderMessageEntity;

public interface OrderMessageMapper {

	/**
	 * 查询所有的消息列表
	 * 
	 * @return
	 */

	ArrayList<OrderMessageEntity> findMessageEntities(Long storeid);

	int insertMessage(OrderMessageEntity entity);

	/**
	 * 更新message操作员ID
	 * 
	 * @param oid
	 * @param mid
	 * @return
	 */
	int changeMessageOper(Long oid, Long mid);

//	/**
//	 * 获得token
//	 * 
//	 * @param operid
//	 * @return
//	 */
//	ArrayList<Map<String, Object>> deviceEntities(Long operid);

	/**
	 * 查询门店下在线的token
	 */
	ArrayList<Map<String, Object>> queryUmengToken(Long storeid);

}
