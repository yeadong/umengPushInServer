package com.wap.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wap.dao.DeskMapper;
import com.wap.entity.DeskEntity;

@Service
public class DeskServer {
	@Autowired
	private DeskMapper deskMapper;

	public JSONObject getDeskByStaus(Long store_id, int ostatus) {
		JSONObject backJson = new JSONObject();
		ArrayList<DeskEntity> deskEntities = deskMapper.selectDeskStatus(
				store_id, ostatus);
		if (deskEntities == null) {
			try {
				backJson.put("back_code", 501);
				backJson.put("error_msg", "获取失败，没有数据");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			JSONArray array = new JSONArray();
			for (DeskEntity deskEntity : deskEntities) {
				JSONObject each = new JSONObject(deskEntity);
				array.put(each);
			}
			try {
				backJson.put("typeslist", array);
				backJson.put("typescount", deskEntities.size());
				backJson.put("back_code", 200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return backJson;
	}

	public String getOrderId(Long deskid) {
		Long oid = deskMapper.getOrderId(deskid);

		if (null == oid) {
			return "{\"order_id\":0}";

		} else {
			return "{\"order_id\":" + oid + "}";

		}

	}
}
