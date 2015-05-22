<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'fileupload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="scripts/boot.js" type="text/javascript"></script>

    <script src="scripts/swfupload/swfupload.js" type="text/javascript"></script>


  </head>
  
  <body>
  <table align="center" style="margin-top: 20px;">
  
  	<tr>
  		<td>
  			<table>
  				<tr>
    			<td colspan="2" align="center"><h2>apk版本上传维护</h2></td>
    		</tr>
  				<tr>
    			<td style="width: 82px;">文件</td>
    			<td><input id="fileupload1"  class="mini-fileupload" name="uploadfile" limitType="*.apk" style="width: 300px;"
				    flashUrl="scripts/swfupload/swfupload.swf"
				    uploadUrl="<%=basePath%>inventory/saveApkSaveFile.action"
				    onuploadsuccess="onUploadSuccess" 
    				onuploaderror="onUploadError"
    				uploadOnSelect ="true"
				    />
    			</td>
    		</tr>
  			</table>
  		</td>
  	</tr>
  
  	<tr>
  		<td>
  			<form id="form1" style="margin-bottom: 0px;">
    	<table>
    		
    		<tr>
    			<td>应用名称：</td>
    			<td><input id="appname" name="appname" class="mini-textbox" emptyText="请输入应用名称" style="width: 300px;"/></td>
    		</tr>
    		<tr>
    			<td>版本名称：</td>
    			<td><input id="verName" name="verName" class="mini-textbox" emptyText="请输入版本名称" style="width: 300px;"/></td>
    		</tr>
    		<tr>
    			<td>版本号：</td>
    			<td><input id="verCode" name="verCode" class="mini-textbox" emptyText="请输入版本号" style="width: 300px;" required ="true"/></td>
    		</tr>
    		<tr>
    			<td>升级内容：</td>
    			<td><textarea id="verContent" name="verContent" class="mini-textarea" emptyText="请输入升级内容" style="width: 300px;"></textarea></td>
    		</tr>
    		<tr>
    			<td colspan="2" align="right"><a class="mini-button" iconCls="icon-upload" onclick="startUpload()" style="width: 80px; margin-top: 3px;">提交</a></td>
    		</tr>
    	</table>
    </form>
  		</td>
  	</tr>
  	
  </table>
    
    
    <script type="text/javascript">
	    mini.parse();
	    
	    var filename="";
	    
	    function startUpload() {
	    	var form = new mini.Form("#form1");
	    	form.validate();

	    	if(form.isValid()){
	    		onClick(filename);
	    	}else{
	    		mini.alert("验证未通过");
	    	}
	    }


	    function onFileSelect(e) {
	    }
	    function onUploadSuccess(e) {
	    	var data = mini.decode(e.serverData);
	    	mini.get("verName").setValue(data.apkInfo.versionName);
	    	mini.get("verCode").setValue(data.apkInfo.versionCode);
	    	mini.get("appname").setValue(data.apkName);
	    	filename = data.fileName;
	    }
	    function onUploadError(e) {
	        mini.alert("文件上传失败！");
	    }

	    function onClick(fileName){
	    	var form = new mini.Form("#form1");            
	    	var data = form.getData();      //获取表单多个控件的数据
	    	var json = mini.encode(data);   //序列化成JSON
	    	$.ajax({
	    	    url: "inventory/apkPakeageSave.action",
	    	    type: "post",
	    	    data: { "submitData": json, "fileName":fileName},
	    	    success: function (text) {
	    	    	var data = mini.decode(text);
	    	    	mini.alert(data.msg);  
	    	    }
	    	});
	    }
    
    </script>
  </body>
</html>
