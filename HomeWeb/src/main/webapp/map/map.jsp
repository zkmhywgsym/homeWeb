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
		<input type="button" onclick="reLocation()" value="重新定位" /> 
		<input type="button" onclick="toUpdate()" value="版本控制" /> 
		<input type="button" onclick="addCircle()" value="画圆" /> 
		<input type="button" onclick="clearMap()" value="除去层" /> 
		<input type="button" onclick="resetLocation()" value="重定位" /> 
		<input type="button" onclick="addSomePoints()" value="随机点" /> 
		<input type="button" onclick="addFox()" value="画狐" /> 
		<input type="button" onclick="deletePoint()" value="删除指定点" />
		<input type="button" onclick="getPoints()" value="画全部定位" />
		<input type="button" onclick="drawMap()" value="画地图" />
	</div>
	<script type="text/javascript">
	///////////////////// 百度地图API功能/////////////////////////////////////////
	var map = new BMap.Map("allmap");
	var localPoint = new BMap.Point(112.560637,37.81407);
	var clickPoint;
	map.centerAndZoom(localPoint, 15);
	//map.disableDoubleClickZoom(true);//禁止双击
	map.enableScrollWheelZoom();//启用滚轮放大缩小
	//计算距离
	//var distance=map.getDistance(pointA,pointB).toFixed(0)+' 米。';
	//map.disableDragging();     //禁止拖拽
	var fox = new BMap.Icon("http://developer.baidu.com/map/jsdemo/img/fox.gif", new BMap.Size(300,157));
	//////////////////////////////初始值完成////////////////////////////////
	map.addEventListener("click",function(e){//点击取坐标
		alert(e.point.lng + "," + e.point.lat);
	});
	map.addEventListener("rightclick",function(e){//点击取坐标
		addMapMenu(e);
	});
	
	function getDistance(first,second){
		return map.getDistance(first,second).toFixed(0);//toFixed(num)保留小数倍数
	}
	function reLocation() {//更新坐标
		$.ajax({
             url: "<%=basePath%>map/getPoint.action?",
				type : "get",
				success : function(text) {
					var obj = eval('(' + text + ')');
					var point = new BMap.Point(obj.longitude,obj.latitude);
					map.centerAndZoom(point, 19);
					var mk = new BMap.Marker(point);
					map.addOverlay(mk);
					mk.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
					var label = new BMap.Label(obj.name, {
						offset : new BMap.Size(20, -10)
					});
					mk.setLabel(label);
					bindWin(mk);
					addMarkerMenu(mk);
				},
				err : function(e) {
					alert(e);
				}
			});
		}
		function setCenter(point){
			map.centerAndZoom(point, 19);
		}
		function drawMap() {//绘制地图
		$.ajax({
             url: "<%=basePath%>map/drawMap.action?",
				type : "get",
				success : function(text) {
					var obj = eval('(' + text + ')');
					for(var i=0;i<obj.length;i++){
						//添加工作区
						var workSpace=obj[i].work;
						var location=new BMap.Point(workSpace.longitude,workSpace.latitude);
						var circle = new BMap.Circle(location,workSpace.radius,{strokeColor:"red", strokeWeight:2, strokeOpacity:10});
						map.addOverlay(circle);
						//添加手持机位置
						var sons=obj[i].sons;
						for(var j=0;j<sons.length;j++){
							var point=new BMap.Point(sons[j].longitude,sons[j].latitude);
							var mark=addOverlay(point,sons[j].name);
							map.addOverlay(mark);
							var distance=getDistance(location,point);
							if (distance>workSpace.radius) {
								mark.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
							}
						}
					}
				},
				err : function(e) {
					alert(e);
				}
			});
		}
		function getPoints() {//获取全部坐标
		$.ajax({
             url: "<%=basePath%>map/getAllPoints.action?",
				type : "get",
				data : {test:"name"},
				success : function(json) {
					var arr = eval('(' + json + ')');
					console.info("success:"+obj);
					for(var i=0;i<arr.length;i++){
						var obj=arr[i];
						var point = new BMap.Point(obj.longitude,obj.latitude);
						map.centerAndZoom(point, 19);
						var mk = new BMap.Marker(point);
						map.addOverlay(mk);
						mk.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
						var label = new BMap.Label("name:"+obj.name+";time:"+obj.time, {
							offset : new BMap.Size(20, -10)
						});
						mk.setLabel(label);
						bindWin(mk);
						addMarkerMenu(mk);
					}
				},
				err : function(e) {
					alert(e);
				}
			});
		}

		function addCircle(localPoint,radius){
			if(radius<0){
				radius=500;
			}
			var circle = new BMap.Circle(localPoint,radius,{strokeColor:"red", strokeWeight:2, strokeOpacity:10}); //创建圆(点，半径，颜色，笔粗，笔画透明度)
			map.addOverlay(circle);
		}
		function addFox(){
			addImage(localPoint,fox);
		}
		function addImage(location,image) {//添加狐狸图片
			var marker2 = new BMap.Marker(location, {
				icon : image
			});
			map.addOverlay(marker2); //增加圆
		}
		
		function addOverlay(point,msg,clickEvent) {
			console.info("addOverlay");
			var p;
			var m;
			if(point==null){
				p=localPoint;
			}else{
				p=point;
			}
			if(msg==null){
				m="";
			}else{
				m=msg;
			}
			var mk = new BMap.Marker(p);
			var label = new BMap.Label(m, {
				offset : new BMap.Size(20, -10)
			});
			map.addOverlay(mk);
			mk.setLabel(label);
			if(clickEvent!=null){
				mk.addEventListener("click", clickEvent);
			}
			return mk;
		}
		function removeOverlay(overlay) {
			map.removeOverlay(overlay);//移除指定标
		}
		//清除所有添加
		function clearMap() {
			map.clearOverlays();//移除全部
		}
		//重新定位到点
		function resetLocation(point) {
			//new BMap.Point(113.262232, 23.154345);
			if(point==null){
				point=localPoint;
			}
			map.panTo(point); //定位到点
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
				addOverlay(point);
			}
		}
		
		function getPosition(mark) {
			var p = mark.getPosition(); //获取marker的位置
			alert("marker的位置是" + p.lng + "," + p.lat);
			return p;
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
			removeOverlay(allOverlay[0]);
		}	
		//显示弹出窗口
		function bindWin(marker){
			var opts = {
					  width : 200,     // 信息窗口宽度
					  height: 100,     // 信息窗口高度
					  title : "海底捞王府井店" , // 信息窗口标题
					  enableMessage:true,//设置允许信息窗发送短息
					  message:"亲耐滴，晚上一起吃个饭吧？戳下面的链接看下地址喔~"
				};
			var infoWindow = new BMap.InfoWindow("地址：北京市东城区王府井大街88号乐天银泰百货八层", opts);  // 创建信息窗口对象 
			marker.addEventListener("click", function(){          
			map.openInfoWindow(infoWindow,localPoint); //开启信息窗口
			});
		}
		function showWin(point){
			var opts = {
					  width : 200,     // 信息窗口宽度
					  height: 100,     // 信息窗口高度
					  title : "设置半径" , // 信息窗口标题
					  //enableMessage:true,//设置允许信息窗发送短息
					  message:"亲耐滴，晚上一起吃个饭吧？戳下面的链接看下地址喔~"
				};
			var infoWindow = new BMap.InfoWindow("请设置工作区域半径", opts);  // 创建信息窗口对象 
			map.openInfoWindow(infoWindow,point);
		}
		//标签右击事件
		function addMarkerMenu(mark){
			var removeMarker = function(e,ee,marker){
				map.removeOverlay(mark);
			};
			var distance=function(e,ee,marker){
				alert(getDistance(map.getCenter(),marker.getPosition()));
			};
			var center=function(e,ee,marker){
				setCenter(marker.getPosition());
			};
			//创建右键菜单
			var markerMenu=new BMap.ContextMenu();
			markerMenu.addItem(new BMap.MenuItem('删除',removeMarker.bind(mark)));
			markerMenu.addItem(new BMap.MenuItem('距离',distance.bind(mark)));
			markerMenu.addItem(new BMap.MenuItem('设为中心',center.bind(mark)));
			mark.addContextMenu(markerMenu);
		}
		//mark右击
		function menuClickDistance(mark){
			mark.addEventListener("rightclick",function(){
				//var dis=getDistance(map.getCenter(),mark.getPosition());
				//alert(dis);
				addMarkerMenu(mark);
			});
		}
		var infoWin;
		function showWin2(point){
			clickPoint=point;
			var sContent="<input id='search' type='text' value='请输入工作区域半径' onfocus="+"this.value=''"+"> <span id='searchBtn' onclick=setWorkSpace(clickPoint)>确定</span>";
			var infoWindow = new BMap.InfoWindow(sContent);
			infoWin=infoWindow;
			$("#searchBtn").click(function(){ alert("click");setWorkSpace(point);});      //     attr(onclick,setWorkSpace(setWorkSpace));
			//$("#searchBtn").live('click',function(){ alert("click");setWorkSpace(point);});      //     attr(onclick,setWorkSpace(setWorkSpace));
			console.info("set:"+$("#searchBtn"));
			map.openInfoWindow(infoWindow,point);
			console.info("setted");
		}
		//添加工作区域
		function setWorkSpace(location){
			var v=$("#search").attr("value");
			alert(v);
			if(v>0){
				
			}else{
				v=100;
			}
			map.closeInfoWindow(infoWin);
			var circle = new BMap.Circle(location,v,{strokeColor:"red", strokeWeight:2, strokeOpacity:10});
			map.addOverlay(circle);
		}
		//添加工作区域
		function addWorkSpace(location){
			showWin2(location);
		}
		//地图右击事件
		function addMapMenu(e){
			var p=new BMap.Point(e.point.lng,e.point.lat);
			var menu = new BMap.ContextMenu();
			var txtMenuItem = [
				{
					text:'放大',
					callback:function(){map.zoomIn();}
				},
				{
					text:'重新定位',
					callback:function(){reLocation();}
				},
				{
					text:'添加坐标',
					callback:function(){menuClickDistance(addOverlay(p));}
				},
				{
					text:'添加工作区域',
					callback:function(){addWorkSpace(p);}
				},
				{
					text:'缩小',
					callback:function(){map.zoomOut();}
				}
			];
			for(var i=0; i < txtMenuItem.length; i++){
				menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
			}
			map.addContextMenu(menu);
		}
		//////////////////////////////map end///////////////////////
		function toUpdate() {//跳到版本控制页面
			window.location = "<%=basePath%>web/fileUpload.jsp";
		}
	</script>
</body>
</html>
