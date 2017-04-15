<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>首页</title>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		/* $("#submit").click(function() {
			$("#indexsub").submit();
		}); */
		$("#kxfsubmit").click(function() {
			var name = $(".uname").val();
			var pwd = $(".upwd").val();
			$.post("OrderWap/LoginCon", {
				uname : name,
				upwd : pwd
			}, function(data, status) {//url data callback
				$(".showmsg").text("data:" + data);
			});
		});

	});

	function getUserId() {
		var url = "http://weijing.f3322.net:7070/UmengPushServer/OrderWap/getZFBUserID";
		$.post(url, {
			app_id : '2016090201837003',
			scope : 'auth_user'
		}, function(data, status) {//url data callback
			consle.log(data + status);
		});
	}
	function hehehe(id, name) {
		this.id = id;
		this.name = name;
	}
	function zhuanhua() {
		console.log("-----zhuan");
		var hehe = new hehehe("123", "dassadsad");
		var s = JSON.stringify(hehe);
		console.log(s);
		$.post("http://weijing.f3322.net:7070/UmengPushServer/wuyu/wuyong1",
				{}, function(data, status) {
					console.log(data + status);
				});
	}
</script>

</head>
<body>
	<div class="showmsg"></div>
	<div style="text-align: center;">
		<h3>登 录</h3>
		</br> 用户名 <input name="uname" style="width: 150px; height: 30px;"
			class="uname" type="text"> </br>
		</p>
		密 码 <input name="upwd" style="width: 150px; height: 30px;"
			class="upwd" type="password"> </br>
		</p>
		</p>

		<button id="kxfsubmit" style="width: 60px; height: 30px;">提交</button>
	</div>

	<form method="post" action="TransactionTest/upimg"
		enctype="multipart/form-data">
		<input type="file" name=img id="file" class="enterInput"> <input
			type="submit" value="submit">
	</form>

	<button onclick="getUserId()">USERID</button>
	<button onclick="zhuanhua()">zhuanhhua</button>

	<!-- 
	<form action="OrderWap/LoginCon" id="indexsub" method="post">
		用户名 <input name="uname" class="uname" type="text"> </br> 密码 <input
			name="upwd" class="upwd" type="text"> </br>

		<button id="submit">提交</button>
	</form> -->
</body>
</html>