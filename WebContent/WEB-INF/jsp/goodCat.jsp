<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>GOODCAT</title>
<link rel="stylesheet" type="text/css" href="/css/docs.min.css">
<script type="text/javascript" src="../js/jquery-3.1.0.min.js"></script>
<script>
	$(document).ready(function() {
		$("#sendBtn").click(function() {
			alert("23");
			var sid = "111114";
			$.get({
				url : 'getCat',
				data : {
					storeid : sid
				},
				success : function(data) {
					$each();
				}
			});
		});
	});
</script>
<script>
	if (typeof jQuery == 'undefined') {
		window.alert("没有jquery");
	}
</script>

<script type="text/javascript">
	function getData() {
		alert("sss");
		$.get("getCat", {
			storeid : sid
		}, function(data, status) {//url data callback
			$.each(position, item);
		});
	}
</script>
</head>
<body>

	<button id="sendBtn">sdaas</button>
	<div class="row">
		<div class="col-lg-6">
			<div class="input-group">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">Go!</button>
				</span> <input type="text" class="form-control" placeholder="Search for...">
			</div>
			<!-- /input-group -->
		</div>
		<!-- /.col-lg-6 -->
		<div class="col-lg-6">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Search for...">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">Go!</button>
				</span>
			</div>
			<!-- /input-group -->
		</div>
		<!-- /.col-lg-6 -->
	</div>
	<!-- /.row -->

</body>