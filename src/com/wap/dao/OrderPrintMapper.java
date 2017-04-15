package com.wap.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.wap.entity.OrderPrintEntity;

public interface OrderPrintMapper {

	ArrayList<OrderPrintEntity> StorePrint(@Param("storeid") Long storeid);

}
