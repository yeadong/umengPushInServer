package com.wap.service;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wap.dao.KxfOrderDetailMapper;
import com.wap.dao.KxfOrderInfoMapper;

@Service
public class OrderTranSer {
	@Autowired
	private KxfOrderInfoMapper order;

	public void chageTo() {
		for (int i = 3; i < 10; i++) {
			Long id = Long.valueOf(i + "");
			if (i == 3) {
				// new Exception("----------------TEST");propagation =
				// Propagation.REQUIRED,
				id = null;
			}
			order.updateOrderAm(6666, id,0l);
		}

	}

	@Autowired
	private KxfOrderDetailMapper detailMapper;

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void addDetailMapper() {
		Long orderid = 121L;
		Long goodid = 245L;
		Integer goodnum = 3;
		Date date = new Date();
		Integer del = 3;
		detailMapper.addDetail(orderid, goodid, goodnum, date, del);
		String str = null;
		if (str.equals("")) {
		}
	}

	public boolean uploadImg(MultipartFile file, JSONObject params) {

		return true;
	}

}
