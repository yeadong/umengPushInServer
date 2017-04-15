<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link href="../css/mui.min.css" rel="stylesheet" />
<script src="../js/mui.min.js"></script>

</head>

<body>
	<header class="mui-bar mui-bar-nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title" style="color: #007AFF;">订单列表</h1>
		<a id="menu"
			class="mui-action-menu mui-icon mui-icon-bars mui-pull-right"
			href="#topPopover"></a>
	</header>
	<div class="mui-content">
		<ul
			class="mui-table-view mui-table-view-striped mui-table-view-condensed"
			id="oldorder_ul">
			<li class="mui-table-view-cell">
				<div class="mui-table">
					<div class="mui-table-cell mui-col-xs-9">
						<h5 class="mui-h4 mui-ellipsis" style="color: #007AFF;">待支付</h5>
						<h5 class="mui-ellipsis" style="margin-top: 4px;">订单号：1235978565</h5>
					</div>
					<div class="mui-table-cell mui-col-xs-3 mui-text-right">
						<span class="mui-h5">01-03 12:25</span>
						<h3 style="color: #CF2D28;">36.98</h3>
					</div>
				</div>
			</li>
		</ul>
		<div id="right_liner" style="display: none;">
			<div id="topPopover" class="mui-popover">
				<ul class="mui-table-view">
					<li class="mui-table-view-cell" style="display: none;"><a href="test">点餐首页</a></li>
					<li class="mui-table-view-cell"><a onclick="refreshMy()">刷新</a></li>
				</ul>
			</div>
		</div>

	</div>
	<script>
		window.onload = function() {
			refreshMy();
		}
		function refreshMy() {
			var customerid = sessionStorage.getItem("userNo");
			var url = "../ComOrderQuery/webQueryAllOrder";
			mui.ajax(url, {
				data : {
					customerId : customerid,
					nowpage : 10
				},
				//dataType: 'json', //服务器返回json格式数据
				type : 'post', //HTTP请求类型
				timeout : 1000, //超时时间设置为10秒；
				success : function(data) {
					setData(data);
					var newpageul = document.getElementById("right_liner");
					newpageul.style.display = 'none';
					var drop = document.getElementsByClassName("mui-backdrop");
					console.log(drop);
					if (drop.length>0)
						drop[0].style.display = "none";
				}
			});
			console.info("------------");
		}
		var menua = document.getElementById("menu");
		menua.addEventListener('tap', function() {
			var right = document.getElementById("right_liner");
			right.style.display = "block";

		});
		function setData(json) {
			var data = mui.parseJSON(json);
			var back_code = data.back_code; //customerId
			if (back_code != 200) {
				mui.alert("获取信息错误");
				return;
			}
			var backlist = data.back_list;
			var phtml = [];
			var orderul = document.getElementById("oldorder_ul");
			mui
					.each(
							backlist,
							function(index, item) {
								var da = item.nowDate;
								var status = item.orderStatus;
								var varstatus;
								if (status == 2) {
									varstatus = "待接单";
								} else if (status == 1) {
									varstatus = "点餐中";
								} else if (status == 3) {
									varstatus = "已下单";
								} else if (status == 4) {
									varstatus = "已上齐";
								} else if (status == 5) {
									varstatus = "已结账";
								} else if (status == 6) {
									varstatus = "已撤单";
								}
								var am = item.orderAm / 100;
								am = formatMoney(am, 2, "", '', '');
								var id = item.orderId;
								phtml
										.push('<li class="mui-table-view-cell" id="orderli" value='+id+'><div class="mui-table"><div class="mui-table-cell mui-col-xs-9"><h5 class="mui-h4 mui-ellipsis" style="color:#007AFF;">'
												+ varstatus
												+ '</h5><h5 class="mui-ellipsis" style="margin-top: 4px;">订单编号:'
												+ id + '</h5>');
								phtml
										.push('</div><div class="mui-table-cell mui-col-xs-3 mui-text-right"><span class="mui-h5">'
												+ da
												+ '</span><h3 class="mui-h3" style="color: #CF2D28;">'
												+ am + '</h3></div></div>')
							});
			orderul.innerHTML = phtml.join('');
			mui("#oldorder_ul").on('tap', 'li', function() {
				var orderId = this.getAttribute("value");
				sessionStorage.setItem("fooditemid", orderId);
				mui.openWindow({
					url : "orderDetail",
					id : "orderDetail",
					waiting : {
						autoShow : false
					},
					createNew : false,
				});
			});
		}
		function formatMoney(number, places, symbol, thousand, decimal) {
			number = number || 0;
			places = !isNaN(places = Math.abs(places)) ? places : 2;
			symbol = symbol !== undefined ? symbol : "$";
			thousand = thousand || ",";
			decimal = decimal || ".";
			var negative = number < 0 ? "-" : "", i = parseInt(number = Math
					.abs(+number || 0).toFixed(places), 10)
					+ "", j = (j = i.length) > 3 ? j % 3 : 0;
			return symbol
					+ negative
					+ (j ? i.substr(0, j) + thousand : "")
					+ i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand)
					+ (places ? decimal
							+ Math.abs(number - i).toFixed(places).slice(2)
							: "");
		}
	</script>
</body>

</html>