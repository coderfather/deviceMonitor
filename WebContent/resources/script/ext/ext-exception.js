
Ext.Ajax.on('requestexception', function(conn, response, options) {
			var errCode = response.status;
			var errMsg = '';
			switch (errCode) {
				case 403 : {
					errMsg = '服务器拒绝该请求。';
					break;
				};
				case 404 : {
					errMsg = '请求的资源不存在。';
					break;
				};
				case 503 : {
					errMsg = '系统正在维护，请稍后再试。';
					break;
				};
				case 500 : {
					errMsg = '系统正在维护，请稍后再试。';
					break;
				};
				default : {
					errMsg = '未知错误代码:' + errCode+'。';
					break;
				}
			}
			Ext.Ajax.abort();
			Ext.MessageBox.show({
				title : '提示',
				msg : errMsg,
				buttons : Ext.MessageBox.OK,
				width : 300,
				height : 200,
				icon : Ext.MessageBox.ERROR
			});		
		})

Ext.Ajax.on('requestcomplete', function(conn, response, options) {
	try {
		var jsonReturn = Ext.util.JSON.decode(response.responseText);
		if (jsonReturn && jsonReturn.exception) {
			Ext.Ajax.abort();
			Ext.MessageBox.show({
				title : '提示',
				msg : '抱歉，系统发生错误，出错信息如下:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+jsonReturn.message+'。',
				buttons : Ext.MessageBox.OK,
				width : 350,
				height : 200,
				icon : Ext.MessageBox.ERROR
			});
		} 
	} catch(e){
		Ext.Ajax.abort();
//		Ext.Msg.alert('错误', '抱歉，系统发生未知错误:');
	}
			
		});
//表单提交失败		
dmt.formSubmitException = function(action) {
	try {
		var jsonReturn = Ext.util.JSON.decode(action.response.responseText);
		if (jsonReturn && jsonReturn.exception) {
			Ext.Ajax.abort();
			Ext.MessageBox.show({
				title : '提示',
				msg : '抱歉，系统发生错误，出错信息如下:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+jsonReturn.message+'。',
				buttons : Ext.MessageBox.OK,
				width : 350,
				height : 200,
				icon : Ext.MessageBox.ERROR
			});
		}
	} catch(e){
		Ext.Ajax.abort();
//		Ext.Msg.alert('错误', '抱歉，系统发生未知错误:');
	}
}

