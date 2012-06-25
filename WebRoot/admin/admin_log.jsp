<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="checkuser.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>系统日志</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/admin/css/subcss.css">
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDrag.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDialog.js"></script>
    <script type="text/javascript">
		//隔行变色
	$(document).ready(function(){
			
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
			
		});
	//
	function showit(message){
			Dialog.alert(message);
		}
		//删除日志
function dellog(beforeday)
{
	$.ajax({
			url :"${pageContext.request.contextPath }/servlet/SystemLogDeleteServlet",
			type : 'post',
			data : 'beforeday='+30,
			success :function(mm){
					var revalue=mm.replace(/\r\n/g,'');
					Dialog.alert("删除了"+revalue+"条日志",function(){location.replace("${pageContext.request.contextPath}/servlet/SystemLogServlet");});
				}
			});
}

	
    </script>

</head>
<body style="margin-top:10px;"  bgcolor="#ffffff" leftmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
<div align="center">
	<div class="title">
系统操作日志
<hr>
</div>
</div>
<table border="1" width="95%" id="table3" class="stripe_tb" style="border-collapse: collapse" bordercolor="#C0C0C0" cellspacing="0" cellpadding="3" align="center">
		<tr style="font-weight:bold">
			<td width="8%" height="25" align="center"><font color="#000000">日志编号</font></td>
		  <td width="14%" height="25" align="center"><font color="#000000">时间</font></td>
		  <td width="65%" height="25" align="center"><font color="#000000">日志内容
         <span> (<a  style="color:#0000FF; text-decoration:underline;" href="#" onClick="Dialog.confirm('提示：您确认要删除日志吗？',function(){dellog();});">删除30天前的日志</a>)</span>
          </font></td>
	      <td width="8%" height="25" align="center" bgcolor="#D6D6D6"><font color="#000000">重要级别</font></td>
		  <td width="5%" height="25" align="center"><font color="#000000">来源类</font></td>
	  </tr>
<c:forEach var="mylog" items="${loglist}" varStatus="countItem"> 
        <tr>
			<td align="center">${mylog.id}</td>
		  	<td align="center">${mylog.logtime}</td>
			<td align="left">
<a href="javascript:void(0)" onClick="showit('${mylog.msg}');">
        <c:choose>
        <c:when test="${fn:length(mylog.msg) > 50}"><c:out value="${fn:substring(mylog.msg, 0, 50)}..." /></c:when>
        <c:otherwise><c:out value="${mylog.msg}" /></c:otherwise>
        </c:choose> 
</a>
            </td>
			<td align="center">${mylog.loglevel}</td>
			<td align="center"><a href="javascript:void(0)" onClick="showit('${mylog.logclass}');">查看</a></td>
		</tr>
</c:forEach>
	</table>

</body>
</html>
