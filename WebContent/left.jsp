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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
    p {
        margin:5px;
    }
    .settings {
        background-image:url(<%=basePath%>resources/images/folder_wrench.png);
    }
    .nav {
        background-image:url(<%=basePath%>resources/images/folder_go.png);
    }
     .systemmanager
    {
	    background-image: url(resources/images/folder_wrench.png) !important;
    }
    iframe{width: 100%;}
    *html iframe{width:99.9%;}
   </style>
  	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/script/ext/resources/css/ext-all.css" />
   	<script type="text/javascript" src="<%=basePath%>resources/script/ext/ext-base.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/script/ext/ext-all.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/script/ext/ext-lang-zh_CN.js"></script>
	<jsp:include page="/module/common/extjs.jsp"></jsp:include>	
	<script type="text/javascript">
	   var __UrlBase = "<%=basePath%>";
	   Ext.BLANK_IMAGE_URL = '<%=basePath%>resources/script/ext/resources/images/default/s.gif';
   </script>
   
    <script type="text/javascript" src="<%=basePath%>resources/script/ext/ext-ux-extensions.js"></script>
	<script type='text/javascript' src='<%=basePath%>module/common/left.js'> </script>
  </head>
  
   
  	<body>

  	<div id="southa" >
	   
	    <a href=""  target="main" id="test"></a>
	</div>
  	</body>
</html>
