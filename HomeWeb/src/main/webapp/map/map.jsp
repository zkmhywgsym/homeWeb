<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="baidu map">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!--移动浏览器端正常显示-->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;}
		#allmap{height:100%;width:100%;}
		#r-result{width:100%;}
	</style>
	<script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=nMbwPGhLNAptpWmfmWTUv8dP"></script>
	<title>添加/删除覆盖物</title>
</head>
<body>
	<div id="allmap"></div>
	<div id="r-result">
		<input type="button" onclick="reLocation();" value="重新定位" />
	</div>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(112.560637,37.81407);
	map.centerAndZoom(point, 15);
	
	var marker = new BMap.Marker(point); // 创建点
	
	function reLocation() {
		 $.ajax({
             url: "<%=basePath%>test/testGetPoint.action?",
			type : "get",
			success : function(text) {
				var obj = eval('(' + text + ')');
				console.info("latitude:"+obj.latitude+",longitude:"+obj.longitude);
				var point = new BMap.Point(obj.latitude,obj.longitude); 
                map.centerAndZoom(point,19);  
                var mk = new BMap.Marker(point);  
                map.addOverlay(mk);  
       		},
       		err:function(e){
       				alert(e);
       		}
		});
	}
</script>
</body>
</html>
