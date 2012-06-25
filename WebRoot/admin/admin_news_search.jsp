<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="checkuser.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<link href="${pageContext.request.contextPath }/admin/css/subcss.css" type="text/css" rel="stylesheet" />
		<style>
			body { background: #ffffff; color: #444;font-size:12px; }
			a { color: #07c; text-decoration: none; border: 0; background-color: transparent; }
			body, div, q, iframe, form, h5 { margin: 0; padding: 0; }
			img, fieldset { border: none 0; }
			body, td, textarea { word-break: break-all; word-wrap: break-word; line-height:1.6; }
			body, input, textarea, select, button { margin: 0; font-size: 12px; font-family: Tahoma, SimSun, sans-serif; }
			div, p, table, th, td { font-size:1em; font-family:inherit; line-height:inherit; }
			h5 { font-size:12px; }
			ol li,ul li{ margin-bottom:0.5em;}
			pre, code { font-family: "Courier New", Courier, monospace; word-wrap:break-word; line-height:1.4; font-size:12px;}
			pre{background:#f6f6f6; border:#eee solid 1px; margin:1em 0.5em; padding:0.5em 1em;}
			#content { padding-left:50px; padding-right:50px; }
			#content h2 { font-size:20px; color:#069; padding-top:8px; margin-bottom:8px; }
			#content h3 { margin:8px 0; font-size:14px; COLOR:#693; }
			#content h4 { margin:8px 0; font-size:16px; COLOR:#690; }
			#content div.item { margin-top:10px; margin-bottom:10px; border:#eee solid 4px; padding:10px; }
			hr { clear:both; margin:7px 0; +margin: 0;
			border:0 none; font-size: 1px; line-height:1px; color: #069; background-color:#069; height: 1px; }
			.infobar { background:#fff9e3; border:1px solid #fadc80; color:#743e04; }
			.buttonStyle{width:64px;height:22px;line-height:22px;color:#369;text-align:center;background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left top;border:0;font-size:12px;}
			.buttonStyle:hover{background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left -23px;}
			.input_on{padding:2px 8px 0pt 3px;height:18px;border:1px solid #999;background-color:#eeeeee;}
			
		</style>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDrag.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDialog.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/admin/My97DatePicker/WdatePicker.js"></script>
	</head>
	<script language="JavaScript">
<!--
		function fun1()
		{
		}
		//
		function fun2()
		{
			parentDialog.close();
		}
		
		//
		function alterpws()
		{
			var title=$("#title").val();
			var content=$("#content").val();
			var contentType=$("#contentType").val();
			var Laiyuan=$("#Laiyuan").val();
			var stime=$("#stime").val();
			var etime=$("#etime").val();
			//
			parent.location.replace("${pageContext.request.contextPath}/servlet/AdminNewsListServlet?cpage=1&title="+title+"&content="+content+"&contentType="+contentType+"&Laiyuan="+Laiyuan+"&stime="+stime+"&etime="+etime+"&clearSession=yes");
			parentDialog.close();
		}
//-->
</script>
	<body style="margin-top:30px">

			<div align="center">
				<p>
				</p>
				<table border="1" width="420" id="table1" cellspacing="0"
					cellpadding="7" style="border-collapse: collapse"
					bordercolor="#C0C0C0">
					<tr>
						<td width="80" align="center">
							标题关键字：
						</td>
						<td>
							<input type="text"  name="title" id="title" size="45" maxlength= "15">
						</td>
					</tr>
					<tr>
						<td width="80" align="center">
							内容关键字：
						</td>
						<td>
							<input type="text" name="content" id="content" size="40" maxlength= "15">
						</td>
					</tr>
					<tr>
						<td width="80" align="center">
							栏目选择：
						</td>
						<td>
							<select  name="contentType" id="contentType" >
								<option value="" >所有栏目</option>
								<c:forEach items="${notes}" var="notes">
									<option value="${notes}" >${notes}</option>
								</c:forEach>
							</select>							
						</td>
					</tr>
					<tr>
						<td width="80" align="center">
							来源部门：
						</td>
						<td>
							<select  name="Laiyuan" id="Laiyuan">
								<option value="" >所有部门</option>
								<c:forEach items="${bumen}" var="bumen">
									<option value="${bumen}" >${bumen}</option>
								</c:forEach>
							</select>			
						</td>
					</tr>
					<tr>
						<td width="80" align="center">
							发布时间：		
						</td>
						<td>
							<input readonly id="stime" type="text" name="stime" size="18" value="" maxlength="50">
                			<img onClick="WdatePicker({el:'stime'})" src="${pageContext.request.contextPath }/admin/My97DatePicker/skin/datePicker.gif"width="16" height="22" align="middle">
							
							至<input readonly id="etime" type="text" name="etime" size="18" value="" maxlength="50">
                			<img onClick="WdatePicker({el:'etime'})" src="${pageContext.request.contextPath }/admin/My97DatePicker/skin/datePicker.gif"width="16" height="22" align="middle">
		
						</td>
					</tr>
					<tr>
	  					<td colspan="2" align="center"><input type="button" onClick="alterpws()" value="开始搜索" class="buttonStyle" /> </td>
					</tr>
				</table>
			</div>
			
	</body>
</html>
