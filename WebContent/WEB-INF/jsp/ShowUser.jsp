<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示用户</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" href="../css/mui.min.css">
</head>
<body>
	<h2>${requestScope.userInfo}</h2>

	<script src="../js/mui.min.js"></script>
	<script>
		mui.init({
			swipeBack : true
		//启用右滑关闭功能
		});
	</script>
	<script>
		function mycloser() {
			var url = "getCat";
			mui.ajax(url, {
				data : {
					storeid : "111114"
				},
				//dataType: 'json', //服务器返回json格式数据
				type : 'get', //HTTP请求类型
				timeout : 1000, //超时时间设置为10秒；
				success : function(data) {
					console.info(data);
				},
				error : function(xhr, type, errorThrown) {
					//异常处理；
					console.log(type);
				}
			});
		}
	</script>
</body>
</html>