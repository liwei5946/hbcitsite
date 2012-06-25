<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="checkuser.jsp"%>
<html>

<head>

<title>后台管理系统</title>
</head>

<frameset framespacing="0" border="0" frameborder="0" rows="70,*">
	<frame name="banner" id="banner" scrolling="no" noresize target="contents" src="admin_top.jsp">
	<frameset cols="138,*">
		<frame name="contents" id="contents" target="main" src="admin_left.jsp">
		<frame name="main"  id="main" src="bg.html" target="_self" scrolling="auto" noresize>
	</frameset>
	<noframes>
	<body>

	<p>此网页使用了框架，但您的浏览器不支持框架。</p>

	</body>
	</noframes>
</frameset>

</html>
