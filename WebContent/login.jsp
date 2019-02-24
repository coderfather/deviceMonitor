<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>设备监控后台管理系统-登录</title>
<style type=text/css>
body {
	padding: 0;
	margin: 0;
	text-align: center
}
.logincontent {
	margin: 100px auto 0 auto;
	width: 950px;
	padding-top: auto;
	padding-bottom: auto;
	height: 100%;
	vertical-align: middle;
}
.loginbg {
	width: 950px;
	height:103px;
	background-image: url(resources/images/bottom.jpg);
	background-repeat: no-repeat;
	font-size:12px;
	align: right;
	padding-right:20px;
}
</style>
<jsp:include page="/module/common/extjs.jsp"></jsp:include>
<script type="text/javascript">
function login(){
		if(document.getElementById("userName").value == ""){
			alert("请输入用户名！");
			return;
		}
		if(document.getElementById("userPwd").value == ""){
			alert("请输入密码！");
			return;
		}
		if(document.getElementById("verifyCode").value == ""){
			alert("请输入验证码！");
			return;
		}
		
		//发起ajax请求
		Ext.Ajax.request({
			url : '/deviceMonitor/user/login.do',
			method : 'POST',
			async : false,
			params : {
				userName : document.getElementById("userName").value,
				userPwd : document.getElementById("userPwd").value,
				verifyCode : document.getElementById("verifyCode").value
			},
			success : function (response, options){
					responseObject = eval("(" + response.responseText + ")");
					if(responseObject.success == true){
						window.location.href = "main.jsp";
					}
					else{
						alert(responseObject.message);
						document.getElementById("verifyCode").value = "";
						document.getElementById("code").click();
					}
				}
		});
	}
	document.onkeydown = function(e){
		var key = (document.all) ? window.event.keyCode :e.which;	
		if(key == 13){
			login();
		}
	}
</script>
</head>
<body onload="javascript:document.getElementById('userName').focus();">
<form action="" name="loginForm">
<div class="logincontent">
<table border="0" cellpadding="0" cellspacing="0" style="margin:0;padding:0;">
	<tr>
		<td style="margin:0;padding:0;">
		<img alt="" src="resources/images/login.png">
		</td>
	</tr>
	<tr>
		<td class="loginbg"  align="right">
		<table>
			<tr>
				<td><img src="resources/images/login/user.gif" id="user"></img></td>
				<td>登录名：</td>
				<td><input type="text" name="userName" style="width: 160px"
					id="userName" value="" onfocus="" /></td>
				<td><img src="resources/images/login/pwd.gif" id="user"></img></td>
				<td>密码：</td>
				<td><input type="password" name="userPwd" style="width: 100px"
					id="userPwd" value="" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;验证码：</td>
				<td><input type="text" name="verifyCode" id="verifyCode" value=""
					style="width: 60px;" maxlength="4" /></td>
				<td><img title="点击更新" id="code"
					style="height: 20px; width: 60px; cursor:pointer;"
					src="/deviceMonitor/user/getCode.do?rd=<%=(new java.util.Date()).toString() %>"
					onclick="javascript:this.src='/deviceMonitor/user/getCode.do?rd='+new Date().toString()" /></td>
			</tr>
			<tr>
				<td colspan="6">&nbsp;</td>
				<td align="left" valign="middle" colspan="3">&nbsp;&nbsp;
					<img src="resources/images/login/ok.gif" style="cursor:pointer" onclick="login();">&nbsp;&nbsp;
				<img src="resources/images/login/cancel.gif"
					style="cursor:pointer" onclick="document.forms[0].reset()" />&nbsp;&nbsp;
					<a href="#" onclick="javascript:window.open('changePwd.jsp','','height=213,width=260;');">修改密码</a>				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</div>
</form>
</body>
</html>