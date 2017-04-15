package com.wap.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wap.dao.KxfOrderDetailMapper;

@Service
public class WuYuser {
	@Autowired
	private KxfOrderDetailMapper detailMapper;
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class }, propagation = Propagation.REQUIRED)
	public void addDetailMapper() {
		Long orderid = 129L;
		Long goodid = 245L;
		Integer goodnum = 3;
		Date date = new Date();
		Integer del = 3;
		detailMapper.addDetail(orderid, goodid, goodnum, date, del);
		// detailMapper.addDetail(orderid, null, null, null, null);
		String str = null;
		if (str.equals("")) {
		}
	}
	
	
	public void postImg(){
		
		
	}

}
