<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="scripts/boot.js" type="text/javascript"></script>
	<script src="scripts/md5.js" type="text/javascript"></script>
  </head>
  
  <body>
	<div id="loginWindow" class="mini-window" title="用户登录"
		style="width:350px;height:165px;" showModal="true"
		showCloseButton="false">

		<div id="loginForm" style="padding:15px;padding-top:10px;">
			<table>
				<tr>
					<td style="width:60px;"><label for="username$text">帐号：</label></td>
					<td><input id="username" name="username"
						 class="mini-textbox"
						required="true" style="width:150px;" /></td>
				</tr>
				<tr>
					<td style="width:60px;"><label for="pwd$text">密码：</label></td>
					<td><input id="pwd" name="pwd" 
						class="mini-password" requiredErrorText="密码不能为空" required="true"
						style="width:150px;" onenter="onLoginClick" /> &nbsp;&nbsp;<a
						href="#">忘记密码?</a></td>
				</tr>
				<tr>
					<td></td>
					<td style="padding-top:5px;"><a onclick="onLoginClick"
						class="mini-button" style="width:60px;">登录</a> <a
						onclick="onResetClick" class="mini-button" style="width:60px;">重置</a>
					</td>
				</tr>
			</table>
		</div>

	</div>
	<script type="text/javascript">
	 mini.parse();

     var loginWindow = mini.get("loginWindow");
     loginWindow.show();

     function onResetClick(e) {
         var form = new mini.Form("#loginWindow");
         form.clear();
     }

     function onLoginClick(e) {
         var form = new mini.Form("#loginWindow");

         form.validate();
         if (form.isValid() == false) return;
		 	var username = mini.get("username").getValue();
			var pwd = mini.get("pwd").getValue();
			 $.ajax({
	                url: "http://192.168.1.117:8080/webDemo/base/userLogin.action?",
	                type: "get",
	                data: { "loginName":username , "loginPwd":pwd.MD5()},
	                success: function (text) {
	                	var data = mini.decode(text);
	                	if(data.loginStatus =="success"){
		                	 loginWindow.hide();
		                     mini.loading("登录成功，马上转到系统...", "登录成功");
		                     setTimeout(function () {
		                         window.location = "<%=basePath%>web/fileUpload.jsp";
		                     }, 1500);
	                	}else{
	                		mini.alert("用户名或密码错误！");
	                	}
	                }
	            });

        
     }

	</script>
  </body>
</html>
