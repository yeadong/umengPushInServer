<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link href="../css/mui.min.css" rel="stylesheet" />
<script src="../js/mui.min.js"></script>
<script src="../js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	var did ='<%=request.getAttribute("deskid")%>';
	sessionStorage.setItem("deskId", did);
	function getUserCode() {
		var did = sessionStorage.getItem("deskId");
		console.log("did----" + did);
		/* $.ajax({
			url : "http://weijing.f3322.net:7070/UmengPushServer/OrderWap/testsmaple",
			type : 'POST',
			dataType : 'JSONP',//here
			data:{did : did},
			success : function(data) {
				console.log(data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
				console.log(XMLHttpRequest.readyState);
				console.log(textStatus);
			}

		});  */
		window.location = "http://weijing.f3322.net:9090/testSSM/api/qrcode/diancan?deskNo="
				+ did;

		/** $.ajax({
			url : "http://weijing.f3322.net:7070/UmengPushServer/Aliuser/test",
			type : 'GET',
			dataType : 'JSONP',//here
			data : {
				deskNo : did,
				browser : "ali_pay_browser"
			},
			success : function(data) {
				console.log("-------" + data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
				console.log(XMLHttpRequest.readyState);
				console.log(textStatus);
			}

		}); */
		/** mui.ajax(url, {
			data : {
				deskNo : did
			},
			type : 'post', //HTTP请求类型
			timeout : 1000, //超时时间设置为10秒；
			success : function(data) {
				console.log(data);
			},
			error : function(xhr, type, errorThrown) {
				console.log(type + errorThrown + xhr);
			}
		});
		 */
	}
</script>

</head>
<body>
	<div class="mui-content">
		<h3 style="margin-top: 100px;">就餐桌位:${requestScope.deskid}在${requestScope.bro}</h3>
		<button style="margin-top: 100px; margin-left: 50px; font-size: 20px;"
			onclick="getUserCode()">开 始 点 餐</button>
	</div>
</body>
</html>