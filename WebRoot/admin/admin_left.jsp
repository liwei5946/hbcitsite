<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="checkuser.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>管理列表</title>
<base target="main">
<link href="mycss.css" rel="stylesheet"/>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDrag.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDialog.js"></script>
<script type="text/javascript">
	function back()
	{
		$.ajax({
					url :"${pageContext.request.contextPath }/servlet/DBOperation",
					type : 'get',
				    data : 'Condition=0',
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								alert("备份成功!");
							}
							else
								alert("备份失败!");
					}
			});	
	}
	//
	function restore()
	{
		if(confirm("恢复数据库将覆盖现有数据，可能造成最新数据的丢失，确定吗？"))
   		{
		    $.ajax({
							url :"${pageContext.request.contextPath }/servlet/DBOperation",
							type : 'get',
						    data : 'Condition=1',
							success :function(mm){
								 	var revalue=mm.replace(/\r\n/g,'');
									if(revalue=="success")
									{
										alert("恢复成功!");
									}
									else
										alert("恢复失败!");
							}
					});	
   		}
		
		
	}
</script>
</head>

<body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" background="images/bj2.jpg" >

<table border="0" width="131" id="table1" cellspacing="0" cellpadding="0">
	<!--所有用户操作  -->
	<tr>
		<td align="center" width="131" colspan="2">
		<img border="0" src="images/nothing.jpg" width="4" height="3"></td>
	</tr>
	<tr>
		<td align="center" height="32" background="images/bj1.jpg" width="36">
		<p>
		<img border="0" src="images/1001.jpg" width="24" height="22"></td>
		<td align="left" width="95" height="32" background="images/bj.jpg">
		<a target="main" href="/hbcitsite/servlet/sendRedirectNewsAddServlet?do=add">
		内容增加</a></td>
	</tr>
	<tr>
		<td align="right" height="32" background="images/bj1.jpg" width="36">
		<p align="center">
		<img border="0" src="images/1002.jpg" width="24" height="22"></td>
		<td align="left" width="95" height="32" background="images/bj.jpg">
			<a target="main" href="/hbcitsite/servlet/AdminNewsListServlet?cpage=1&clearSession=yes">
		内容修改</a></td>
	</tr>
	  
 	<tr>
 		<%--李玮2011-11-11添加--%>
 		<td align="right" height="32" background="images/bj1.jpg" width="36">
		<p align="center">
		<img border="0" src="images/1109.gif" width="24" height="22"></td>
		<td align="left" width="95" height="32" background="images/bj.jpg">
			<a target="main" href="/hbcitsite/admin/admin_analytics_frame.jsp">访问量统计</a>
		</td>
		
	</tr>	
	
<!--管理员权限操作  -->
	<tr>
		<td align="center" height="32" background="images/bj1.jpg" width="36">
			<img border="0" src="images/1003.jpg" width="24" height="22">
		</td>
		<td align="left" width="95" height="32" background="images/bj.jpg">
			<c:forEach items="${admin}" var="admin">
				<c:if test="${admin.dj =='1' }">
					<a target="main" href="/hbcitsite/servlet/LmSelectServlet?Condition=0">	栏目管理</a>
				</c:if>
				<c:if test="${admin.dj!='1'}">
					<span style='overflow:hidden;word-wrap:break-word;word-break:break:all; font-size:12px; '><font disabled>	栏目管理</font></span>
				</c:if>
			</c:forEach>
		</td>
	</tr>

	<tr>
		<td align="center" height="32" background="images/bj1.jpg" width="36">
		<p>
		<img border="0" src="images/1008.gif" width="23" height="24"></td>
		<td align="left" width="95" height="32" background="images/bj.jpg">
		
		<c:forEach items="${admin}" var="admin">
			<c:if test="${admin.dj=='1'}">
				<a target="main" href="/hbcitsite/servlet/UserSelectServlet?Condition=0">	用户管理</a>
			</c:if>			
			<c:if test="${admin.dj!='1'}">
				<span style='overflow:hidden;word-wrap:break-word;word-break:break:all; font-size:12px; '><font disabled>	用户管理</font></span>
			</c:if>
		</c:forEach>
	
		</td>
	</tr>
	<tr>
		<td align="center" height="32" background="images/bj1.jpg" width="36">
		<p>
		<img border="0" src="images/1011.gif" width="24" height="23"></td>
		<td align="left" width="95" height="32" background="images/bj.jpg">
		
		<c:forEach items="${admin}" var="admin">
			<c:if test="${admin.dj=='1'}">
				<a target="main" href="/hbcitsite/servlet/IndexBanKuaiSetServlet?conditions=1">主页版块设置</a>
			</c:if>
			<c:if test="${admin.dj!='1'}">
				<span style='overflow:hidden;word-wrap:break-word;word-break:break:all; font-size:12px; '><font disabled>主页版块设置</font></span>
			</c:if>
		</c:forEach>
		
		</td>
	</tr>
    
    <tr><%--李玮2011-10-31添加--%>
		<td align="center" height="32" background="images/bj1.jpg" width="36">
		<p>
		<img border="0" src="images/1104.gif" width="24" height="24"></td>
		<td align="left" width="95" height="32" background="images/bj.jpg">
		<c:forEach items="${admin}" var="admin">
			<c:if test="${admin.dj=='1'}">
				<a target="main" href="/hbcitsite/servlet/SystemLogServlet">系统日志</a>
			</c:if>
			<c:if test="${admin.dj!='1'}">
				<span style='overflow:hidden;word-wrap:break-word;word-break:break:all; font-size:12px; '><font disabled>系统日志</font></span>
			</c:if>
		</c:forEach>
	
		</td>
	</tr>
   
    
	<tr>
		<td align="center" height="32" background="images/bj1.jpg" width="36">
		<p>
		<img border="0" src="images/1013.gif" width="24" height="24"></td>
		<td align="left" width="95" height="32" background="images/bj.jpg">
		<c:forEach items="${admin}" var="admin">
			<c:if test="${admin.dj=='1'}">
				<a target="main" href="/hbcitsite/admin/admin_news_count.jsp">	数据统计</a>
			</c:if>
			<c:if test="${admin.dj!='1'}">
				<span style='overflow:hidden;word-wrap:break-word;word-break:break:all; font-size:12px; '><font disabled>数据统计</font></span>
			</c:if>
		</c:forEach>
	
		</td>
	</tr>
	<!--  
	<tr>
		<td align="center" height="32" background="images/bj1.jpg" width="36">
		<p>
		<img border="0" src="images/1014.gif" width="24" height="24"></td>
		<td align="left" width="95" height="32" background="images/bj.jpg"><a target="main" href="#">
		服务器情况</a></td>
	</tr>
	
	-->
	<tr>
		<td align="center" height="32" background="images/bj1.jpg" width="36">
		<p>
		<img border="0" src="images/1015.gif" width="24" height="20"></td>
		<td align="left" width="95" height="32" background="images/bj.jpg">
		<c:forEach items="${admin}" var="admin">
			<c:if test="${admin.dj=='1'}">
				<a href="/hbcitsite/admin/bg.html" onClick="back();">
				备份</a> <a  href="/hbcitsite/admin/bg.html" onClick="restore();">恢复</a> 
			</c:if>
			<c:if test="${admin.dj!='1'}">
				<span style='overflow:hidden;word-wrap:break-word;word-break:break:all; font-size:12px; '><font disabled>
				备份     恢复</font></span>
			</c:if>
		</c:forEach>
		</td>
	</tr>

<!--管理员权限操作 结束 -->	
	<tr>
		<td align="center" height="32" background="images/bj1.jpg" width="36">
		<p>
		<img border="0" src="images/1016.gif" width="19" height="24"></td>
		<td align="left" width="95" height="32" background="images/bj.jpg"><a target="_top" href="logout.jsp">
		退出后台管理</a></td>
	</tr>
	</table>
</body>

</html>

