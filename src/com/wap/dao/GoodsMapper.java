package com.wap.dao;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.wap.entity.GoodsEntity;
import com.wap.entity.GoodsType;
import com.wap.entity.OrderOrderInfo;

public interface GoodsMapper {
	ArrayList<GoodsEntity> SelectGoodAll(Long id);

	/**
	 * 返回店商品类型及信息
	 * 
	 * @return
	 */
	ArrayList<GoodsType> selectGoodType(Long id);

	/**
	 * 添加订单
	 * 
	 * @param orderInfo
	 * @return
	 */
	int addOrderinfo(OrderOrderInfo orderInfo);

	/**
	 * 添加详情
	 * 
	 * @param order_id
	 * @param goods_id
	 * @param goods_num
	 * @param create_time
	 * @return
	 */
	int addOrderDetail(@Param("order_id") Long order_id,
			@Param("goods_id") Long goods_id,
			@Param("goods_num") Integer goods_num,
			@Param("create_time") Date create_time);

	/***
	 * 餐桌为使用状态
	 * 
	 * @return
	 */
	int updateDeskStatus(@Param("deskid") Long deskid);

}
