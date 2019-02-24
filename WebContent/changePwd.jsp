<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改个人密码</title>
<style type="text/css">
	html {
		overflow-x:hidden;overflow-y:hidden;
	}
</style>
<jsp:include page="/module/common/extjs.jsp"></jsp:include>
<script type="text/javascript">
<!--

	function submitForm(){
		if(document.getElementById("userName").value == ""){
			alert("请输入用户名！");
			return false;
		}
		if(document.getElementById("oldPwd").value == ""){
			alert("原始密码不能为空！");
			return false;
		}
		if(document.getElementById("newPwd").value == ""){
			alert("新密码不能为空！");
			return false;
		}
		if(document.getElementById("comfirmPwd").value != document.getElementById("newPwd").value){
			alert("两次密码输入不一致！");
			return false;
		}
		//发起ajax请求
		Ext.Ajax.request({
			url : '/deviceMonitor/user/changePwd.do',
			method : 'POST',
			async : false,
			params : {
				oldPwd : document.getElementById("oldPwd").value,
				newPwd : document.getElementById("newPwd").value,
				userName : document.getElementById("userName").value
			},
			success : function (response, options){
					var o = eval("(" + response.responseText + ")");
					alert(o.message);
					if(o.success == true){
						window.close();
					}
			}
		});
	}
-->
</script>
<script type="text/javascript" src="/module/common/changePwd.js"></script>
</head>
<body>
</body>
</html>



