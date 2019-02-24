Ext.onReady(function() {
	Ext.QuickTips.init();
	
	//创建可重用的renderer
	Ext.util.Format.comboRenderer = function(combo){
	    return function(value){
	        var record = combo.findRecord(combo.valueField, value);
	        return record ? record.get(combo.displayField) : combo.valueNotFoundText;
	    }
	}
	
	var regNumber = Ext.data.Record.create([{
		name : "id",
		type : "int"
	}, {
		name : "register_number",
		type : "string"
	}, {
		name : "device_code",
		type : "string"
	}, {
		name : "device_name",
		type : "string"
	}]);
	
	var regNumberStore = new Ext.data.Store({
			baseParams : {type : 'regNumber'},
			proxy : new Ext.data.HttpProxy({
						url : '/deviceMonitor/deviceStation/list.do',
						method : "POST"  
					}),
			reader : new Ext.data.JsonReader({
						root : 'list',
						totalProperty : 'totalSize',
						id : 'id'
					}, regNumber)
		});
	
	regNumberStore.load();
	
	var cboRegNumber = new Ext.form.ComboBox({
			id : 'cboRegNumber',
			name : 'cboRegNumber',
			fieldLabel : '注册号',
			selectOnFocus : true,
			hiddenName : 'device_station_id',
			valueField : 'id',
			displayField : 'register_number',
			emptyText : '请选择',
			editable : false,
			forceSelection : true,
			triggerAction : 'all',
			allowBlank : false,
			store : regNumberStore,
			width : 147,
			itemCls : 'begin',
			clearCls : 'allow-float',
			allowBlank: false,
			blankText: '请选择注册号'
		});
	
	// 联动的实现
	cboRegNumber.on('select', function () {
		var record = regNumberStore.getById(cboRegNumber.getValue());
		_form.form.findField('register_number').setValue(record.data.register_number);
		_form.form.findField('device_code').setValue(record.data.device_code);
		_form.form.findField('device_name').setValue(record.data.device_name);
	  })
	  
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
					cboRegNumber,
					{
						fieldLabel : '注册号',
						id : 'register_number',
						name : 'register_number',
						xtype:'hidden'
					},
					{
						fieldLabel : '当前设备编号',
						id : 'device_code',
						name : 'device_code',
						xtype:'textfield',
						readOnly : true
					},
					{
						fieldLabel : '当前设备名称',
						id : 'device_name',
						name : 'device_name',
						xtype:'textfield',
						readOnly : true
					},
					{
						fieldLabel : 'Tag名称',
						id : 'tag_name',
						name : 'tag_name',
						xtype:'textfield'
					},
					{
						fieldLabel : '显示值',
						id : 'display_value',
						name : 'display_value',
						xtype:'textfield'
					},
					{
						fieldLabel : '读写权',
						id : 'limit_set',
						name : 'limit_set',
						xtype:'textfield'
					},
					{
						fieldLabel : '数据类型',
						id : 'data_type',
						name : 'data_type',
						xtype:'textfield'
					},
					{
						fieldLabel : '显示名称',
						id : 'display_name',
						name : 'display_name',
						xtype:'textfield'
					},
					{
						fieldLabel : '显示格式',
						id : 'display_format',
						name : 'display_format',
						xtype:'textfield'
					},
					{
						fieldLabel : '显示说明',
						id : 'display_remark',
						name : 'display_remark',
						xtype:'textfield'
					}
				],
				reader : new Ext.data.JsonReader({
							root : 'list',
							successProperty : 'success',
							totalProperty : 'totalSize',
							id : 'id'
						}, [
						'id',
						'device_station_id',
						'register_number',
						'device_code',
						'device_name',
						'tag_name',
						'display_value',
						'limit_set',
						'data_type',
						'display_name',
						'display_format',
						'display_remark'
				])
			});

	var _window = new Ext.Window({
				title : '通道参数管理',
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
					name : 'device_station_id',
					type : "int"
				},
				{
					name : 'register_number',
					type : "string"
				},
				{
					name : 'device_code',
					type : "string"
				},
				{
					name : 'device_name',
					type : "string"
				},
				{
					name : 'tag_name',
					type : "string"
				},
				{
					name : 'display_value',
					type : "string"
				},
				{
					name : 'limit_set',
					type : "string"
				},
				{
					name : 'data_type',
					type : "string"
				},
				{
					name : 'display_name',
					type : "string"
				},
				{
					name : 'display_format',
					type : "string"
				},
				{
					name : 'display_remark',
					type : "string"
				}
			]);

	var store = new Ext.data.Store({
				baseParams : {
					action : 'list'
				},
				proxy : new Ext.data.HttpProxy({
							url : '/deviceMonitor/deviceParam/list.do',
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
					header : '注册号',
					width : 110,
					sortable : false,
					dataIndex : 'device_station_id',
					renderer: Ext.util.Format.comboRenderer(cboRegNumber)
				},
				{
					header : '当前设备编号',
					width : 110,
					sortable : false,
					dataIndex : 'device_code'
				},
				{
					header : '当前设备名称',
					width : 110,
					sortable : false,
					dataIndex : 'device_name'
				},
				{
					header : 'Tag名称',
					width : 110,
					sortable : false,
					dataIndex : 'tag_name'
				},
				{
					header : '显示值',
					width : 110,
					sortable : false,
					dataIndex : 'display_value'
				},
				{
					header : '读写权',
					width : 110,
					sortable : false,
					dataIndex : 'limit_set'
				},
				{
					header : '数据类型',
					width : 75,
					sortable : false,
					dataIndex : 'data_type'
				},
				{
					header : '显示名称',
					width : 110,
					sortable : false,
					dataIndex : 'display_name'
				},
				{
					header : '显示格式',
					width : 110,
					sortable : false,
					dataIndex : 'display_format'
				},
				{
					header : '显示说明',
					width : 110,
					sortable : false,
					dataIndex : 'display_remark'
				}
			]);

	function func_form_save() {
		if (!_form.getForm().isValid())
			return;
			
		var id = _form.form.findField('id').getValue();
		var url = "/deviceMonitor/deviceParam/insert.do";
		var params = {};
		params["action"] = "insert";
		if (id) {
			params["action"] = "update";
			url = "/deviceMonitor/deviceParam/update.do";
		}

		var key = _form.form.findField('tag_name').getValue();
		Ext.Ajax.request({
		url : "/deviceMonitor/deviceParam/isExists.do",
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
										url : '/deviceMonitor/deviceParam/delete.do?delIds='
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