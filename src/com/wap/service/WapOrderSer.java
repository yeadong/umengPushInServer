package com.wap.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wap.dao.KxfOrderDetailMapper;
import com.wap.dao.KxfOrderInfoMapper;
import com.wap.entity.KxfOrderDetail;
import com.wap.entity.KxfOrderInfo;

@Service
public class WapOrderSer {
	@Resource
	private KxfOrderInfoMapper order;
	private static String URL = "http://192.168.1.156:7070/UmengPushServer/img/";
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// private OrderOrderInfoMapper orderInfoMapper;

	public String getOrderIngs(Long longid) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<KxfOrderInfo> array = order.slectMyOrdering(longid);
		if (array != null || array.size() > 0) {
			DecimalFormat df = new DecimalFormat(".##");
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
				String orderstatus = "";
				if (intstatus == 2) {
					orderstatus = "待接单";
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
			jsonObject.put("back_code", "200");
			// }
		} else {
			jsonObject.put("back_code", 501);
			jsonObject.put("error_msg", "获取失败，没有数据");
		}
		String s = jsonObject.toString();
		return s;
	}

	/**
	 * 订单详情+点餐list
	 * 
	 * @param longid
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public String getDetailByID(Long longid) throws Exception {
		KxfOrderInfo info = order.selectOrderByID(longid);
		List<KxfOrderDetail> kxfOrderDetails = null;
		JSONObject json;
		if (info == null) {
			json = new JSONObject();
			json.put("back_code", "501");
			json.put("error_msg", "获取失败，没有数据");
		} else {
			json = new JSONObject(info);
			Date date = info.getCreateTime();
			String sdate = formatter.format(date);
			json.put("date", sdate);
			kxfOrderDetails = detailMapper.selectOrderDetailAll(longid);
			JSONArray array = new JSONArray();
			if (!kxfOrderDetails.isEmpty()) {
				for (KxfOrderDetail kxfOrderDetail : kxfOrderDetails) {
					int s = kxfOrderDetail.getGoodstatus();
					JSONObject jsonObject = new JSONObject();
					String picurl = URL + kxfOrderDetail.getPictureAddr();
					double am = kxfOrderDetail.getGoodPrice();
					am = am / 100;
					jsonObject.put("id", kxfOrderDetail.getId());
					jsonObject.put("oname", kxfOrderDetail.getGoodName());
					jsonObject.put("note", kxfOrderDetail.getGoodDesc());
					jsonObject.put("price", am);
					jsonObject.put("num", kxfOrderDetail.getGoodsNum());
					jsonObject.put("opic", picurl);
					jsonObject.put("gstatus", s);
					jsonObject.put("style", "");
					jsonObject.put("printType", kxfOrderDetail.getPrintType());
					array.put(jsonObject);
				}
				json.put("isarray", true);
				json.put("backlist", array);
			} else {
				json.put("isarray", false);
			}
		}
		return json.toString();
		// System.out.println(json.toString());
	}

	public String getOrderByid(Long longid) {
		KxfOrderInfo info = order.selectOrderByID(longid);
		JSONObject eachobject = new JSONObject();
		if (info != null) {
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
			String orderstatus = "";
			// 1点餐中，2下单中， 3已下单， 4 已结账
			if (intstatus == 2) {
				orderstatus = "待接单";
			} else if (intstatus == 1) {
				orderstatus = "点餐中";
			} else if (intstatus == 3) {
				orderstatus = "已下单";
			} else if (intstatus == 4) {
				orderstatus = "已结账";
			}
			try {
				eachobject.put("money", am);
				eachobject.put("renshu", person);
				eachobject.put("id", id);
				eachobject.put("canzhuo", dname);
				eachobject.put("remark", remark);
				eachobject.put("cid", cid);
				eachobject.put("date", sdate);
				eachobject.put("status", orderstatus);
				eachobject.put("back_code", "200");
			} catch (JSONException e) {
				e.printStackTrace();
			}

		} else {
			try {
				eachobject.put("back_code", 501);
				eachobject.put("error_msg", "获取失败，没有数据");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		String s = eachobject.toString();
		return s;
	}

	/**
	 * 修改数量
	 */
	public void updateNum(int i, Long id) {
		int code = detailMapper.updateOrderNum(i, id);
		System.out.println(code);
	}

	public void updateNum(List<Map<String, Object>> numlis) {
		Map<String, Object> map = new HashMap<>();
		for (int j = 0; j < numlis.size(); j++) {
			map = numlis.get(j);
			int inum = (int) map.get("inum");
			Long llid = (Long) map.get("llid");
			System.out.println(llid);
			int code = detailMapper.updateOrderNum(inum, llid);
			// System.out.println("updateNum --->" + llid + "======" + code);
		}
	}

	/**
	 * s 修改状态
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public String updateDel(Long id, Long oid) {
		String backmsg = "";
		int status = detailMapper.updateOrderDel(id);
		Map<String, Object> map = new HashMap<>();
		map.put("poid", oid);
		map.put("back_count", -1);
		detailMapper.orderinfoCcgc(map);
		int code = (int) map.get("back_count");
		System.out.println("changeOrder--------***********" + code);
		if (status == 1) {
			backmsg = "{\"back_code\":200}";
		} else {
			backmsg = "{\"back_code\":502,\"error_msg\":\"提交出错\"}";
		}
		return backmsg;

	}

	public void updateDel(List<Long> dellis) {

		for (int i = 0; i < dellis.size(); i++) {
			Long llid = dellis.get(i);
			int code = detailMapper.updateOrderDel(llid);
			System.out.println("updateDel --->" + llid + "======" + code);
		}
		// System.out.println(code);
	}

	public void changeOrderAm(Integer am, Long id, Long operid) {
		int backcode = order.updateOrderAm(am, id, operid);
		System.out.println("changeOrderAm----》" + backcode);
	}
	public void changeDeskUser(long id){
		order.updateDeskUsers(id);
	}

	@Resource
	private KxfOrderDetailMapper detailMapper;

	// "id": "2147579258556", "oname": "鸡丝凉面", "note": "哈哈哈新鲜，吃的放心", "price":
	// 9.99, "nprice": 0, "num": 1,"opic":"","style":"la"

	public void getAllDetail(Long longid) {
		List<KxfOrderDetail> kxfOrderDetails = detailMapper
				.selectOrderDetailAll(longid);
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		if (!kxfOrderDetails.isEmpty()) {
			for (KxfOrderDetail kxfOrderDetail : kxfOrderDetails) {
				JSONObject jsonObject = new JSONObject();
				String picurl = URL + kxfOrderDetail.getPictureAddr();
				double am = kxfOrderDetail.getGoodPrice();
				am = am / 100;
				try {
					jsonObject.put("id", kxfOrderDetail.getId());
					jsonObject.put("oname", kxfOrderDetail.getGoodName());
					jsonObject.put("note", kxfOrderDetail.getGoodDesc());
					jsonObject.put("price", am);
					jsonObject.put("num", kxfOrderDetail.getGoodsNum());
					jsonObject.put("opic", picurl);
					jsonObject.put("style", "");
					array.put(jsonObject);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			try {
				json.put("code", 200);
				json.put("money", 36.26);
				json.put("backlist", array);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			try {
				json.put("code", "301");
				json.put("msg", "无更多数据");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		// System.out.println(json);
	}

	/**
	 * 修改单品状态
	 * 
	 * @param id
	 * @return
	 */
	public String changeGoodStatus(long id) {
		String backmsg = "";
		int status = detailMapper.changeStatus(id);
		if (status == 1) {
			backmsg = "{\"back_code\":200}";
		} else {
			backmsg = "{\"back_code\":502,\"error_msg\":\"提交出错\"}";
		}
		return backmsg;
	}

	/**
	 * 
	 */
	public void changeOrder(long oid) {
		System.out.println("changeOrder---->");
		Map<String, Object> map = new HashMap<>();
		map.put("poid", oid);
		map.put("back_count", -1);
		detailMapper.orderinfoCcgc(map);
		int code = (int) map.get("back_count");
		System.out.println("changeOrder--------***********" + code);
	}


	public String orderPayStatus(Long orderid, Long operid) {
		String backmsg = "";

		int backcode = order.updateOrderPay(operid, orderid);
		if (backcode > 0) {
			backmsg = "{\"back_code\":200}";
		} else {
			backmsg = "{\"back_code\":502,\"error_msg\":\"提交出错\"}";
		}
		return backmsg;
	}

}
