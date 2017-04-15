package com.wap.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.wap.entity.DeskEntity;

public interface DeskMapper {
	/**
	 * 餐桌状态
	 * @param store_id
	 * @param ostatus
	 * @return
	 */
	ArrayList<DeskEntity> selectDeskStatus(@Param("store_id") Long store_id,
			@Param("ostatus") Integer ostatus);

	/**
	 * 已点餐orderid
	 * @param deskid
	 * @return
	 */
	Long getOrderId(Long deskid);
}
