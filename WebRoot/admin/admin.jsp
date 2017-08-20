<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<title>后台管理</title>
<meta charset="UTF-8" />
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js" ></script>
<script type="text/javascript" src="js/admin.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/admin.css" />
<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
</head>
<body class="easyui-layout">

<div data-options="region:'north',title:'header',noheader:true" style="height:60px;background:#666;">
	<div class="logo">后台管理</div>
	<div class="logout">您好，${adminName } | <a href="adminAction_exitLogin">安全退出</a></div>
</div>  
  
<div data-options="region:'south',title:'footer',noheader:true" style="height:50px;line-height:20px;text-align:center;background:#D2E0F2;">
       <div style="margin:3px;">
       <span>李展制作   仅供学习</span></br>
       <span style="font-family:Arial;">Copyright © 2017 - 2100 All Rights Reserved</span>
       </div>
</div>
    
<!-- 以tree的形式,只有menu一个文件 
<div data-options="region:'west',title:'导航',split:true,iconCls:'icon-world'" style="width:180px;padding:10px;">

   <div id="nav"></div>       

</div> 
--> 

<!-- 以Accordion 折叠面板的方式,但是有mune1和menu2两个文件 -->
<div data-options="region:'west',title:'导航',split:true,iconCls:'icon-classification'" style="width:180px;">

<div id="aa" >        
    <div title="人员管理" data-options="iconCls:'icon-user'," style="overflow:auto;padding:10px;">
		<div id="nav1"></div>
    </div>
    <div title="内容管理" data-options="iconCls:'icon-content'," style="padding:10px;">
		<div id="nav2"></div>
    </div>
</div>

</div>
  
<div data-options="region:'center'" style="overflow:hidden;">
	<div id="tabs">
		<div title="起始页" iconCls="icon-house" style="padding:0 10px;display:block;">
			欢迎来到后台管理系统！
		</div>
	</div>
</div> 

</body>
</html>
