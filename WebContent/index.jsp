<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>设备监控后台管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <frameset cols="230,2,*" name="sss" frameborder="NO" border="0" framespacing="0" rows="*">
    <frame name="left" noresize scrolling="YES" src="left.jsp">
    <frame src="" name="middle" scrolling="NO" noresize>
    <frame name="main" src="" scrolling="YES">
  </frameset>
  <noframes></noframes>
  <noframes>
  	<body></body>
  </noframes>
</html>
