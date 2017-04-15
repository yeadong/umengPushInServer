<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加设备</title>
<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		alert("ssss");
		$("#kxfwap_send").click(function() {
			$("#food_submit").submit();
		});
		$("#kxfwap_push").click(function() {
			$("#pushmsg").submit();
		});
	});
</script>
</head>
<body>
	<form action="/UserDevice/AddDevice" method="post" id="food_submit">
		<input type="text" class="storeid" name="storeid" value="storeid"
			style="width: 120px; height: 20px; margin: 20px" /> <input
			type="text" class="dtoken" name="dtoken" value="dtoken"
			style="width: 120px; height: 20px; margin: 20px" /> <input
			type="text" class="dmac" name="dmac" value="dmac"
			style="width: 120px; height: 20px; margin: 20px" /> <input
			type="text" ="text" class="dimei" name="dimei" value="dimei"
			style="width: 120px; height: 20px; margin: 20px" /> <input
			type="text" class="dnote" name="dnote" value="dnote"
			style="width: 120px; height: 20px; margin: 20px" /> <input
			type="text" class="umengtoken" name="umengtoken" value="umengtoken"
			style="width: 120px; height: 20px; margin: 20px" /> <input
			type="text" class="appversion" name="appversion" value="appversion"
			style="width: 120px; height: 20px; margin: 20px" /> <input
			type="text" class="storeName" name="storeName" value="storeName"
			style="width: 120px; height: 20px; margin: 20px" />

	</form>
	<button id="kxfwap_send" style="margin-top: 20px; font-size: 22px">提
		交</button>


	<h2>测试消息推送</h2>
	<form action="SendDevice" method="post" id="pushmsg">
		<input type="text" class="devicetoken" name="devicetoken"
			style="width: 120px; height: 20px; margin: 20px" value="devicetoken">
		<input type="text" class="msg" name="msg"
			style="width: 120px; height: 20px; margin: 20px" value="msg">
	</form>
	<button id="kxfwap_push" style="margin-top: 20px; font-size: 22px">发送消息</button>

</body>
</html>