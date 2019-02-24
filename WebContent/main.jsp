<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>设备监控后台管理系统</title>
  </head>
  
  <frameset rows="*,23" frameborder="NO" border="0" framespacing="0" id="mainframes">
	<frame src="index.jsp" name="mainFrame"  scrolling="NO">
	<frame src="bottom.jsp" name="bottomFrame" scrolling="NO" noresize>
  </frameset>
  <noframes>
  	<body>抱歉，您当前的浏览器不支持页面框架（frameset），请升级浏览器版本或选择其它浏览器访问本系统！</body>
  </noframes>
</html>
