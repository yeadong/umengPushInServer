<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link href="../css/mui.min.css" rel="stylesheet" />
</head>

<body>
	<header class="mui-bar mui-bar-nav"> <a
		class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
	<a id="menu"
		class="mui-action-menu mui-icon mui-icon-bars mui-pull-right"
		href="#topPopover"></a>
	<h1 class="mui-title" style="color: #007AFF;">订单信息</h1>
	</header>

	<div class="mui-content">
		<div
			style="width: 100%; padding-top: 14px; padding-bottom: 14px; background: #FFFFFF;">
			<div style="display: inline; margin-left: 8px; margin-right: 8px;">
				<label style="color: #8F8F94;" id="foodordernumber"></label> <label
					style="float: right; padding-right: 8px; color: #8F8F94;"
					id="foodorderdesk"></label>
			</div>
			</br>
			<div
				style="display: inline; margin-left: 8px; margin-right: 8px; color: #8F8F94;">
				<label id="foodordertime"> </label> <label
					style="float: right; padding-right: 8px; font-size: 18px; color: #0062CC;"
					id="foodorderstatus"></label>
			</div>
			</br>
			<div
				style="display: inline; margin-left: 8px; margin-right: 8px; color: #8F8F94;">
				<label> 下单金额：</label><label
					style="color: #DD524D; font-size: 28px; margin-top: 12px;"
					id="foodorderamount"></label>
			</div>
		</div>
		</br>

		<ul class="mui-table-view" id="foodorderul">

		</ul>
		<div id="right_liner" style="display: none;">
			<!--右上角弹出菜单-->
			<div id="topPopover" class="mui-popover">
				<ul class="mui-table-view">
					<li class="mui-table-view-cell"><a onclick="refreshData()">刷
							新</a></li>
					<li class="mui-table-view-cell" style="display: none;"><a
						onclick="addFood()">加 菜</a></li>
					<li class="mui-table-view-cell"  style="display: none;"><a href="test">首 页</a></li>
				</ul>
			</div>
		</div>
	</div>
	<script src="../js/mui.min.js"></script>
	<script type="text/javascript">
		mui.init();
		window.onload = function() {
			/* var fooditemid = ${orderid};
				sessionStorage.setItem("fristin",1);			sessionStorage.setItem("fristin",1);
			if (fooditemid.length <= 0) */
				fooditemid = sessionStorage.getItem("fooditemid");
			setData(fooditemid);
			sessionStorage.setItem("firstIn","1");

		}
		//刷新
		function refreshData() {
			var info = sessionStorage.getItem("fooditemid");
			setData(info);
			var newpageul = document.getElementById("right_liner");
			newpageul.style.display = 'none';
			var drop = document.getElementsByClassName("mui-backdrop");
			drop[0].style.display = "none";
		}
		var menua = document.getElementById("menu");
		menua.addEventListener('tap', function() {
			var right = document.getElementById("right_liner");
			right.style.display = "block";

		});
		//加菜
		function addFood() {
			var info = sessionStorage.getItem("fooditemid");
			location.href = "test?orderId=" + info;
		}
		function setData(orderid) { //OrderQuery/QueryOrderDetail
			var url = "../OrderQuery/QueryOrderDetail";
			mui.ajax(url,{data : {
									OrderId : orderid
								},
								//dataType: 'json', //服务器返回json格式数据
								type : 'post', //HTTP请求类型
								timeout : 3000, //超时时间设置为10秒；
								success : function(data) {
									var jsondata = mui.parseJSON(data);
									console.log(jsondata);
									var orderuser = jsondata.customerId; //customerId
									var uphone = jsondata.uphone; //
									var canzhuo = jsondata.deskname;
									var odate = jsondata.date;
									var isarray = jsondata.backlist;
									var intstatus = jsondata.orderStatus;
									var orderstatus;
									var popover = document
											.getElementById("topPopover");
									if (intstatus == 2) {
										orderstatus = "待接单";
									} else if (intstatus == 1) {
										orderstatus = "点餐中";
									} else if (intstatus == 3) {
										orderstatus = "已下单";
										popover.children[0].children[1].style.display = "block";
									} else if (intstatus == 4) {
										orderstatus = "已上齐";
									} else if (intstatus == 5) {
										orderstatus = "已结账";
									} else if (intstatus == 6) {
										orderstatus = "已撤单";
									}
									var orderid = jsondata.id;
									var ordersum = jsondata.orderAmount;
									var deskname = jsondata.deskname;
									var ordeli = [];
									var spannum = document
											.getElementById("foodordernumber");
									var spandesk = document
											.getElementById("foodorderdesk");
									var spantime = document
											.getElementById("foodordertime");
									var spanstatus = document
											.getElementById("foodorderstatus");
									var spanamount = document
											.getElementById("foodorderamount");
									spannum.innerText = "订单号:" + orderid;
									spantime.innerText = odate;
									spanstatus.innerText = orderstatus;
									spanamount.innerText = ordersum / 100;
									spandesk.innerText = deskname;
									if (isarray.length > 0) {
										var ul = document
												.getElementById("foodorderul");
										var i = 0, cont = isarray.length;
										for (i; i < cont; i++) {
											var jsoncont = isarray[i];
											var name = jsoncont.oname;
											var num = jsoncont.num;
											var icode = jsoncont.gstatus;
											var status = "";
											if (icode == 1) {
												status = "未上菜";
											} else {
												status = "已上菜";
											}
											ordeli
													.push('<li class="mui-table-view-cell"><span>'
															+ name
															+ '</span> <span style="margin-left: 12px;">'
															+ num
															+ '</span> <span style="color:#CF2D28 ;float: right;">'
															+ status
															+ '</span></li>');
										}
										ul.innerHTML = ordeli.join('');
									}
								}
							});
			mui.plusReady(function() {
				plus.nativeUI.closeWaiting();
				mui.currentWebview.show();
			});

		}
	</script>

</body>

</html>