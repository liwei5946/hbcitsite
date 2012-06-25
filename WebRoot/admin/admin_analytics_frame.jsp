<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="checkuser.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<!--admin_analytics_frame.jsp-->
    <title>访问量统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
    <script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDialog.js"></script>
</head>
<body>
	<iframe src="admin_analytics.jsp" frameborder="0" scrolling="no" height="560px" width="100%" ></iframe>
</body>
</html>
