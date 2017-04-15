<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>接单ing</title>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$.get("GoodsQuery/getCat", {
			storeid : 111114
		}, function(data, status) {//url data callback
			alert("ssss");
		});
	});
</script>
<body>
	<div id="food_list"></div>

</body>
</html>