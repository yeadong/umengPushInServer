package com.wap.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wap.dao.KxfOrderInfoMapper;
import com.wap.entity.CustomerOrderEntity;
import com.wap.entity.KxfOrderInfo;
import com.wap.entity.ServingAm;

@Service
public class OrderCompileSer {
	@Resource
	private KxfOrderInfoMapper order;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// org.springframework.orm.jpa.JpaTransactionManage
	public String getAllOrderCompile(Long longid, int nowpage, int pagecon,
			int status,String begin,String end) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		if (nowpage < 1)
			nowpage = 1;
		int begincount = (nowpage - 1) * pagecon;
		int allconut = order.slectOrderComCount(longid, status,begin,end);
		List<KxfOrderInfo> array = order.slectMyOrderCom(longid, begincount,
				pagecon, status,begin,end);
		if (array != null || array.size() > 0) {
			JSONArray eachjson = new JSONArray();
			for (KxfOrderInfo info : array) {
				JSONObject eachobject = new JSONObject();
				double am = info.getOrderAmount();
				am = am / 100;
				int person = info.getPersonNum();
				long id = info.getId();
				String dname = info.getDeskname();
				String remark = info.getRemark();
				Long cid = info.getCustomerId();
				Date date = info.getCreateTime();
				String sdate = formatter.format(date);
				int intstatus = info.getOrderStatus();
				String orderstatus = "已完成";
				switch (intstatus) {
				case 2:
					orderstatus = "待接单";
					break;
				case 3:
					orderstatus = "已下单";
					break;
				case 4:
					orderstatus = "已上齐";
					break;
				case 5:
					orderstatus = "已结账";
					break;
				case 6:
					orderstatus = "已撤单";
					break;
				}
				eachobject.put("money", am);
				eachobject.put("renshu", person);
				eachobject.put("id", id);
				eachobject.put("canzhuo", dname);
				eachobject.put("remark", remark);
				eachobject.put("cid", cid);
				eachobject.put("date", sdate);
				eachobject.put("status", orderstatus);
				eachjson.put(eachobject);
			}
			jsonObject.put("jiedanlist", eachjson);
			jsonObject.put("allcount", allconut);
			jsonObject.put("nowpage", nowpage);
			int pagecount = allconut % pagecon == 0 ? allconut / pagecon
					: allconut / pagecon + 1;
			jsonObject.put("pagecount", pagecount);
			jsonObject.put("back_code", "200");
		} else {
			jsonObject.put("back_code", 501);// fen
			jsonObject.put("error_msg", "获取失败，没有数据");
		}
		String s = jsonObject.toString();
		return s;
	}

	/**
	 * 
	 */
	public String showAM(long id, String begin, String end) {
		ServingAm servingAm = order.selectOrderTimeAm(id, begin, end);
		JSONObject json = new JSONObject();
		try {
			if (servingAm == null) {
				json.put("back_code", "501");
				json.put("error_msg", "获取失败，没有数据");
			}
			json.put("back_code", "200");
			json.put("sumam", servingAm.getSumAm() / 100);
			json.put("allcount", servingAm.getAllcount());
			json.put("allperson", servingAm.getAllperson());
			json.put("recount", 123);
		} catch (Exception e) {

		}
		return json.toString();
	}

	public String testAm(long id, String begin, String end) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("btime", begin);
		map.put("etime", end);
		map.put("sid", id);
		order.testoam(map);
		Object oam = map.get("oam");
		if (null == oam) {
			map.put("oam", 0);
		}
		Object operson = map.get("operson");
		if (null == operson) {
			map.put("operson", 0);
		}
		JSONObject json = new JSONObject(map);
		return json.toString();
	}

	public String changeOrderinfo(long orderid, long deskid, int status) {
		int code = order.changeOrdeDeatilCom(orderid, deskid, status);
		String backmsg = "{\"back_code\":" + code + "}";
		return backmsg;
	}

	@Transactional
	public String changeOrderPay(Long orderid, int payway, Long payid, Long did) {
		int ocode = order.changeOrderPay(payway, orderid, payid);
		int dcode = order.updateDeskStatus(did);
		String backmsg;
		if (ocode == 1 && dcode == 1) {
			backmsg = "{\"back_code\":200}";
		} else {
			backmsg = "{\"back_code\":302,\"error_msg\":\"修改出错\"}";
		}

		return backmsg;
	}

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"MM-dd HH:mm");

	public JSONObject getCustomerOrder(String usercode, int page) {

		ArrayList<CustomerOrderEntity> allentity = order.getAllCustomerOrder(
				usercode, page);
		JSONObject jsonObject = new JSONObject();
		try {
			if (allentity == null || allentity.size() == 0) {
				jsonObject.put("back_code", "501");
				jsonObject.put("error_msg", "获取失败，没有数据");
			} else {
				JSONArray array = new JSONArray();
				for (int i = 0; i < allentity.size(); i++) {
					CustomerOrderEntity cusEntity = allentity.get(i);
					Date date = cusEntity.getNowDate();
					String sdf = simpleDateFormat.format(date);
					JSONObject eachJson = new JSONObject(cusEntity);
					eachJson.put("nowDate", sdf);
					array.put(eachJson);
				}
				jsonObject.put("back_code", "200");
				jsonObject.put("back_list", array);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
