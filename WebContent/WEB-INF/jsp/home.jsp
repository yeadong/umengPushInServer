<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>点餐</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" href="../css/mui.min.css">
<style>
.mui-row.mui-fullscreen>[class*="mui-col-"] {
	height: 100%;
}

.mui-col-xs-3,.mui-col-xs-9 {
	overflow-y: auto;
	height: 100%;
}

.mui-segmented-control .mui-control-item {
	line-height: 50px;
	width: 100%;
}

.mui-control-content {
	display: block;
}

.mui-segmented-control.mui-segmented-control-inverted .mui-control-item.mui-active
	{
	background-color: #fff;
}

.mui-table-view-cell {
	font-size: 13px;
}

.myrightbtn button {
	font-size: small;
}

.rightgoods {
	float: left;
	vertical-align: middle;
}

#rightimg {
	width: 50px;
	height: 50px;
	float: left;
}

.ulliname {
	margin-left: 60px;
	color: #222222;
	width: 140px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

.ulliprice {
	color: #DD524D;
	font-size: 20px;
	margin-left: 10px;
	float: left;
}
.numadd {
				background: url(../img/jia.png);
				border-style: none;
				background-repeat: round;
				width: 24px;
				height: 26px;
				color: transparent;
				font-size: 20px;
			}
			
			.numred {
				color: transparent;
				background: url(../img/jian.png);
				border-style: none;
				background-repeat: round;
				width: 24px;
				font-size: 20px;
				height: 26px;
			}
</style>
</head>

<body>
	<header class="mui-bar mui-bar-nav">
		<a id="menu"
			class="mui-action-menu mui-icon mui-icon-bars mui-pull-right"
			href="#topPopover"></a>
		<h1 class="mui-title" style="color: #007AFF;">茉莉花店铺</h1>
	</header>
	<nav class="mui-bar mui-bar-tab mui-row">
		<div class="mui-col-sm-6" style="margin-top: 12px; margin-left: 6px"
			onclick="shoppingcat()">
			<label>共 </label> <label id="home_allnum" style="width: 50px;">0</label>
			<label> 件</label> <label> 总计:</label> <label id="home_allsum"
				style="color: #CF2D28; font-size: 28px; width: 50px;"></label>
		</div>
		<div class="mui-col-sm-5"
			style="float: right; margin-top: 12px; margin-right: 6px">
			<button class="mui-btn-danger" onclick="toScale()"
				style="float: right;">去结算</button>
		</div>
	</nav>
	<div class="mui-content mui-row mui-fullscreen">
		<div class="mui-col-xs-3">
			<div id="segmentedControls" style="font: smaller;"
				class="mui-segmented-control mui-segmented-control-inverted mui-segmented-control-vertical">
			</div>

		</div>
		<div id="segmentedControlContents" class="mui-col-xs-9"
			style="border-left: 1px solid #c8c7cc;"></div>
		<div id="modal" class="mui-modal" style="margin-top: 70%;">
			<div style="width: 70%; text-align: center; margin: 0 auto;">
				<div style="margin-bottom: 10%;">
					<img src="../img/xhscjd.png "
						style="margin-top: 40px; width: 160px; height: 160px;" />
					<p>西红柿炒鸡蛋</p>
					<p style="color: #DD524D; font-size: 20px;">12.25</p>

				</div>
			</div>
		</div>
		<nav class="goodscat mui-pull" id="goodscatid"
			style="display: none; background-color: rgba(0, 0, 0, .3); width: 100%; height: 100%;">
			<div style="height: 60%;" id="cancelgoodcat"></div>
			<div style="height: 4%; background: #FFFFFF;">
				<label class="mui-pull-left" style="color: #0062CC;"> |购物车</label><label
					class="mui-pull-right" onclick="clearcat()">清空</label>
			</div>
			<div class="mui-scroll-wrapper"
				style="position: relative; width: 100%; height: 30%; background: #FFFFFF; padding: 0px;">
				<div class="mui-scroll">
					<ul class="mui-table-view" id="goodcatul">
					</ul>
				</div>
			</div>

		</nav>

	</div>
	<div class="mui-modal" id="shownewpage">
		<div style="margin-left: 8px; margin-right: 8px; margin-top: 8px;">
			<div class="mui-card" style="margin: 0 auto; text-align: center;">
				<div class="mui-card-content">
					<h3 style="color: #007AFF; clear: both;" id="newpagedesk">3号桌</h3>
					<p id="newpagenum">·共 8 件</p>
				</div>
			</div>
			<div style="margin-top: 12px;">
				<div style="margin-bottom: 2px; color: #555555;">菜品:</div>
				<div class="mui-scroll-wrapper"
					style="position: relative; bottom: 50px; width: 80%; height: 140px; margin-bottom: 16px;">
					<div class="mui-scroll">
						<ul id="newpageul" style="color: #555555;">
						</ul>
					</div>
				</div>
				<div class="mui-input-row mui-input-range"
					style="margin: 0 auto; display: hide;">
					<label>人数：</label> <input type="number" placeholder="就餐人数">
				</div>
				<div style="clear: both; margin-top: 20px;">
					<label>订 单 总 计 :</label><label
						style="color: crimson; font-size: 28px; margin-left: 12px"
						id="newpageprice">￥ 11.36</label>
				</div>
				<div style="margin-top: 20px;">
					<input type="text" placeholder="请输入您的口味、忌口（可不填）" id="order_remark" />
				</div>
			</div>
			<div style="margin: 0 auto; text-align: center;">
				<button style="margin-top: 20px;" class="mui-btn"
					onclick="addFood()">继 续 点 菜</button>
				<button style="margin-top: 20px;" class="mui-btn" onclick="ggggo()">
					确 认 下 单</button>
			</div>
		</div>

	</div>
	<div id="right_liner" style="display: none;">

		<!--右上角弹出菜单-->
		<div id="topPopover" class="mui-popover">
			<ul class="mui-table-view">
				<li class="mui-table-view-cell"><a href="orderList">订单查看</a></li>
				<li class="mui-table-view-cell" style="display: none;"><a href="test">点餐首页</a></li>
			</ul>
		</div>
	</div>

</body>

<script type="text/javascript" src="../js/mui.min.js"></script>

<script>
/* *var strings = ''; 
console.log(${userInfo}==strings); 
var fristin=sessionStorage.getItem("fristin");
console.log("ssss");
console.log("fristin---------"+fristin);**/
var frist=sessionStorage.getItem("firstIn");
console.log("----------"+frist);
if (frist==null) {
	sessionStorage.setItem("firstIn","1");
	var jsc = ${requestScope.userInfo};
	var oid = ${requestScope.orderid};
		if (jsc!=null) {
			sessionStorage.setItem("userNo", jsc);
			console.log("----------");
		}	var userno=	sessionStorage.getItem("userNo");
		console.log(oid+"   "+jsc+"   "+userno);
		if (oid!=0) {
			console.log(oid);
			mui.alert("","检测到餐桌还有订单正在进行","确定",function (){
				sessionStorage.setItem("fooditemid", oid);
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
}

	var searchorder = window.location.search;
	console.log("---search-----" + searchorder);
	mui.init({
		swipeBack : true
	//启用右滑关闭功能
	});
	mui('.mui-scroll-wrapper').scroll({
		deceleration : 0.0005
	//flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
	});
	mui('.mui-popover').popover('toggle',
			document.getElementById("openPopover"));
	mui('.mui-popover').popover('hide');
	var controls = document.getElementById("segmentedControls");
	var contents = document.getElementById("segmentedControlContents");
	var html = [];
	var i = 1, j = 1, m = 10, //左侧选项卡数量+1
	n = 21; //每个选项卡列表数量+1
	var url = "../GoodsQuery/QueryAllGoods";
	mui
			.ajax(
					url,
					{
						data : {
							merchantid : "111114",
							storeid : "111114",
							deskid : "00"
						},
						//dataType: 'json', //服务器返回json格式数据
						type : 'post', //HTTP请求类型
						timeout : 1000, //超时时间设置为10秒；
						success : function(data) {
							var jdata = mui.parseJSON(data);
							var backcode = jdata.back_code;
							var leftarray = jdata.typeslist;
							var rightarray;
							var lefts = [];
							var rights = [];
							mui
									.each(
											leftarray,
											function(index, item) {
												var typename = item.name;
												lefts
														.push('<a class="mui-control-item" data-index="'
																+ index
																+ '" href="#content'
																+ (index + 1)
																+ '">'
																+ typename
																+ '</a>');
												var subarray = item.arraylist.length;
												var subitem = item.arraylist;
												rights
														.push('<div id="content'
																+ (index + 1)
																+ '" class="mui-control-content"><ul class="mui-table-view mui-media">');
												for (j = 0; j < subarray; j++) {
													var goodjson = subitem[j];
													var gname = goodjson.goodName;
													var gid = goodjson.id;
													var gprice = goodjson.goodPrice / 100;
													rights
															.push('<li class="mui-table-view-cell"><div><img src="../img/bhc.png" id="rightimg" /><p class="ulliname">'
																	+ gname
																	+ '</p><div style=" margin-top: 8px; "><p class="ulliprice">'
																	+ gprice
																	+ '</p>元&nbsp/份<p style="float: right;line-height:30px;" class="myrightbtn"><button class="numred">-</button><lable class="gnum" value="' + gid + '"> 0 </lable><button class="numadd">+</button></p></div></div></li>');
												}
												rights.push('</ul></div>');
											});
							controls.innerHTML = lefts.join('');
							contents.innerHTML = rights.join('');
							scroll();
							var menua = document.getElementById("menu");
							menua.addEventListener('tap', function() {
								var right = document
										.getElementById("right_liner");
								right.style.display = "block";

							});
							mui(".myrightbtn") 
									.on(
											'tap',
											'button',

											function() {
												var ss = this.innerHTML;
												var numlable = this.parentNode.children[1];
												var nameprice = this.parentNode.parentNode;
												var goodname = nameprice.parentNode.children[1].innerText;
												var goodprice = nameprice.parentNode.children[2].children[0].innerText;
												var numvalue = numlable.innerHTML;
												var valueid = numlable
														.getAttribute('value');
												switch (ss) {
												case '+':
													numvalue++;
													notifinum(valueid, 1,
															goodprice, goodname);
													break;
												case '-':
													if (numvalue > 0) {
														numvalue--;
														notifinum(valueid, 2,
																goodprice,
																goodname);
													}
													break;
												}
												numlable.innerHTML = '   '
														+ numvalue + '   ';
											});

							mui(".rightgoods")
									.on(
											'tap',
											'img',
											function() {
												var gna = this.parentNode.children[1].innerText;
												var gpr = this.parentNode.children[2].innerText;
												showitem(gna, gpr, '');

											});
						},
						error : function(xhr, type, errorThrown) {
							//异常处理；
							console.log(type);
						}
					});

	/* mui.getJSON('json/good.json', null, function(data) {
		
	});
	 */
	 
	/*  var oid = ${requestScope.orderid};
		console.log(oid);
		sessionStorage.getItem("fristin");
		if (isfist=="ino") {
			
		}else{
		if (oid!=0) {
			mui.alert("","检测到餐桌还有订单正在进行","确定",function (){
				sessionStorage.setItem("fooditemid", oid);
				mui.openWindow({
					url : "orderDetail",
					id : "orderDetail",
					waiting : {
						autoShow : false
					},
					createNew : false,
				});
			});
		}} */
	function addFood() {
		var newpageul = document.getElementById("shownewpage");
		newpageul.style.display = 'none';
		var drop = document.getElementsByClassName("mui-backdrop");
		drop[0].style.display = "none";
	}
	function ggggo() {
		var tempo = "orderId";
		console.log("----tempo    " + searchorder.indexOf(tempo));
		if (searchorder.indexOf(tempo) != -1) {
			var pos_start = searchorder.indexOf(tempo) + tempo.length + 1;
			var pos_end = searchorder.indexOf("&", pos_start);
			if (pos_end == -1) {
				var oid = searchorder.substring(pos_start);
				var desk = document.getElementById("newpagedesk");
				desk.innerText = desk.innerText + "加菜";
				jiacaiFood(oid);
			}
		} else {
			jiadanOrder();
		}

		/* */
		//			class="mui-backdrop mui-active"
		//			var rophide = document.gete(".mui-backdrop");
		//			rophide.style.display = 'none';
	}
	function jiacaiFood(oid) {
		var jsono = JSON.stringify(gonecat);
		jsono = jsono.substr(0, jsono.length);
		console.log(jsono);
		var newpageul = document.getElementById("shownewpage");
		var allsum = document.getElementById("home_allsum").innerText;
		newpageul.style.display = 'none';
		var d = new Date();
		var url = "../GoodsQuery/addWebFood"
		mui.ajax(url, {
			data : {
				goodlist : jsono,
				orderId : oid
			},
			headers : {
				'Content-Type' : 'application/json'
			},
			type : 'post', //HTTP请求类型
			timeout : 1000, //超时时间设置为10秒；
			success : function(data) {
				var jsondata = mui.parseJSON(data);
				var back_code = jsondata.back_code;
				if (back_code == 200) {
					mui.alert("加菜成功");
					sessionStorage.setItem("fooditemid", oid);
					mui.openWindow({
						url : "orderDetail",
						id : "orderDetail",
						waiting : {
							autoShow : false
						},
						createNew : false
					});
				}

			},
			error : function(xhr, type, errorThrown) {
				//异常处理；
				console.log(type);
			}
		});
	}
	function jiadanOrder() {
		var userno = sessionStorage.getItem("userNo");
		var jsono = JSON.stringify(gonecat);
		jsono = jsono.substr(0, jsono.length);
		console.log(jsono);
		var newpageul = document.getElementById("shownewpage");
		var allsum = document.getElementById("home_allsum").innerText;
		newpageul.style.display = 'none';
		var d = new Date();
		var url = "../GoodsQuery/addWapOrder"
		mui.ajax(url, {
			data : {
				goodlist : jsono,
				deskid : 7,
				storeid : 111114,
				cretime : d,
				personNum : 2,
				merchantId : 111114,
				customerId : userno,
				orderway : 3,
				orderAmount : allsum,
				remark : "web订单"
			},
			headers : {
				'Content-Type' : 'application/json'
			},
			type : 'post', //HTTP请求类型
			timeout : 1000, //超时时间设置为10秒；
			success : function(data) {
				var jsondata = mui.parseJSON(data);
				var orderid = jsondata.orderid;
				console.info(orderid);
				sessionStorage.setItem("storeid", 111114);
				sessionStorage.setItem("merchantid", 111114);
				sessionStorage.setItem("info", orderid);
				sessionStorage.setItem("msgtype", 1);
				mui.openWindow({
					url : "FoodOrder",
					id : "FoodOrder",
					waiting : {
						autoShow : false
					},
					createNew : false
				});

			},
			error : function(xhr, type, errorThrown) {
				//异常处理；
				console.log(type);
			}
		});
	}

	function toScale() {
		if (tempcat != '') {
			var glength = gonecat.length;
			var cathtml = [];
			var allnum = 0;
			for (var i = 0; i < glength; i++) {
				var s = gonecat[i];
				var num = s.num;
				if (num > 0) {
					allnum = eval(allnum + '+' + num);
					cathtml.push('<li> <label>' + s.name + '</label> <label>x '
							+ num + '</label></li>');
				}
			}
			var newpageul = document.getElementById("newpageul");
			var newpagenum = document.getElementById("newpagenum");
			newpagenum.innerText = "共 " + allnum + " 件";
			var allsum = document.getElementById("home_allsum");
			var newpageprice = document.getElementById("newpageprice");
			newpageprice.innerHTML = allsum.innerHTML;
			newpageul.innerHTML = cathtml.join('');
			var newpageul = document.getElementById("shownewpage");
			newpageul.style.display = 'block';
			mui('#shownewpage').popover('show');

		}
		//			mui(".gnum").each(function(index, item) {
		//				var num = item.innerHTML;
		//				if(num > 0) {
		//					var parentview = item.getAttribute('value');
		//					
		//					console.log("num: " + num + " value: " + parentview);
		//				}
		//			});
	}

	function show() {
		var bm = document.getElementById("bootmpop");
		bm.style.display = "block";
	}
	//加入购物车 判断是否重复
	var tempcat = [];
	var gonecat = [];

	function notifinum(gid, tempnum, price, name) {
		var tem = tempcat.indexOf(gid);
		if (tem > -1) {
			var glength = gonecat.length;
			for (var i = 0; i < glength; i++) {
				var s = gonecat[i];
				if (s.id == gid) {
					if (tempnum == 2) {
						s.num--;
					} else {
						s.num++;
					}
				}
			}
		} else {
			tempcat.push(gid);
			mygoodentity = new goodcatentity(gid, name, 1, price);
			gonecat.push(mygoodentity);
		}

		var allnum = document.getElementById("home_allnum");
		var allsum = document.getElementById("home_allsum");
		var allvalue = allnum.innerHTML;
		var sumvalue = allsum.innerHTML;
		if (tempnum == 2) {
			allvalue--;
			sumvalue = eval(sumvalue + "-" + price);
		} else {
			allvalue++;
			sumvalue = eval(sumvalue + "+" + price);
		}
		allnum.innerHTML = allvalue;
		allsum.innerHTML = sumvalue;
	}
	var goodcatdiv = document.getElementById("goodscatid");
	//显示购物车
	function shoppingcat() {
		var goodcatul = document.getElementById("goodcatul");
		var cathtml = [];
		var ishow = goodcatdiv.style.display;
		if (ishow == 'none') {
			goodcatdiv.style.display = 'block';
			var glength = gonecat.length;
			for (var i = 0; i < glength; i++) {
				var s = gonecat[i];
				if (s.num > 0) {
					cathtml
							.push('<li style=" width: 100%; height: 30px;" value='
									+ s.id
									+ '><label class="mui-pull-left">'
									+ s.name
									+ '</label><label style="color: #DD524D;"></label><label class="mui-pull-right">x '
									+ s.num + '</label></li>');
				}
			}
			goodcatul.innerHTML = cathtml.join('');
		}
		if (ishow == 'block') {
			goodcatdiv.style.display = 'none';
		}
	}
	//单击弹出
	function showitem(name, price, img) {
		console.log(name + price);
		var divnote = document.getElementById("modal").children[0].children[0];
		divnote.children[1].innerText = name;
		divnote.children[2].innerText = price;
		mui('#modal').popover('show');

	}

	function clearcat() {
		var ishow = document.getElementById("goodcatul");
		ishow.innerHTML = "";
		tempcat = [];
		gonecat = [];
		var newpagenum = document.getElementById("newpagenum");
		newpagenum.innerText = " 0 ";
		var homeallnum = document.getElementById("home_allnum");
		homeallnum.innerText = " 0 ";
		var allsum = document.getElementById("home_allsum");
		allsum.innerText = "";
		mui(".gnum").each(function() {
			this.innerText = " 0 ";
		})
	}
	var dom = document.getElementById("cancelgoodcat");
	dom.onclick = function() {
		var ishow = goodcatdiv.style.display;
		if (ishow == 'block') {
			goodcatdiv.style.display = 'none';
		}
	}
</script>
<script>
	//id, name, jia jian
	function goodcatentity(id, name, num, price) {
		this.id = id;
		this.name = name;
		this.num = num;
		this.price = price;
	}

	function scroll() {
		controls.querySelector('.mui-control-item').classList.add('mui-active');
		(function() {
			var controlsElem = document.getElementById("segmentedControls");
			var contentsElem = document
					.getElementById("segmentedControlContents");
			var controlListElem = controlsElem
					.querySelectorAll('.mui-control-item');
			var contentListElem = contentsElem
					.querySelectorAll('.mui-control-content');
			var controlWrapperElem = controlsElem.parentNode;
			var controlWrapperHeight = controlWrapperElem.offsetHeight;
			var controlMaxScroll = controlWrapperElem.scrollHeight
					- controlWrapperHeight; //左侧类别最大可滚动高度
			var maxScroll = contentsElem.scrollHeight
					- contentsElem.offsetHeight; //右侧内容最大可滚动高度
			var controlHeight = controlListElem[0].offsetHeight; //左侧类别每一项的高度
			var controlTops = []; //存储control的scrollTop值
			var contentTops = [ 0 ]; //存储content的scrollTop值
			var length = contentListElem.length;
			for (var i = 0; i < length; i++) {
				controlTops.push(controlListElem[i].offsetTop + controlHeight);
			}
			for (var i = 1; i < length; i++) {
				var offsetTop = contentListElem[i].offsetTop;
				if (offsetTop + 100 >= maxScroll) {
					var height = Math.max(offsetTop + 100 - maxScroll, 100);
					var totalHeight = 0;
					var heights = [];
					for (var j = i; j < length; j++) {
						var offsetHeight = contentListElem[j].offsetHeight;
						totalHeight += offsetHeight;
						heights.push(totalHeight);
					}
					for (var m = 0, len = heights.length; m < len; m++) {
						contentTops
								.push(parseInt(maxScroll
										- (height - heights[m] / totalHeight
												* height)));
					}
					break;
				} else {
					contentTops.push(parseInt(offsetTop));
				}
			}
			contentsElem.addEventListener('scroll', function() {
				var scrollTop = contentsElem.scrollTop;
				for (var i = 0; i < length; i++) {
					var offsetTop = contentTops[i];
					var offset = Math.abs(offsetTop - scrollTop);
					//						console.log("i:"+i+",scrollTop:"+scrollTop+",offsetTop:"+offsetTop+",offset:"+offset);
					if (scrollTop < offsetTop) {
						if (scrollTop >= maxScroll) {
							onScroll(length - 1);
						} else {
							onScroll(i - 1);
						}
						break;
					} else if (offset < 20) {
						onScroll(i);
						break;
					} else if (scrollTop >= maxScroll) {
						onScroll(length - 1);
						break;
					}
				}
			});
			var lastIndex = 0;
			//监听content滚动
			var onScroll = function(index) {
				if (lastIndex !== index) {
					lastIndex = index;
					var lastActiveElem = controlsElem
							.querySelector('.mui-active');
					lastActiveElem
							&& (lastActiveElem.classList.remove('mui-active'));
					var currentElem = controlsElem
							.querySelector('.mui-control-item:nth-child('
									+ (index + 1) + ')');
					currentElem.classList.add('mui-active');
					//简单处理左侧分类滚动，要么滚动到底，要么滚动到顶
					var controlScrollTop = controlWrapperElem.scrollTop;
					if (controlScrollTop + controlWrapperHeight < controlTops[index]) {
						controlWrapperElem.scrollTop = controlMaxScroll;
					} else if (controlScrollTop > controlTops[index]
							- controlHeight) {
						controlWrapperElem.scrollTop = 0;
					}
				}
			};
			//滚动到指定content
			var scrollTo = function(index) {
				contentsElem.scrollTop = contentTops[index];
			};
			mui(controlsElem).on('tap', '.mui-control-item', function(e) {
				scrollTo(this.getAttribute('data-index'));
				return false;
			});
		})();
	}
</script>

</html>