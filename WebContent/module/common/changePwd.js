/**
 * @function 修改密码JS
 * 
 */
Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';
	var regPwd = /^[\d,\w]+$/;
	var argFrm = new Ext.FormPanel({
		frame : true,
		autoHeight : true,
		width : '100%',
		labelWidth : 60,
		bodyStyle : 'padding:5px 5px 0; text-align:left',
		renderTo : Ext.getBody(),
		buttonAlign : 'center',
		autoScroll : false,
		items : [{
					id : 'userName',
					name : 'userName',
					fieldLabel : '&nbsp;&nbsp;&nbsp;用户名',
					xtype : 'textfield',
					allowBlank : false,
					blankText : '请输入用户名',
					width : 150
				}, {
					id : 'oldPwd',
					name : 'oldPwd',
					fieldLabel : '原始密码',
					xtype : 'textfield',
					allowBlank : false,
					inputType : 'password',
					allowBlank : false,
					blankText : '请输入原始密码',
					maxLength : 50,
					maxLengthText : '密码允许输入的最大长度为50',
					regex : regPwd,
					regexText : '密码只能是数字和英文',
					width : 150
				}, {
					id : 'newPwd',
					name : 'newPwd',
					fieldLabel : '&nbsp;&nbsp;&nbsp;新密码',
					xtype : 'textfield',
					allowBlank : false,
					inputType : 'password',
					allowBlank : false,
					blankText : '请输入新密码',
					maxLength : 50,
					maxLengthText : '密码允许输入的最大长度为50',
					regex : regPwd,
					regexText : '密码只能是数字和英文',
					width : 150
				}, {
					id : 'confirmPwd',
					name : 'confirmPwd',
					fieldLabel : '确认密码',
					xtype : 'textfield',
					allowBlank : false,
					xtype : 'textfield',
					vtype : "password",// 使用自定义的确认密码检验
					vtypeText : '两次输入密码不一致',
					confirmTo : 'newPwd',
					inputType : 'password',
					allowBlank : false,
					blankText : '请再次输入密码',
					maxLength : 50,
					maxLengthText : '密码允许输入的最大长度为50',
					regex : regPwd,
					regexText : '密码只能是数字和英文',
					width : 150
				}],
		buttons : [{
					id : 'smtBtn',
					width : 60,
					text : '保 存',
					handler : function(e) {
						if(argFrm.form.isValid()){
							argFrm.form.submit({
										waitTitle : ' ',
										waitMsg : '正在修改密码，请等待......',
										url : '/deviceMonitor/user/changePwd.do',
										method : 'POST',
										failure : function(form, action) {
											var json = action.response.responseText;
											var o = eval("(" + json + ")");
											Ext.MessageBox.show({
														title : '提示',
														msg : o.message,
														width : 200,
														buttons : Ext.MessageBox.OK,
														icon : Ext.MessageBox.INFO
											});
										},
										success : function(form1, action) {
											var json = action.response.responseText;
											var o = eval("(" + json + ")");
											Ext.MessageBox.show({
														title : '提示',
														msg : o.message,
														width : 200,
														buttons : Ext.MessageBox.OK,
														icon : Ext.MessageBox.INFO
													});
											window.close();
										}
							});
 						}
					}
				}, {
					id : 'cancelBtn',
					width : 60,
					text : '取 消',
					handler : function(e) {
						window.close();
					}
				}]
	});
});