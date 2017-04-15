<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="../css/mui.min.css" rel="stylesheet" />
		<script src="../js/mui.min.js"></script>

	</head>

	<body>
		<script>
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
			//B页面onload从服务器获取列表数据；
			window.onload = function() {
				var storeid = sessionStorage.getItem("storeid");
				var merchantid = sessionStorage.getItem("merchantid");
				var info = sessionStorage.getItem("info");
				var msgtype = sessionStorage.getItem("msgtype");
				var url = "../orderMessage/pushMessage";
				mui.ajax(url, {
					data : {
						storeid : storeid,
					    info:"您有一笔新订单请查收", 
					    merchantid:merchantid,
					    storeid:storeid, 
					    msgtype:msgtype
					},
					//dataType: 'json', //服务器返回json格式数据
					type : 'post', //HTTP请求类型
					timeout : 1000, //超时时间设置为10秒；
					success : function(data) {
						console.info(data);
					}
				});
				console.info("------------");

				setData(info);
			}

			function setData(orderid) { //OrderQuery/QueryOrderDetail
				var url = "../OrderQuery/QueryOrderDetail";
				mui.ajax(url, {
					data: {
						OrderId: orderid
					},
					//dataType: 'json', //服务器返回json格式数据
					type: 'POST', //HTTP请求类型
					timeout: 3000, //超时时间设置为10秒；
					success: function(data) {
						var jsondata = mui.parseJSON(data);
						var orderuser = jsondata.customerId; //customerId
						var uphone = jsondata.uphone; //
						var canzhuo = jsondata.deskname;
						var odate = jsondata.date;
						var isarray = jsondata.backlist;
						var intstatus = jsondata.orderStatus;
						var orderstatus;
						if(intstatus == 2) {
							orderstatus = "待接单";
						} else if(intstatus == 1) {
							orderstatus = "点餐中";
						} else if(intstatus == 3) {
							orderstatus = "已下单";
						} else if(intstatus == 4) {
							orderstatus = "已结账";
						}
						var orderid = jsondata.id;
						var ordersum = jsondata.orderAmount;
						var deskname = jsondata.deskname;

						var ordeli = [];
						var spannum = document.getElementById("foodordernumber");
						var spandesk = document.getElementById("foodorderdesk");
						var spantime = document.getElementById("foodordertime");
						var spanstatus = document.getElementById("foodorderstatus");
						var spanamount = document.getElementById("foodorderamount");
						spannum.innerText = "订单号:" + orderid;
						spantime.innerText = odate;
						spanstatus.innerText = orderstatus;
						spanamount.innerText = ordersum / 100;
						spandesk.innerText = deskname;
						if(isarray.length > 0) {
							var ul = document.getElementById("foodorderul");
							var i = 0,
								cont = isarray.length;

							for(i; i < cont; i++) {
								var jsoncont = isarray[i];
								var name = jsoncont.oname;
								var num = jsoncont.num;
								var icode = jsoncont.gstatus;
								var status = "";
								if(icode == 1) { status = "未上菜"; } else { status = "已上菜"; }
								ordeli.push('<li class="mui-table-view-cell"><span>' + name + '</span> <span style="margin-left: 12px;"> x' + num + '</span> <span style="color:#CF2D28 ;float: right;">' + status + '</span></li>');
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

			function refreshData() {
				var info = sessionStorage.getItem("info");
				setData(info);
		

			}
		</script>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" onclick="backOrder()"></a>
			<a class="mui-action-menu mui-icon mui-icon-refresh mui-pull-right" onclick="refreshData()"></a>
			<h1 class="mui-title" style="color: #007AFF;">订单信息</h1>
		</header>
		<div class="mui-content">
			<div style="padding: 10px 10px; background: #FFFFFF; margin-bottom: 0px; padding-bottom: 0px; margin-top: 6px;">
				<div id="segmentedControl" class="mui-segmented-control mui-segmented-control-inverted mui-segmented-control-primary">
					<a class="mui-control-item mui-active" href="#item3"> 订单详情 </a>
						<a class="mui-control-item " href="#item2"> 订单状态 </a>
				
				</div>
			</div>
			<div>
				<div id="item2" class="mui-control-content " style="margin-top: 12px;">
					<ul class="mui-table-view">
						<li class="mui-table-view-cell"><span class="mui-icon mui-icon-person" style="background: #CCCCCC;"></span>
							<label style="margin-left: 60px;">订单已经提交</label></li>
					</ul>
				</div>
				<div id="item3" class="mui-control-content mui-active" style="margin-top: 12px;">
					<div style="width: 100%; padding-top: 14px; padding-bottom: 14px; background: #FFFFFF;">
						<div style="display: inline; margin-left: 8px; margin-right: 8px;">
							<label style="color: #8F8F94;" id="foodordernumber">
							订单号：54841655684</label> <label style="float: right; padding-right: 8px; color: #8F8F94;" id="foodorderdesk">桌位：VIP桌</label>
						</div>
						</br>
						<div style="display: inline; margin-left: 8px; margin-right: 8px; color: #8F8F94;">
							<label id="foodordertime"> 下单时间：3/25 16:46</label> <label style="float: right; padding-right: 8px; font-size: 18px; color: #0062CC;" id="foodorderstatus">待接单</label>
						</div>
						</br>
						<div style="display: inline; margin-left: 8px; margin-right: 8px; color: #8F8F94;">
							<label> 下单金额：</label><label style="color: #DD524D; font-size: 28px; margin-top: 12px;" id="foodorderamount">32.69</label>
						</div>
					</div>
					</br>

					<ul class="mui-table-view" id="foodorderul">
						<li class="mui-table-view-cell"><span>西红柿炒鸡蛋</span> <span style="margin-left: 12px;">x3</span> <span style="color: #CF2D28; float: right; font-style: italic;">已上菜</span>
						</li>
					</ul>
				</div>
			</div>
		</div>

	</body>

</html>