package com.wap.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wap.entity.CustomerOrderEntity;
import com.wap.entity.KxfOrderInfo;
import com.wap.entity.ServingAm;

public interface KxfOrderInfoMapper {
	/**
	 * 查询店铺所有的接单情况
	 * 
	 * @param id
	 * @return
	 */
	List<KxfOrderInfo> slectMyOrdering(Long id);

	/**
	 * 查询指定的订单
	 * 
	 * @param id
	 * @return
	 */
	KxfOrderInfo selectOrderByID(Long id);

	/**
	 * 更新订单am
	 * 
	 * @param am
	 * @param id
	 * @return
	 */
	int updateOrderAm(Integer am, Long id, Long operId);

	/**
	 * 使用中状态餐桌
	 * 
	 * @param id
	 * @return
	 */
	int updateDeskUsers(Long id);

	/**
	 * 
	 * @param id
	 * @param nowPage
	 * @param pageCon
	 * @return
	 */
	List<KxfOrderInfo> slectMyOrderCom(@Param("id") Long id,
			@Param("nowPage") Integer nowPage,
			@Param("pageCon") Integer pageCon,
			@Param("orderstatus") Integer orderstatus,
			@Param("beigin") String beigin, @Param("end") String end);

	/**
	 * all count
	 * 
	 * @param id
	 * @return
	 */

	int slectOrderComCount(@Param("id") Long id,
			@Param("orderstatus") Integer orderstatus,
			@Param("beigin") String beigin, @Param("end") String end);

	/**
	 * 查询指定时间营业额
	 * 
	 * @param storeid
	 * @param beigin
	 * @param end
	 * @return
	 */
	ServingAm selectOrderTimeAm(Long storeid, String beigin, String end);

	/**
	 * 更新orderinfo 上完菜
	 * 
	 * @param orderid
	 * @return
	 */
	int updateOrderCom(Long orderid);

	int updateDeskCom(Long deskid);

	/**
	 * s上完菜/退单 存储过程
	 * 
	 * @param orderid
	 * @param deskid
	 * @param status
	 * @return
	 */
	int changeOrdeDeatilCom(Long orderid, Long deskid, Integer status);

	Map<String, Object> testoam(Map<String, Object> map);

	/**
	 * 更改付款信息
	 * 
	 * @param operid
	 * @param orderid
	 * @return
	 */
	int updateOrderPay(Long operid, Long orderid);

	ArrayList<CustomerOrderEntity> getAllCustomerOrder(String usercode,
			Integer page);

	/**
	 * 付款信息
	 * 
	 * @param payway
	 *            1支付宝2微信3现金4其他
	 * @param orderid
	 * @param payid
	 * @return
	 */
	int changeOrderPay(@Param("payway") int payway,
			@Param("orderid") Long orderid, @Param("payid") Long payid);

	/**
	 * 更改桌子状态
	 * 
	 * @param deskId
	 * @return
	 */
	int updateDeskStatus(@Param("deskId") Long deskId);
}
