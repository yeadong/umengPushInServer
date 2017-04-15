package com.wap.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wap.dao.OrderPrintMapper;
import com.wap.entity.OrderPrintEntity;

@Service
public class StorePrintServer {
	@Autowired
	private OrderPrintMapper printMapper;

	public String getAllPrint(Long sid) {
		String backmsg = "";
		ArrayList<OrderPrintEntity> orderprints = printMapper.StorePrint(sid);
		if (orderprints != null) {
			JSONObject backjson = null;
			JSONArray eacharray = new JSONArray();
			for (OrderPrintEntity orderPrintEntity : orderprints) {
				JSONObject eachjs = new JSONObject(orderPrintEntity);
				eacharray.put(eachjs);
			}
			backjson = new JSONObject();
			try {
				backjson.put("typeslist", eacharray);
				backjson.put("typescount", orderprints.size());
				backjson.put("back_code", 200);
				backmsg = backjson.toString();
			} catch (JSONException e) {
				backmsg = "{\"back_code\":407,\"error_msg\":\"转化异常\"}";
			}

		} else {
			backmsg = "{\"back_code\":501,\"error_msg\":\"没有更多数据\"}";
		}
		return backmsg;
	}
}
