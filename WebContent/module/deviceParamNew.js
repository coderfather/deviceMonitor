Ext.onReady(function() {
	Ext.QuickTips.init();
	
	//创建可重用的renderer
	Ext.util.Format.comboRenderer = function(combo){
	    return function(value){
	        var record = combo.findRecord(combo.valueField, value);
	        return record ? record.get(combo.displayField) : combo.valueNotFoundText;
	    }
	}
	
	var userRecord = Ext.data.Record.create([{
		name : "id",
		type : "int"
	}, {
		name : "user_name",
		type : "string"
	}]);
	
	var userStore = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
						url : '/deviceMonitor/user/list.do',
						method : "POST"  
					}),
			reader : new Ext.data.JsonReader({
						root : 'list',
						totalProperty : 'totalSize',
						id : 'id'
					}, userRecord)
		});
	
	userStore.load();
	
	var cboUser = new Ext.form.ComboBox({
			id : 'cboUser',
			name : 'cboUser',
			fieldLabel : '用户姓名',
			selectOnFocus : true,
			hiddenName : 'user_id',
			valueField : 'id',
			displayField : 'user_name',
			emptyText : '请选择',
			editable : false,
			forceSelection : true,
			triggerAction : 'all',
			allowBlank : false,
			store : userStore,
			width : 147,
			itemCls : 'begin',
			clearCls : 'allow-float',
			allowBlank: false,
			blankText: '请选择用户姓名'
		});
	
	var group1 = Ext.data.Record.create([{
		name : "code",
		type : "string"
	}, {
		name : "name",
		type : "string"
	}]);
	
	var group1Store = new Ext.data.Store({
			baseParams : {type : 'group1'},
			proxy : new Ext.data.HttpProxy({
						url : '/deviceMonitor/sysParam/listByType.do',
						method : "POST"  
					}),
			reader : new Ext.data.JsonReader({
						root : 'list',
						totalProperty : 'totalSize',
						id : 'code'
					}, group1)
		});
	
	group1Store.load();
	
	var cboGroup1 = new Ext.form.ComboBox({
			id : 'cboGroup1',
			name : 'cboGroup1',
			fieldLabel : '分类1',
			selectOnFocus : true,
			hiddenName : 'group1_id',
			valueField : 'code',
			displayField : 'name',
			emptyText : '请选择',
			editable : false,
			forceSelection : true,
			triggerAction : 'all',
			allowBlank : false,
			store : group1Store,
			width : 147,
			itemCls : 'begin',
			clearCls : 'allow-float',
			allowBlank: false,
			blankText: '请选择分类1'
		});
	
	var group2 = Ext.data.Record.create([{
		name : "code",
		type : "string"
	}, {
		name : "name",
		type : "string"
	}]);
	
	var group2Store = new Ext.data.Store({
			baseParams : {type : 'group2'},
			proxy : new Ext.data.HttpProxy({
						url : '/deviceMonitor/sysParam/listByType.do',
						method : "POST"  
					}),
			reader : new Ext.data.JsonReader({
						root : 'list',
						totalProperty : 'totalSize',
						id : 'id'
					}, group2)
		});
	
	group2Store.load();
	
	var cboGroup2 = new Ext.form.ComboBox({
			id : 'cboGroup2',
			name : 'cboGroup2',
			fieldLabel : '分类2',
			selectOnFocus : true,
			hiddenName : 'group2_id',
			valueField : 'code',
			displayField : 'name',
			emptyText : '请选择',
			editable : false,
			forceSelection : true,
			triggerAction : 'all',
			allowBlank : false,
			store : group2Store,
			width : 147,
			itemCls : 'begin',
			clearCls : 'allow-float',
			allowBlank: false,
			blankText: '请选择分类2'
		});

	var recordType = Ext.data.Record.create([
				{
					name : 'id',
					type : "int"
				},
				{
					name : 'register_number',
					type : "string"
				},
				{
					name : 'device_name',
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
					name : 'max_comm_volume',
					type : "int"
				},
				{
					name : 'max_table_volume',
					type : "int"
				},
				{
					name : 'user_id',
					type : "int"
				},
				{
					name : 'group1_id',
					type : "string"
				},
				{
					name : 'group2_id',
					type : "string"
				}
			]);

	var store = new Ext.data.Store({
				baseParams : {
					action : 'list'
				},
				proxy : new Ext.data.HttpProxy({
							url : '/deviceMonitor/deviceStation/list.do',
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
					dataIndex : 'register_number'
				},
				{
					header : '设备名称',
					width : 110,
					sortable : false,
					dataIndex : 'device_name'
				},
				{
					header : '设备编号',
					width : 110,
					sortable : false,
					dataIndex : 'device_code'
				},
				{
					header : '允许最大通信量',
					width : 110,
					sortable : false,
					dataIndex : 'max_comm_volume'
				},
				{
					header : '允许最大表格数量',
					width : 110,
					sortable : false,
					dataIndex : 'max_table_volume'
				},
				{
					header : '用户姓名',
					width : 110,
					sortable : false,
					dataIndex : 'user_id',
					renderer: Ext.util.Format.comboRenderer(cboUser)
				},
				{
					header : '分类1',
					width : 110,
					sortable : false,
					dataIndex : 'group1_id',
					renderer: Ext.util.Format.comboRenderer(cboGroup1)
				},
				{
					header : '分类2',
					width : 110,
					sortable : false,
					dataIndex : 'group2_id',
					renderer: Ext.util.Format.comboRenderer(cboGroup2)
				}
			]);

	function func_grid_set() {
		if (_grid.selModel.hasSelection() == false) {
			Ext.getCmp('tBtnSave').disable();
			editStore.removeAll();
			return;
		}

		var records = _grid.selModel.getSelections();
		if (records.length > 1) {
			Ext.MessageBox.show({
						title : '错误信息',
						msg : '一次只能选择一条记录！',
						buttons : Ext.MessageBox.OK,
						width : 280,
						icon : Ext.MessageBox.ERROR
					});
			return;
		}
		
		var record = _grid.getSelectionModel().getSelected();
		var registerNumber = record.data['register_number'];
		var resolutionVer = record.data['resolution_ver'];
		
		editStore.load({
			params : {
				start : 0,
				limit : 20,
				action : "list",
				registerNumber : registerNumber,
				resolutionVer : resolutionVer
			},
			callback: function(records, operation, success) {
				// 根据ID得到列索引
				var col = _editorGrid.getColumnModel().getIndexById("tag_name");
				// 设置该列为不可编辑
				_editorGrid.getColumnModel().setEditable(col, false);
				
				col = _editorGrid.getColumnModel().getIndexById("id");
				_editorGrid.getColumnModel().setEditable(col, false);
				
				col = _editorGrid.getColumnModel().getIndexById("data_type");
				_editorGrid.getColumnModel().setEditable(col, false);
				
				col = _editorGrid.getColumnModel().getIndexById("rw_flag");
				_editorGrid.getColumnModel().setEditable(col, false);
				if (records.length == 0 || (records[0] && !records[0].data.editable)) {
					Ext.getCmp('tBtnSave').disable();
				} else {
					Ext.getCmp('tBtnSave').enable();
				}
			}
		});
	}
	
	//checkbox选择模型  
    var editSm = new Ext.grid.CheckboxSelectionModel({ checkOnly: true });  
    
    // 构造ColumnModel  
    var editCm = new Ext.grid.ColumnModel({  
        columns: [  
        {  
            id: 'id',  
            header: 'ID',  
            dataIndex: 'id',  
            width: 220,  
            // 使用上边定义好的别名  
            editor: new Ext.form.TextField({  
                allowBlank: false  
            })  
        },
        {  
            id: 'tag_name',  
            header: 'Tag名称',  
            dataIndex: 'tag_name',  
            width: 220,  
            // 使用上边定义好的别名  
            editor: new Ext.form.TextField({  
                allowBlank: false  
            })  
        },
        {  
            id: 'data_type',  
            header: '数据类型',  
            dataIndex: 'data_type',  
            width: 220,  
            // 使用上边定义好的别名  
            editor: new Ext.form.TextField({  
                allowBlank: false  
            })  
        },
        {  
            id: 'rw_flag',  
            header: '读写权限',  
            dataIndex: 'rw_flag',  
            width: 220,  
            // 使用上边定义好的别名  
            editor: new Ext.form.TextField({  
                allowBlank: false  
            })  
        },
        {  
            id: 'display_name',  
            header: '显示名称',  
            dataIndex: 'display_name',  
            width: 220,  
            // 使用上边定义好的别名  
            editor: new Ext.form.TextField({  
                allowBlank: false  
            })  
        },
        {  
            id: 'display_value',  
            header: '显示值',  
            dataIndex: 'display_value',  
            width: 220,  
            // 使用上边定义好的别名  
            editor: new Ext.form.TextField({  
                allowBlank: false  
            })  
        },
        {  
            id: 'display_format',  
            header: '显示格式',  
            dataIndex: 'display_format',  
            width: 220,  
            // 使用上边定义好的别名  
            editor: new Ext.form.TextField({  
                allowBlank: false  
            })  
        },
        {  
            id: 'display_remark',  
            header: '显示说明',  
            dataIndex: 'display_remark',  
            width: 220,  
            // 使用上边定义好的别名  
            editor: new Ext.form.TextField({  
                allowBlank: false  
            })  
        }
        ],  
        defaults: {  
            sortable: true  
        }  
    });  
    
	var editRecordType = Ext.data.Record.create([
			{
				name : 'id',
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
			},
			{
				name : 'editable',
				type : "boolean"
			},
			{
				name : 'register_number',
				type : "string"
			},
			{
				name : 'tagId',
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
				name : 'display_name',
				type : "string"
			},
			{
				name : 'display_value',
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
	
	var editStore = new Ext.data.Store({
			baseParams : {
				action : 'list'
			},
			proxy : new Ext.data.HttpProxy({
						url : '/deviceMonitor/deviceTags/list.do',
						method : "POST"  
					}),
			reader : new Ext.data.JsonReader({
						root : 'list',
						totalProperty : 'totalSize',
						id : 'id'
					}, editRecordType)
		});

	var editDisplayMsg = '显示第[<b><font color=red>{0}</font></b>]条到[<b><font color=red>{1}</font></b>]条记录 / 总共[<b><font color=red>{2}</font></b>]记录';

	var editGrid_bbar = new Ext.PagingToolbar({
				store : editStore,
				displayMsg : editDisplayMsg,
				emptyMsg : '没有记录',
				pageSize : 100,
				displayInfo : true
			});
	
	var _editorGrid = new Ext.grid.EditorGridPanel({
		//sm: editSm,
		store: editStore,  
		cm: editCm,  
        autoExpandColumn: 'id',  
        title: '通道设置',  
        frame: true,  
        clicksToEdit: 1,  //默认2次双击才触发编辑框事件
        bbar : editGrid_bbar,
        tbar : [{
        	id : 'tBtnSave',
			text : '保存',
			iconCls : 'icon_update',
			width : '24px',
			handler : func_editGrid_save
		}],
		viewConfig : {
             getRowClass : function(record, rowIndex, rowParams, store) {
            	 if (!record.data.editable) {
            		 return 'x-grid-record-gray';
            	 }
		     }
		},
		listeners : {
		    beforeedit : function(e) {
		    	return e.record.data.editable;
		    }
		}
	});
	
	function func_editGrid_save() {
		var record = _grid.getSelectionModel().getSelected();
		var registerNumber = record.data['register_number'];
		
		var mod = _editorGrid.getStore().getModifiedRecords();  
		// var mod = editStore.modified;  
		var json = [];  
	    Ext.each(mod, function(item) {    
	        json.push(item.data);    
	    });    
	    if (json.length >= 0) {    
	        Ext.Ajax.request({    
	            url: "/deviceMonitor/deviceTags/save.do",    
	  			params: { 
	  				    registerNumber : registerNumber,
	  					data : Ext.util.JSON.encode(json) 
	  				},    
				method: "POST",    
				success: function(response) {    
			      	Ext.Msg.alert("信息", "数据更新成功！", function() { 
			      			Ext.getCmp('tBtnSave').disable();
			      			//editStore.reload(); 
			      		});    
			    },    
			    failure: function(response) {    
	       			Ext.Msg.alert("警告", "数据更新失败，请稍后再试！");    
	            }    
	        });    
	    }    
	    else {    
	        Ext.Msg.alert("警告", "没有任何需要更新的数据！");    
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
				items : [/**{
							text : '通道设置',
							iconCls : 'icon-pub1',
							handler : func_grid_set
						}**/]
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
				/**
				tbar : [{
							text : '通道设置',
							iconCls : 'icon-pub1',
							width : '24px',
							handler : func_grid_set
						}],
				**/
				listeners : {
					'contextmenu' : function(e) {
						e.stopEvent();
					},
					'rowcontextmenu' : function(_grid, rowIndex, e) {
						_grid.selModel.selectRow(rowIndex);
						e.stopEvent();
						contextMenu.showAt(e.getXY());
					},
					'rowclick' : func_grid_set
				},
				bbar : grid_bbar
			});

	var layout = new Ext.Viewport({
				layout : 'border',
				hideBorder : false,
				forceLayout : true,
				items : [{
							region : 'west',
							collapsible: true, 
							split:true, 
							title: '注册点设备',  
					        xtype: 'panel',
							width: 392,  
				            autoScroll: false,  
							items : [_grid]
						}, {
							region : 'center',
							title: '通道设置',  
							xtype: 'tabpanel',  
				            activeItem: 0,  
							items : [_editorGrid]
						}]
			});
	
	function layout_resize() {
		//Ext.getCmp("grid").setWidth(Ext.getBody().getWidth());
		Ext.getCmp("grid").setHeight(Ext.getBody().getHeight() - 2);
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