<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问题管理页</title>
</head>
<body>

<table id="question"></table>

<div id="question_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add-new" plain="true" onclick="question_tool.add();">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="question_tool.edit();">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="question_tool.remove();">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="save" onclick="question_tool.reload();">刷新</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" plain="true" id="redo" onclick="question_tool.redo();">取消选择</a>		
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
		根据用户查询：<input id="find" type="text" class="textbox" name="userName_find" style="width:110px"/>
		
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="question_tool.search();" >查询</a>
	</div>
</div>



<form id="question_add" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<p>问题标题：<input type="text" name="title" class="textbox" style="width:200px;"></p>
	<p>用户密码：<input type="password" name="content" class="textbox" style="width:200px;"></p>
	<p>创建时间：<input id="date" type="text" name="date" editable="false" style="width:200px;"></p>
	<p>用　　户：<input type="text" name="userName" class="textbox" style="width:200px;"></p>
</form>

<form id="question_edit" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<input type="hidden" name="qid">
	<!-- 在input中添加disabled="true"可以阻止修改，editable="false"表示不允许自行填写 -->
	<p>用户帐号：<input type="text" name="userName_edit"  class="textbox" style="width:200px;"></p>
	<p>用户密码：<input type="password" name="password_edit" class="textbox" style="width:200px;"></p>
	<p>性　　别：<input type="radio" name="sex_edit" value="1" checked>男&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex_edit" value="0">女</p>
	<p>邮　　箱：<input class="textbox" name="email_edit" style="width:200px;"></p>
	<p>创建时间：<input id="date_edit" type="text" name="date_edit" editable="false" style="width:200px;"></p>
</form>



<script type="text/javascript" src="js/question.js"></script>
</body>
</html>