package com.wap.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wap.entity.GoodsType;
import com.wap.entity.KxfOrderDetail;

public interface KxfOrderDetailMapper {
	/**
	 * 指定订单点餐list
	 * 
	 * @param id
	 *            订单ID
	 * @return
	 */
	List<KxfOrderDetail> selectOrderDetailAll(Long id);

	/**
	 * 更改点餐数量
	 * 
	 * @return
	 */
	int updateOrderNum(Integer integer, Long id);

	/**
	 * 是否删除此菜品
	 * 
	 * @return
	 */
	int updateOrderDel(Long id);

	/**
	 * 添加菜品
	 * 
	 * @return
	 */
	int addDetail(Long orderid, Long goodid, Integer goodnum, Date date,
			Integer del);

	/**
	 * 上菜单品状态
	 * 
	 * @param id
	 * @return
	 */
	int changeStatus(Long id);

	/**
	 * 用于删除菜品后取得sum
	 * 
	 * @param orderid
	 * @return
	 */
	Integer selectDetailAllAm(@Param("orderid") Long orderid);

	/**
	 * 删除菜品后更新order
	 * 
	 * @return
	 */
	int changeOrderAm();

	// 存储过程
	/**
	 * 更新orderinfo的金额
	 * 
	 * @param map
	 * @return
	 */
	Integer orderinfoCcgc(Map<String, Object> map);

	// 获得指定门店的菜品目录
	List<GoodsType> getCatMap(Long storeid);

}
