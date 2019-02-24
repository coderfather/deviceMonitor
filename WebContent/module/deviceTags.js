Ext.onReady(function() {
	Ext.QuickTips.init();
	
	//创建可重用的renderer
	Ext.util.Format.comboRenderer = function(combo){
	    return function(value){
	        var record = combo.findRecord(combo.valueField, value);
	        return record ? record.get(combo.displayField) : combo.valueNotFoundText;
	    }
	}
			
	var _form = new Ext.form.FormPanel({
				frame : true,
				autoHeight : true,
				bodyStyle : 'padding:5px 5px 1; text-align:left;',
				labelAlign : 'right',
				labelWidth : 200,
				items : [
					{
						fieldLabel : 'register_number',
						id : 'register_number',
						name : 'register_number',
						xtype:'textfield'
					},
					{
						fieldLabel : 'device_code',
						id : 'device_code',
						name : 'device_code',
						xtype:'textfield'
					},
					{
						fieldLabel : 'resolution_ver',
						id : 'resolution_ver',
						name : 'resolution_ver',
						xtype:'textfield'
					},
					{
						fieldLabel : 'tag_name',
						id : 'tag_name',
						name : 'tag_name',
						xtype:'textfield'
					},
					{
						fieldLabel : 'data_type',
						id : 'data_type',
						name : 'data_type',
						xtype:'textfield'
					},
					{
						fieldLabel : 'rw_flag',
						id : 'rw_flag',
						name : 'rw_flag',
						xtype:'textfield'
					},
					{
						fieldLabel : 'sNop',
						id : 'sNop',
						name : 'sNop',
						xtype:'textfield'
					}
				],
				reader : new Ext.data.JsonReader({
							root : 'list',
							successProperty : 'success',
							totalProperty : 'totalSize',
							id : 'id'
						}, [
						'register_number',
						'device_code',
						'resolution_ver',
						'tag_name',
						'data_type',
						'rw_flag',
						'sNop'
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
					name : 'register_number',
					type : "string"
				},
				{
					name : 'device_code',
					type : "string"
				},
				{
					name : 'resolution_ver',
					type : "int"
				},
				{
					name : 'tag_name',
					type : "string"
				},
				{
					name : 'data_type',
					type : "int"
				},
				{
					name : 'rw_flag',
					type : "int"
				},
				{
					name : 'sNop',
					type : "string"
				}
			]);

	var store = new Ext.data.Store({
				baseParams : {
					action : 'list'
				},
				proxy : new Ext.data.HttpProxy({
							url : '/deviceMonitor/deviceTags/list.do'
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
					header : 'register_number',
					width : 110,
					sortable : false,
					dataIndex : 'register_number'
				},
				{
					header : 'device_code',
					width : 110,
					sortable : false,
					dataIndex : 'device_code'
				},
				{
					header : 'resolution_ver',
					width : 110,
					sortable : false,
					dataIndex : 'resolution_ver'
				},
				{
					header : 'tag_name',
					width : 110,
					sortable : false,
					dataIndex : 'tag_name'
				},
				{
					header : 'data_type',
					width : 110,
					sortable : false,
					dataIndex : 'data_type'
				},
				{
					header : 'rw_flag',
					width : 110,
					sortable : false,
					dataIndex : 'rw_flag'
				},
				{
					header : 'sNop',
					width : 110,
					sortable : false,
					dataIndex : 'sNop'
				}
			]);

	function func_form_save() {
		if (!_form.getForm().isValid())
			return;
			
		var id = _form.form.findField('id').getValue();
		var url = "/deviceMonitor/deviceTags/insert.do";
		var params = {};
		params["action"] = "insert";
		if (id) {
			params["action"] = "update";
			url = "/deviceMonitor/deviceTags/update.do";
		}

		var key = _form.form.findField('key').getValue();
		Ext.Ajax.request({
		url : "/deviceMonitor/deviceTags/isExists.do",
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
										url : '/deviceMonitor/deviceTags/delete.do?delIds='
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