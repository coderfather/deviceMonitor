Ext.onReady(function() {
	Ext.QuickTips.init();
	
	//创建可重用的renderer
	Ext.util.Format.comboRenderer = function(combo){
	    return function(value){
	        var record = combo.findRecord(combo.valueField, value);
	        return record ? record.get(combo.displayField) : combo.valueNotFoundText;
	    }
	}
	
	var ds_enabled = new Ext.data.SimpleStore({
		fields : ['value','text'],
		data : [
			['1','启用'],
			['0', '禁用']
		]
	});
	
	var cboEnabled = new Ext.form.ComboBox({
		id : 'cboEnabled',
		store:	ds_enabled,
		fieldLabel : '状态',
		emptyText : '请选择',
		hiddenName:'enabled', //提交到后台
		displayField:'text', 
		typeAhead : true,
		triggerAction : 'all',
		readOnly : true,
		valueField:'value',
		mode:'local',
		width: 147,
		itemCls : 'begin',
		clearCls : 'allow-float',
		allowBlank: false,
		blankText: '请选择状态'
	});
	
	var ds_sex = new Ext.data.SimpleStore({
		fields : ['value','text'],
		data : [
			['1', '男'],
			['0', '女']
		]
	});
	
	var cboSex = new Ext.form.ComboBox({
		id : 'cboSex',
		store:	ds_sex,
		fieldLabel : '性别',
		emptyText : '请选择',
		hiddenName:'sex', //提交到后台
		displayField:'text', 
		typeAhead : true,
		triggerAction : 'all',
		readOnly : true,
		valueField:'value',
		mode:'local',
		width: 147,
		itemCls : 'begin',
		clearCls : 'allow-float',
		allowBlank: false,
		blankText: '请选择性别'
	});
	
	var _form = new Ext.form.FormPanel({
				frame : true,
				autoHeight : true,
				bodyStyle : 'padding:5px 5px 1; text-align:left;',
				labelAlign : 'right',
				labelWidth : 200,
				items : [
					{
						fieldLabel : 'id',
						id : 'id',
						name : 'id',
						xtype:'hidden'
					},
					{
						fieldLabel : '角色',
						id : 'role_id',
						name : 'role_id',
						xtype:'hidden'
					},
					{
						fieldLabel : 'IMEI',
						id : 'imei',
						name : 'imei',
						xtype:'textfield'
					},
					{
						fieldLabel : '登录名',
						id : 'login_name',
						name : 'login_name',
						xtype:'textfield'
					},
					{
						fieldLabel : '密码',
						id : 'pwd',
						name : 'pwd',
						xtype:'textfield',
						inputType:'password'
					},
					{
						fieldLabel : '姓名',
						id : 'user_name',
						name : 'user_name',
						xtype:'textfield'
					},
					cboSex,
					{
						fieldLabel : '电话',
						id : 'phone',
						name : 'phone',
						xtype:'textfield'
					},
					{
						fieldLabel : '手机型号',
						id : 'phone_type',
						name : 'phone_type',
						xtype:'textfield'
					},
					{
						fieldLabel : '邮箱',
						id : 'email',
						name : 'email',
						xtype:'textfield'
					},
					{
						fieldLabel : '公司',
						id : 'company',
						name : 'company',
						xtype:'textfield'
					},
					{
						fieldLabel : '地址',
						id : 'address',
						name : 'address',
						xtype:'textfield'
					},
					cboEnabled,
					{
						fieldLabel : '备注',
						id : 'remark',
						name : 'remark',
						xtype:'textfield'
					},
					{
						fieldLabel : 'parent_id',
						id : 'parent_id',
						name : 'parent_id',
						xtype:'hidden'
					},
					{
						fieldLabel : 'limit_ver',
						id : 'limit_ver',
						name : 'limit_ver',
						xtype:'hidden'
					}
				],
				reader : new Ext.data.JsonReader({
							root : 'list',
							successProperty : 'success',
							totalProperty : 'totalSize',
							id : 'id'
						}, [
						'id',
						'role_id',
						'imei',
						'login_name',
						'pwd',
						'user_name',
						'sex',
						'phone',
						'phone_type',
						'email',
						'company',
						'address',
						'enabled',
						'remark',
						'parent_id',
						'limit_ver'
				])
			});

	var _window = new Ext.Window({
				title : '用户管理',
				width : 520,
				autoHeight : true,
				resizable : false,
				plain : false,
				border : false,
				modal : true,
				autoScroll : true,
				layout : 'fit',
				closeAction : 'hide',
				items : _form,
				buttonAlign : "center",
				buttons : [{
							text : '保存',
							handler : func_form_save
						}, {
							text : '取消',
							handler : function() {
								_window.hide();
							}
						}]
			});

	var deleteFormDiv = Ext.DomHelper.insertHtml('afterEnd', document.body,
			'<div id="' + Ext.id() + '" style="visibility: hidden;"> </div>');

	var deleteForm = new Ext.form.FormPanel({
				renderTo : deleteFormDiv,
				items : {}
			});

	var recordType = Ext.data.Record.create([
				{
					name : 'id',
					type : "int"
				},
				{
					name : 'role_id',
					type : "int"
				},
				{
					name : 'imei',
					type : "string"
				},
				{
					name : 'login_name',
					type : "string"
				},
				{
					name : 'pwd',
					type : "string"
				},
				{
					name : 'user_name',
					type : "string"
				},
				{
					name : 'sex',
					type : "string"
				},
				{
					name : 'phone',
					type : "string"
				},
				{
					name : 'phone_type',
					type : "string"
				},
				{
					name : 'email',
					type : "string"
				},
				{
					name : 'company',
					type : "string"
				},
				{
					name : 'address',
					type : "string"
				},
				{
					name : 'enabled',
					type : "string"
				},
				{
					name : 'remark',
					type : "string"
				},
				{
					name : 'parent_id',
					type : "int"
				},
				{
					name : 'limit_ver',
					type : "int"
				}
			]);

	var store = new Ext.data.Store({
				baseParams : {
					action : 'list'
				},
				proxy : new Ext.data.HttpProxy({
							url : '/deviceMonitor/user/list.do',
							method : "POST"  
						}),
				reader : new Ext.data.JsonReader({
							root : 'list',
							totalProperty : 'totalSize',
							id : 'id'
						}, recordType)
			});

	var rownum = new Ext.grid.RowNumberer();

	var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'id'
			});

	var columnModel = new Ext.grid.ColumnModel([rownum, sm, 
				{
					header : 'IMEI',
					width : 130,
					sortable : false,
					dataIndex : 'imei'
				},
				{
					header : '登录名',
					width : 80,
					sortable : false,
					dataIndex : 'login_name'
				},
				{
					header : '密码',
					width : 45,
					sortable : false,
					dataIndex : 'pwd',
					renderer : function(value, p, r) {return '******'}
				},
				{
					header : '姓名',
					width : 80,
					sortable : false,
					dataIndex : 'user_name'
				},
				{
					header : '性别',
					width : 45,
					sortable : false,
					dataIndex : 'sex',
					renderer: Ext.util.Format.comboRenderer(cboSex)
				},
				{
					header : '电话',
					width : 110,
					sortable : false,
					dataIndex : 'phone'
				},
				{
					header : '手机型号',
					width : 60,
					sortable : false,
					dataIndex : 'phone_type'
				},
				{
					header : '邮箱',
					width : 110,
					sortable : false,
					dataIndex : 'email'
				},
				{
					header : '公司',
					width : 110,
					sortable : false,
					dataIndex : 'company'
				},
				{
					header : '地址',
					width : 110,
					sortable : false,
					dataIndex : 'address'
				},
				{
					header : '状态',
					width : 60,
					sortable : false,
					dataIndex : 'enabled',
					renderer: Ext.util.Format.comboRenderer(cboEnabled)
				},
				{
					header : '备注',
					width : 110,
					sortable : false,
					dataIndex : 'remark'
				}
			]);

	function func_form_save() {
		if (!_form.getForm().isValid())
			return;
			
		var id = _form.form.findField('id').getValue();
		var url = "/deviceMonitor/user/insert.do";
		var params = {};
		params["action"] = "insert";
		if (id) {
			params["action"] = "update";
			url = "/deviceMonitor/user/update.do";
		}

		var key = _form.form.findField('login_name').getValue();
		Ext.Ajax.request({
		url : "/deviceMonitor/user/isExists.do",
		method : 'POST',
		async : false, // 默认为true异步提交，false为同步提交
		params : {
			'key': key,
			'id' : id
		},
		success : function(response, options) {
			responseObject = eval("(" + response.responseText + ")");
			if (responseObject.result == true) {
				Ext.MessageBox.show({
							title : '错误信息',
							msg : '已存在相同数据！',
							buttons : Ext.MessageBox.OK,
							icon : Ext.MessageBox.ERROR,
							width : 300
						});
			} else {
				submit_form_to_save(url, params);
			}	
		}}	);
	}

	function submit_form_to_save(url, params) {
		_form.form.submit({
					waitTitle : ' ',
					waitMsg : '正在保存，请稍等......',
					url : url,
					params : params,
					method : 'POST',
					failure : function(form, action) {
						var json = action.response.responseText;
						var o = eval("(" + json + ")");
						Ext.MessageBox.show({
									title : '错误信息',
									msg : o.message,
									buttons : Ext.MessageBox.OK,
									icon : Ext.MessageBox.ERROR,
									width : 240
								});
					},
					success : function(form1, action) {
						_window.hide();
						_grid.store.reload();
						_grid.getView().refresh();
					}
				});
	}

	function func_grid_add() {
		if (_window) {
			_form.getForm().reset();
			_window.show();
		}
	}

	function func_grid_update() {
		if (_grid.selModel.hasSelection()) {
			var records = _grid.selModel.getSelections();
			var recordsLen = records.length;
			if (recordsLen > 1) {
				Ext.MessageBox.show({
							title : '修改信息',
							msg : '不能选择多行记录！',
							autoWidth : true,
							buttons : Ext.MessageBox.OK,
							icon : Ext.MessageBox.ERROR,
							width : 240
						});
			} else {
				var record = _grid.getSelectionModel().getSelected();
				_window.show();
				_form.form.loadRecord(record);
			}
		} else {
			Ext.MessageBox.show({
						title : '修改信息',
						msg : '首先，请选择一条记录！',
						autoWidth : true,
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR,
						width : 240
					});
		}
	}

	function func_grid_delete() {
		if (_grid.selModel.hasSelection() == false) {
			Ext.MessageBox.show({
						title : '删除信息',
						msg : '首先，请选择一条记录！',
						autoWidth : true,
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR,
						width : 240
					});
			return;
		}

		var records = _grid.selModel.getSelections();
		if (records) {
			Ext.MessageBox.confirm('确定删除', '您确定要删除记录吗？', function(btn) {
						if (btn == 'yes') {
							var ids = "";
							for (var i = 0; i < records.length; i++) {
								var record = records[i];
								if (record.data.id) {
									ids += record.data.id + ",";
								} else {
									_grid.store.remove(record);
									_grid.getView().refresh();
								}
							}
							if (ids != "")
								ids = ids.substring(0, ids.length - 1);
							var params = {};
							params["action"] = "delete";
							deleteForm.getForm().submit({
										waitTitle : ' ',
										waitMsg : '正在删除，请稍等......',
										url : '/deviceMonitor/user/delete.do?delIds='
												+ ids,
										params : params,
										failure : function(form, action) {
											var json = action.response.responseText;
											var o = eval("(" + json + ")");
											Ext.MessageBox.show({
														title : '错误信息',
														msg : o.message,
														autoWidth : true,
														buttons : Ext.MessageBox.OK,
														icon : Ext.MessageBox.ERROR,
														width : 240
													});
										},
										success : function(form, action) {
											_grid.store.reload();
											_grid.getView().refresh();
										}
									});
						}
					});
		
		}
	}

	var displayMsg = '显示第[<b><font color=red>{0}</font></b>]条到[<b><font color=red>{1}</font></b>]条记录 / 总共[<b><font color=red>{2}</font></b>]记录';

	var grid_bbar = new Ext.PagingToolbar({
				store : store,
				displayMsg : displayMsg,
				emptyMsg : '没有记录',
				pageSize : 100,
				displayInfo : true
			});

	var contextMenu = new Ext.menu.Menu({
				items : [{
							text : '添加',
							iconCls : 'icon_add',
							handler : func_grid_add,
							functionID:'FUN_EDIT'
						}, {
							text : '修改',
							iconCls : 'icon_update',
							handler : func_grid_update,
							functionID:'FUN_EDIT'
						}, {
							text : '删除',
							iconCls : 'icon_delete',
							handler : func_grid_delete,
							functionID:'FUN_EDIT'
						}]
			});

	var _grid = new Ext.grid.GridPanel({
				frame : true,
				id : 'grid',
				iconCls : 'icon-pub1',
				ds : store,
				sm : sm,
				cm : columnModel,
				autoSizeColumns : true,
				selModel : new Ext.grid.RowSelectionModel(),
				loadMask : true,
				tbar : [{
							text : '添加',
							iconCls : 'icon_add',
							width : '24px',
							handler : func_grid_add,
							functionID:'FUN_EDIT'
						}, '-', {
							text : '修改',
							iconCls : 'icon_update',
							width : '24px',
							handler : func_grid_update,
							functionID:'FUN_EDIT'
						}, '-', {
							text : '删除',
							iconCls : 'icon_delete',
							width : '24px',
							handler : func_grid_delete,
							functionID:'FUN_EDIT'
						}],
				listeners : {
					'contextmenu' : function(e) {
						e.stopEvent();
					},
					'rowcontextmenu' : function(_grid, rowIndex, e) {
						_grid.selModel.selectRow(rowIndex);
						e.stopEvent();
						contextMenu.showAt(e.getXY());
					}
				},
				bbar : grid_bbar
			});

	var layout = new Ext.Viewport({
				layout : 'border',
				hideBorder : false,
				forceLayout : true,
				items : [{
							layout : 'fit',
							region : 'center',
							border : false,
							bodyStyle : 'padding:0 0 0 0',
							items : [_grid]
						}]
			});

	function layout_resize() {
		Ext.getCmp("grid").setWidth(Ext.getBody().getWidth());
		Ext.getCmp("grid").setHeight(Ext.getBody().getHeight());

	}

	layout.on({
				'resize' : function() {
					layout_resize.defer(300);
				},
				scope : this
			});

	store.load({
				params : {
					start : 0,
					limit : 20,
					action : "list"
				}
			});

	layout_resize();
});