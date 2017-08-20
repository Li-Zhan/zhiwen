<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理页</title>
</head>
<body>

<table id="manager"></table>

<div id="manager_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add-new" plain="true" onclick="manager_tool.add();">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="manager_tool.edit();">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="manager_tool.remove();">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="save" onclick="manager_tool.reload();">刷新</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" plain="true" id="redo" onclick="manager_tool.redo();">取消选择</a>		
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
		根据账号查询：<input id="find" type="text" class="textbox" name="userName_find" style="width:110px">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="manager_tool.search();">查询</a>
	</div>
</div>



<form id="manager_add" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<p>用户帐号：<input type="text" name="userName" class="textbox" style="width:200px;"></p>
	<p>用户密码：<input type="password" name="password" class="textbox" style="width:200px;"></p>
	<p>性　　别：<input type="radio" name="sex" value="1" checked>男&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="0">女</p>
	<p>邮　　箱：<input class="textbox" name="email" style="width:200px;"></p>
	<p>创建时间：<input id="date" type="text" name="date" editable="false" style="width:200px;"></p>
</form>

<form id="manager_edit" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<input type="hidden" name="id">
	<!-- 在input中添加disabled="true"可以阻止修改，editable="false"表示不允许自行填写 -->
	<p>用户帐号：<input type="text" name="userName_edit"  class="textbox" style="width:200px;"></p>
	<p>用户密码：<input type="password" name="password_edit" class="textbox" style="width:200px;"></p>
	<p>性　　别：<input type="radio" name="sex_edit" value="1" checked>男&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex_edit" value="0">女</p>
	<p>邮　　箱：<input class="textbox" name="email_edit" style="width:200px;"></p>
	<p>创建时间：<input id="date_edit" type="text" name="date_edit" editable="false" style="width:200px;"></p>
</form>



<script type="text/javascript" src="js/manager.js"></script>
</body>
</html>