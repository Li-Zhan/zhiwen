$(function() {
	
	//查询按钮
	$('#search_button').button({
		icons : {
			primary : ' ui-icon-search',
		},
	});

	//cookie登录
	$('#member, #logout').hide();

	if ($.cookie('user')) {
		$('#member, #logout').show();
		$('#reg_a, #login_a').hide();
		$('#member').html($.cookie('user'));
	} else {
		$('#member, #logout').hide();
		$('#reg_a, #login_a').show();
	}

	$('#logout').click(function() {
		$.removeCookie('user');
		window.location.href = '/zhiwen/index.html';
	});

	//加载中框体
	$('#loading').dialog({
		autoOpen : false,
		modal : true,
		closeOnEscape : false,
		resizable : false,
		draggable : false,
		width : 180,
		height : 50,
	}).parent().find('.ui-widget-header').hide();

	$('#reg_a').click(function() {
		$('#reg').dialog('open');
	});
	
	//注册时工具提示
	$('#reg input[title]').tooltip();

	//注册对话框
	$('#reg').dialog({
		width : 400,
		height : 400,
		resizable : false,
		autoOpen : false,
		buttons : {
			'注册' : function() {
				$(this).submit();

			},
			'取消' : function() {
				$(this).dialog("close");
			}
		},
		//show : 'puff',
		//hide : 'puff',
		modal : true,
		closeText : '关闭',
	}).buttonset().validate({
		submitHandler : function(form) {

			//form插件提交
			$('#reg').ajaxSubmit({
				url : 'userAction_addUser.action',
				type : 'post',
				beforeSend : function() {
					$('#reg').dialog('widget').find('button').eq(1).button('disable');
					$('#loading').dialog('open');
				},
				success : function(response, status) {
					if (response==='true') {
						$('#reg').dialog('widget').find('button').eq(1).button('enable');
						$('#loading').css('background', 'url(img/success.gif) no-repeat 20px center').html('注册成功...');
						$.cookie('user', $('#user').val());
						setTimeout(function() {
							$('#loading').dialog('close');
							$('#reg').dialog('close');
							$('#reg').resetForm();
							$('#reg span.star').html('*').removeClass('succ');
							$('#loading').css('background', 'url(img/loading.gif) no-repeat 20px center').html('努力加载中...');
							$('#member, #logout').show();
							$('#reg_a, #login_a').hide();
							$('#member').html($.cookie('user'));
						}, 1000);
					}
				},
			});

		},

		//在框体上部显示的validate错误信息
		showErrors : function(errorMap, errorList) {
			var errors = this.numberOfInvalids();

			if (errors > 0) {
				$('#reg').dialog('option', 'height', errors * 20 + 400);
			} else {
				$('#reg').dialog('option', 'height', 400);
			}

			this.defaultShowErrors();
		},

		//高亮(red)显示validate不通过位置
		highlight : function(element, errorClass) {
			$(element).css('border', '1px solid red');
			$(element).parent().find('span').html('*').removeClass('succ');
		},

		//通过后恢复
		unhighlight : function(element, errorClass) {
			$(element).css('border', '1px solid #ccc');
			$(element).parent().find('span').html('&nbsp;').addClass('succ');
		},
		
		//错误显示的位置
		errorLabelContainer : 'ol.reg_err',
		wrapper : 'li',
		//validate验证
		rules : {
			userName : {
				required : true,
				minlength : 2,
				remote : { //远程验证
					url : 'userAction_isUser.action',
					type : 'post',
				},
			},
			password : {
				required : true,
				minlength : 6,
			},
			notpass : {
				required : true,
				equalTo : '#password',
			},
			email : {
				required : true,
				email : true,
			},
			date : {
				date : true,
			},
		},
		messages : {
			userName : {
				required : '账号不得为空',
				minlength : '账号最小为{0}位',
				remote : '账号已被占用',
			},
			password : {
				required : '密码不得为空',
				minlength : '密码最小为{0}位',
			},
			notpass : {
				required : '确认密码不得为空',
				equalTo : '两次密码不一致',
			},
			email : {
				required : '邮箱不得为空',
			},
		},
	});


	//日历控件
	$('#date').datepicker({
		dateFormat : 'yy-mm-dd',
		changeYear : true,
		changeMonth : true,
		maxDate : 0,
		hideIfNoPrevNext : true,
		yearRange : '1950:2020',
	});

	//邮箱自动提醒
	$('#email').autocomplete({
		delay : 0,
		autoFocus : true,
		source : function(request, response) {

			//绑定数据源的		
			var hosts = [ 'qq.com', '163.com', '263.com', 'sina.com.cn', 'gmail.com', 'hotmail.com' ],
				term = request.term, //获取用户输入的内容
				name = term, //邮箱的用户名
				host = '', //邮箱的域名
				ix = term.indexOf('@'), //@的位置
				result = []; //最终呈现的邮箱列表


			result.push(term);

			//当有@的时候，重新分别用户名和域名
			if (ix > -1) {
				name = term.slice(0, ix);
				host = term.slice(ix + 1);
			}

			if (name) {
				//如果用户已经输入@和后面的域名，
				//那么就找到相关的域名提示，比如bnbbs@1，就提示bnbbs@163.com
				//如果用户还没有输入@或后面的域名，
				//那么就把所有的域名都提示出来

				var findedHosts = (host ? $.grep(hosts, function(value, index) {
						return value.indexOf(host) > -1
					}) : hosts),
					findedResult = $.map(findedHosts, function(value, index) {
						return name + '@' + value;
					});

				result = result.concat(findedResult);
			}

			response(result);
		},
	});



	$('#login_a').click(function() {
		$('#login').dialog('open');
	});

	//登录对话框
	$('#login').dialog({
		width : 330,
		height : 270,
		resizable : false,
		autoOpen : false,
		buttons : {
			'登录' : function() {
				$(this).submit();

			},
			'取消' : function() {
				$(this).dialog("close");
			}
		},
		//show : 'puff',
		//hide : 'puff',
		modal : true,
		closeText : '关闭',
	}).validate({
		submitHandler : function(form) {

			//form插件提交
			$('#login').ajaxSubmit({
				url : 'userAction_loginUser.action',
				type : 'post',
				beforeSend : function() {
					$('#login').dialog('widget').find('button').eq(1).button('disable');
					$('#loading').dialog('open');
				},
				success : function(response, status) {
					if (response==='true') {
						$('#login').dialog('widget').find('button').eq(1).button('enable');
						$('#loading').css('background', 'url(img/success.gif) no-repeat 20px center').html('登录成功...');
						if ($('#expires').is(':checked')) {
							$.cookie('user', $('#login_user').val(), {
								expires : 7,
							});
						} else {
							$.cookie('user', $('#login_user').val());
						}
						setTimeout(function() {
							$('#loading').dialog('close');
							$('#login').dialog('close');
							$('#login').resetForm();
							$('#login span.star').html('*').removeClass('succ');
							$('#loading').css('background', 'url(img/loading.gif) no-repeat 20px center').html('努力加载中...');
							$('#member, #logout').show();
							$('#reg_a, #login_a').hide();
							$('#member').html($.cookie('user'));
							window.location.href = '/zhiwen/index.html';
						}, 1000);
					} else {
						$('#loading').dialog('close');
						$('#login').dialog('widget').find('button').eq(1).button('enable');
						$('ol.login_err').html('账号或密码不正确').show();
						$('#login').dialog('option', 'height', 35 + 270);
					}
				},
			});

		},

		showErrors : function(errorMap, errorList) {
			var errors = this.numberOfInvalids();

			if (errors > 0) {
				$('#login').dialog('option', 'height', errors * 35 + 270);
			} else {
				$('#login').dialog('option', 'height', 270);
			}

			this.defaultShowErrors();
		},

		highlight : function(element, errorClass) {
			$(element).css('border', '1px solid red');
			$(element).parent().find('span').html('*').removeClass('succ');
		},

		unhighlight : function(element, errorClass) {
			$(element).css('border', '1px solid #ccc');
			$(element).parent().find('span').html('&nbsp;').addClass('succ');
		},
		errorLabelContainer : 'ol.login_err',
		wrapper : 'li',
		rules : {
			userName : {
				required : true,
				minlength : 2,
			},
			password : {
				required : true,
				minlength : 6,
			},
		},
		messages : {
			userName : {
				required : '账号不得为空',
				minlength : '账号最小为{0}位',
			},
			password : {
				required : '密码不得为空',
				minlength : '密码最小为{0}位',
			},
		},
	});

	//选项卡
	$('#tabs').tabs({
		//event:'mouseover',
		//heightStyle : 'auto',
		show : true,
		hide : true,
	});

	//下拉折叠菜单
	$('#accordion').accordion({
		event : 'mouseover',
		icons : {
			"header" : "ui-icon-plus",
			"activeHeader" : "ui-icon-minus",
		},
	});

	//提问按钮
	$('#edit_button').button({
		icons : {
			primary : 'ui-icon-pencil',
		},
	}).click(function(){
		if($.cookie('user')) {
			//如果处于登录状态,可以提问
			$('#question').dialog('open');
		} else {
			$('#error').dialog('open');
			setTimeout(function
			() {
				$('#error').dialog('close');
				$('#login').dialog('open');
			},
				1000);
		}
	});
	
	
	//提问对话框
	$('#question').dialog({
		autoOpen : false,
		modal : true,
		resizable : false,
		width : 600,
		height : 450,
		buttons : {
			'发布' : function () {
				$(this).submit();
			},
			'取消' : function() {
				$(this).dialog("close");
			},
		}
	}).validate({
		submitHandler: function(){
			$('#question').ajaxSubmit({
				url : 'questionAction_addQuestion.action',
				type : 'post',
				resetForm:true,
				data : {
					userName : $.cookie('user'),
				},
				beforeSubmit : function (formData, jqForm, options) {
					$('#loading').dialog('open');
					$('#question').dialog('widget').find('button').eq(1).button('disable');
				},
				success : function (response, status) {
					if (response==='true') {       //这样验证返回值是不是true,可以减少错误
						$('#question').dialog('widget').find('button').eq(1).button('enable');
						$('#loading').css('background', 'url(img/success.gif) no-repeat 20px center').html('问题发布成功...');
						setTimeout(function () {
							$('#loading').dialog('close');
							$('#question').dialog('close');
							$('#question').resetForm();
							$('#myEditor').html('请填写问题描述！(1000字以内)');
							$('#loading').css('background', 'url(img/loading.gif) no-repeat 20px center').html('努力加载中...');
						}, 1000);
					} else {
						$('#loading').dialog('close');
						$('#question').dialog('widget').find('button').eq(1).button('enable');
						$('ol.login_err').html('问题发布失败').show();
						$('#question').dialog('option', 'height', 35 + 450);
					}
				},
			});
		},
		
		showErrors : function(errorMap, errorList) {
			var errors = this.numberOfInvalids();

			if (errors > 0) {
				$('#question').dialog('option', 'height', errors * 35 + 450);
			} else {
				$('#question').dialog('option', 'height', 450);
			}

			this.defaultShowErrors();
		},
		
		highlight : function(element, errorClass) {
			$(element).css('border', '1px solid red');
		},

		unhighlight : function(element, errorClass) {
			$(element).css('border', '1px solid #ccc');
		},
		errorLabelContainer : 'ol.login_err',
		wrapper : 'li',
		rules : {
			title : {
				required : true,
			},
		},
		messages : {
			title : {
				required : '标题不得为空',
			},
		},
	});

	
	
	//未登录提示框
	$('#error').dialog({
		autoOpen : false,
		modal : true,
		closeOnEscape : false,
		resizable : false,
		draggable : false,
		width : 180,
		height : 50,
	}).parent().find('.ui-widget-header').hide();
	
	//应该在登录之后操作(加载问题)
	$.ajax({
		url:'questionAction_findAllByUserName',
		type:'post',
		data: {
			userName: $.cookie('user'),
		},
		success:function(response,status,xhr){

			var json=response;
			
			var arr=[];   
			
			var summary = [];  //保存截取的内容
			
			var html='';
			$.each(json,function(index,value) {
				var myDate =new Date(value.date);
				html+='<h4>' + $.cookie('user') + ' 发表于 ' + myDate.getFullYear()+'-'+(myDate.getMonth() + 1)+'-'+myDate.getDate()+' '+myDate.getHours()+':'+myDate.getMinutes()+':'+myDate.getSeconds()+ '</h4><h3>' + value.title +
				'</h3><div class="editor">' + value.content+ '</div><div class="bottom"><span class="comment" data-id="' + value.qid + '">' + value.count + '条评论 </span><span class="up">收起</span></div><hr noshade="noshade" size="1" /><div class="comment_list"></div>';
			});

			$('.content').append(html);
			
			$.each($('.editor'), function (index, value) {
				arr[index] = $(value).html();
				summary[index] = $(arr[index]).text().substr(0, 200);
				
				if (summary[index].substring(199,200) == '<') {
					summary[index] = replacePos(summary[index], 200, '');
				}
				if (summary[index].substring(198,200) == '</') {
					summary[index] = replacePos(summary[index], 200, '');
					summary[index] = replacePos(summary[index], 199, '');
				}
				
				if (arr[index].length > 200) {
					summary[index] += '...<br/><span class="down">显示全部</span>';
					$(value).html(summary[index]);
				}
				$('.bottom .up').hide();
			});
			
			
			$.each($('.editor'), function (index, value) {
				$(this).on('click', '.down', function () {
					$('.editor').eq(index).html($(arr[index]).text());
					$(this).hide();
					$('.bottom .up').eq(index).show();
				});
			});
			
			$.each($('.bottom'), function (index, value) {
				$(this).on('click', '.up', function () {
					$('.editor').eq(index).html(summary[index]);
					$(this).hide();
					$('.editor .down').eq(index).show();
				});
			});
			
			$.each($('.bottom'), function (index, value) {
				$(this).on('click', '.comment', function () {
					var currPage = 1;
					var comment_this = this;
					if ($.cookie('user')) {
						if (!$('.comment_list').eq(index).has('form').length) {
							$.ajax({
								url : 'commentAction_showComment',
								type : 'POST',
								data:{
									titleid: $(comment_this).attr('data-id'),         //根据titleid查询评论
									currPage : currPage,
								},
								beforeSend : function (jqXHR, settings) {
									$('.comment_list').eq(index).append('<dl class="comment_load"><dd>正在加载评论</dd></dl>');
								},
								success : function (response, status) {
									$('.comment_list').eq(index).find('.comment_load').hide();
									var json_comment = response;
									var count = json_comment.totalPage;
									$.each(json_comment.list, function (index2, value) {
										//count = value.totalCount;
										$('.comment_list').eq(index).append('<dl class="comment_content"><dt>' + value.user + '</dt><dd>' + value.comment + '</dd><dd class="date">' + value.date + '</dd></dl>');
									});
									$('.comment_list').eq(index).append('<dl><dd><span class="load_more">加载更多评论</span></dd></dl>');
									//var currPage = 1;
									if (currPage >= count) {
										$('.comment_list').eq(index).find('.load_more').off('click');
										$('.comment_list').eq(index).find('.load_more').hide();
									}
									$('.comment_list').eq(index).find('.load_more').button().on('click', function () {
										$('.comment_list').eq(index).find('.load_more').button('disable');
										$.ajax({
											url : 'commentAction_showComment',
											type : 'POST',
											data : {
												titleid : $(comment_this).attr('data-id'),
												currPage : (currPage+1),
											},
											beforeSend : function (jqXHR, settings) {
												$('.comment_list').eq(index).find('.load_more').html('<img src="img/more_load.gif" />');
											},
											success : function (response, status) {
												var json_comment_more = response;
												$.each(json_comment_more.list, function (index3, value) {
													$('.comment_list').eq(index).find('.comment_content').last().after('<dl class="comment_content"><dt>' + value.user + '</dt><dd>' + value.comment + '</dd><dd class="date">' + value.date + '</dd></dl>');
												});
												$('.comment_list').eq(index).find('.load_more').button('enable');
												$('.comment_list').eq(index).find('.load_more').html('加载更多评论');
												currPage++;
												if (currPage >= count) {
													$('.comment_list').eq(index).find('.load_more').off('click');
													$('.comment_list').eq(index).find('.load_more').hide();
												}
											}
										});
									});
									$('.comment_list').eq(index).append('<form><dl class="comment_add"><dt><textarea name="comment"></textarea></dt><dd><input type="hidden" name="titleid" value="' + $(comment_this).attr('data-id') + '" /><input type="hidden" name="user" value="' + $.cookie('user') + '" /><input type="button" value="发表" /></dd></dl></form>');
									$('.comment_list').eq(index).find('input[type=button]').button().click(function () {
										var _this = this;
										$('.comment_list').eq(index).find('form').ajaxSubmit({
											url : 'commentAction_addComment',
											type : 'POST',
											beforeSubmit : function (formData, jqForm, options) {
												$('#loading').dialog('open');
												$(_this).button('disable');
											},
											success : function (response, status) {
												if (response==='true') {
													$(_this).button('enable');
													$('#loading').css('background', 'url(img/success.gif) no-repeat 20px center').html('评论成功...');
													setTimeout(function () {
														var date = new Date();
														$('#loading').dialog('close');
														$('.comment_list').eq(index).prepend('<dl class="comment_content"><dt>' + $.cookie('user')+ '</dt><dd>' + $('.comment_list').eq(index).find('textarea').val() + '</dd><dd>' +date.getFullYear() + '-' + (date.getMonth()+ 1) + '-' + date.getDate() + ' ' + date.getHours() + ':' +date.getMinutes() + ':' + date.getSeconds() + '</dd></dl>');
														$('.comment_list').eq(index).find('form').resetForm();
														$('#loading').css('background', 'url(img/loading.gif) no-repeat 20px center').html('努力加载中...');
													}, 1000);
												}
											},
										});
									});
								},
							});
						}
						if ($('.comment_list').eq(index).is(':hidden')) {
							$('.comment_list').eq(index).show();
							
						} else {
							$('.comment_list').eq(index).hide();
						}
					} else {
						$('#error').dialog('open');
						setTimeout(function () {
							$('#error').dialog('close');
							$('#login').dialog('open');
						}, 1000);
					}
				});
			});
				
		},
	});

});



function replacePos(strObj, pos, replaceText) {
	return strObj.substr(0, pos-1) + replaceText + strObj.substring(pos, strObj.length);
}
