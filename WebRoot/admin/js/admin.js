$(function () {


	//左侧菜单选项
	$('#aa').accordion({
		fit:true,
		border:false,
		selected:false,
	});
	
	//以tree的形式加载菜单项(json文件中定义)
	$('#nav1').tree({
		url:'menu1.json',
		lines:true,
		selected:false,
		onClick : function (node) {
			
			$('#nav1').tree({
				selected:false,
			});
			
			if (node.url) {
				if ($('#tabs').tabs('exists', node.text)) {
					$('#tabs').tabs('select', node.text);
				} else {
					$('#tabs').tabs('add', {
						title : node.text,
						iconCls : node.iconCls,
						closable : true,
						href : node.url,
					});
				}
			}
		}
	});
	
	
	$('#nav2').tree({
		url:'menu2.json',
		lines:true,
		selected:false,
		onClick : function (node) {
			
			$('#nav2').tree({
				selected:false,
			});
			
			if (node.url) {
				if ($('#tabs').tabs('exists', node.text)) {
					$('#tabs').tabs('select', node.text);
				} else {
					$('#tabs').tabs('add', {
						title : node.text,
						iconCls : node.iconCls,
						closable : true,
						href : node.url,
					});
				}
			}
		}
	});
	
	$('#tabs').tabs({
		fit : true,
		border : false,
	});
	
});