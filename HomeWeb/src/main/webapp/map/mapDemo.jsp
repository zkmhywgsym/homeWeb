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
body,html {
	width: 100%;
	height: 100%;
	margin: 0;
}

#allmap {
	height: 100%;
	width: 100%;
}

#r-result {
	width: 100%;
}
</style>
<script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=nMbwPGhLNAptpWmfmWTUv8dP"></script>
<title>添加/删除覆盖物</title>
</head>
<body>
	<div id="allmap"></div>
	<div id="r-result">
		<input type="button" onclick="reLocation()" value="重新定位" /> <input
			type="button" onclick="toUpdate()" value="版本控制" /> <input
			type="button" onclick="add_circle()" value="画圆" /> <input
			type="button" onclick="removeAllOverLays()" value="除去层" /> <input
			type="button" onclick="resetLocation()" value="重定位" /> <input
			type="button" onclick="addSomePoints()" value="随机点" /> <input
			type="button" onclick="add_fox()" value="画狐" /> <input
			type="button" onclick="deletePoint()" value="删除指定点" />
	</div>
	<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(112.560637,37.81407);
	map.centerAndZoom(point, 15);
	map.centerAndZoom(point, 15);
	//map.disableDoubleClickZoom(true);//禁止双击
	map.disableDragging();     //禁止拖拽
	map.addEventListener("click",function(e){//点击取坐标
		alert(e.point.lng + "," + e.point.lat);
	});
	var marker = new BMap.Marker(point); // 创建点
	var circle = new BMap.Circle(point,500,{strokeColor:"red", strokeWeight:2, strokeOpacity:10}); //创建圆(点，半径，颜色，笔粗，笔画透明度)
	var fox = new BMap.Icon("http://developer.baidu.com/map/jsdemo/img/fox.gif", new BMap.Size(300,157));
	
	function toUpdate() {//跳到版本控制页面
		window.location = "<%=basePath%>web/fileUpload.jsp";
	}
	function reLocation() {//更新坐标
		 $.ajax({
             url: "<%=basePath%>map/getPoint.action?",
				type : "get",
				success : function(text) {
					var obj = eval('(' + text + ')');
					var point = new BMap.Point(obj.latitude, obj.longitude);
					map.centerAndZoom(point, 19);
					var mk = new BMap.Marker(point);
					map.addOverlay(mk);
					mk.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
					var label = new BMap.Label("我是文字标注哦", {
						offset : new BMap.Size(20, -10)
					});
					mk.setLabel(label);
					showWin(mk);
					addMarkerMenu(mk);
				},
				err : function(e) {
					alert(e);
				}
			});
		}

		function add_fox(location) {//添加狐狸图片
			var marker2 = new BMap.Marker(location, {
				icon : fox
			});
			map.addOverlay(marker2); //增加圆
		}
		//计算距离
		//var distance=map.getDistance(pointA,pointB).toFixed(0)+' 米。';
		
		//添加覆盖物
		function add_circle() {
			map.addOverlay(circle); //增加圆
		}
		function addOverlay(point) {
			var mk = new BMap.Marker(point);
			var label = new BMap.Label("flag", {
				offset : new BMap.Size(20, -10)
			});
			map.addOverlay(mk);
			mk.setLabel(label);
			mk.addEventListener("click", getAttr);
		}
		function removeOverlay(overlay) {
			map.removeOverlay(overlay);//移除指定标
		}
		function removeAllOverLays() {
			//map.removeOverlay(circle);//移除指定标
			map.clearOverlays();//移除全部
		}
		function resetLocation() {
			map.panTo(new BMap.Point(113.262232, 23.154345)); //定位到点
		}
		// 随机向地图添加25个标注
		function addSomePoints() {
			var bounds = map.getBounds();
			var sw = bounds.getSouthWest();
			var ne = bounds.getNorthEast();
			var lngSpan = Math.abs(sw.lng - ne.lng);
			var latSpan = Math.abs(ne.lat - sw.lat);
			for (var i = 0; i < 25; i++) {
				var point = new BMap.Point(sw.lng + lngSpan
						* (Math.random() * 0.7), ne.lat - latSpan
						* (Math.random() * 0.7));
				addMarker(point);
			}
		}
		
		function getAttr() {
			var p = marker.getPosition(); //获取marker的位置
			alert("marker的位置是" + p.lng + "," + p.lat);
		}
		//删除指定点
		function deletePoint(mark){
		var allOverlay = map.getOverlays();
		/* for (var i = 0; i < allOverlay.length -1; i++){
			alert(i);
			if(allOverlay[i].getLabel().content == "flag"){
				map.removeOverlay(allOverlay[i]);
				alert(i);
			}
		} */
		for(var i=0;i< allOverlay.length - 1 ; i++){
		}
		//alert(allOverlay[0].getLabel());
		removeOverlay(allOverlay[0]);
	}	
		//显示弹出窗口
	function showWin(marker){
			var opts = {
					  width : 200,     // 信息窗口宽度
					  height: 100,     // 信息窗口高度
					  title : "海底捞王府井店" , // 信息窗口标题
					  enableMessage:true,//设置允许信息窗发送短息
					  message:"亲耐滴，晚上一起吃个饭吧？戳下面的链接看下地址喔~"
				}
			var infoWindow = new BMap.InfoWindow("地址：北京市东城区王府井大街88号乐天银泰百货八层", opts);  // 创建信息窗口对象 
			marker.addEventListener("click", function(){          
			map.openInfoWindow(infoWindow,point); //开启信息窗口
			});
		}
		window.onload=function(){
			addMapMenu();
		}
		function addMarkerMenu(mark){
			var removeMarker = function(e,ee,marker){
			console.info("addMarkerMenu");
				map.removeOverlay(mark);
			}
			//创建右键菜单
			var markerMenu=new BMap.ContextMenu();
			markerMenu.addItem(new BMap.MenuItem('删除',removeMarker.bind(mark)));
			mark.addContextMenu(markerMenu);
		}
		function addMapMenu(){
			var menu = new BMap.ContextMenu();
			var txtMenuItem = [
				{
					text:'放大',
					callback:function(){map.zoomIn()}
				},
				{
					text:'重新定位',
					callback:function(){reLocation()}
				},
				{
					text:'缩小',
					callback:function(){map.zoomOut()}
				}
			];
			for(var i=0; i < txtMenuItem.length; i++){
				menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
			}
			map.addContextMenu(menu);
		}
	</script>
</body>
</html>
