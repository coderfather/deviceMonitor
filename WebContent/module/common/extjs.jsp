<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String basePath = request.getContextPath();%>
<link rel="stylesheet" type="text/css"	href="<%=basePath%>/resources/css/commstyle.css" />
<link rel="stylesheet" type="text/css"	href="<%=basePath%>/resources/script/ext/resources/css/ext-all.css" />
<script type="text/javascript" src="<%=basePath%>/resources/script/ext/ext-base.js"></script>
<script type="text/javascript"	src="<%=basePath%>/resources/script/ext/ext-all-debug.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/script/ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
	Ext.namespace('dmt','dmt.user');
	dmt.webRoot='<%=basePath%>';
	dmt.user.id = '<%=(String)session.getAttribute("uid")%>';
	dmt.user.userName = '<%=(String)session.getAttribute("yhm")%>';
	dmt.user.loginName = '<%=(String)session.getAttribute("dlm")%>';
	dmt.user.roleType = '<%=(String)session.getAttribute("roleType")%>';
	Ext.BLANK_IMAGE_URL = dmt.webRoot+'/resources/script/ext/resources/images/default/s.gif';
</script>
<script type="text/javascript" src="<%=basePath%>/resources/script/ext/ext-vtypes.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/script/ext/ext-exception.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/script/ext-common.js"></script>
<noscript>您的浏览器不支持Javascript脚本，请检查您的浏览器版本或安全设置！</noscript>