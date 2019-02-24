<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>DeviceStation Info</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<style>
		.icon_down {
			background: url('<%=basePath%>resources/images/button-icon/icon_down.png') 0 0 no-repeat   !important;
		}
        .icon_add {
            background: url('<%=basePath%>resources/images/button-icon/icon_add.png') 0 0 no-repeat   !important;
        }
       .icon_delete {
            background: url('<%=basePath%>resources/images/button-icon/icon_delete.png') 0 0 no-repeat  !important;
        }
        .icon_update {
            background: url('<%=basePath%>resources/images/button-icon/icon_update.png')  0 0 no-repeat  !important;
        }
        .icon-pub1 {
            background: url('<%=basePath%>resources/images/button-icon/icon-pub1.png')  0 0 no-repeat  !important;
        }   
        .icon-setRole {
            background: url('<%=basePath%>resources/images/00.gif')  0 0 no-repeat  !important;
        }   
    </style>  
    
	<jsp:include page="/module/common/extjs.jsp"></jsp:include>	
	<script type="text/javascript" src="<%=basePath%>resources/script/ext/ext-exception.js"></script>	
	<script type="text/javascript" src="<%=basePath%>module/deviceStation.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/script/jquerys-1.5.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/script/jquery.upload.js"></script>

	<script>
		Ext.BLANK_IMAGE_URL = '<%=basePath%>resources/script/ext/resources/images/default/s.gif';
	</script>
	
</head>
<body>

</body>
</html>