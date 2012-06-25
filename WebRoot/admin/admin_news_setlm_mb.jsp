<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="checkuser.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<link href="css/subcss.css" type="text/css" rel="stylesheet" />
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
	</head>
	<script language="JavaScript">
<!--
		$(document).ready(function(){
			//
			var objM=document.getElementById("lmmb");  //
		    objM.length=0;	
		    <c:forEach items="${newsMBList}" var="mb">
				objM.add(new Option("${mb.title}","${mb.id}"));
			</c:forEach>
		});
		
		function fun1()
		{
		}
		//
		function fun2()
		{
			parentDialog.close();
		}
		//
		function saveMb()
		{
			var selectMbId=$('#lmmb').val();
			$.ajax({
					url :"${pageContext.request.contextPath}/servlet/NewsMBServletSelect?condition=2",
					type : 'get',
				    data : 'selectMbId='+selectMbId+'&id='+$('#lmid').val(),
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								Dialog.alert("修改成功!",function(){window.location.reload();});
							}
							else
								Dialog.alert("修改失败!");
						}
					});	
			
		}
//-->
</script>
	<body style="margin-top:30px">

			<div align="center">
				<p>
				</p>
				<table border="1" width="300" id="table1" cellspacing="0"
					cellpadding="7" style="border-collapse: collapse"
					bordercolor="#C0C0C0">
					<tr>
						<td width="127" align="center">
							栏目名及栏目号：
							<input id="lmid" type="hidden" value="<%=request.getParameter("id")%>">
						</td>
						<td>
          					<input type="text" id="name" name="name" value="<%=new String(request.getParameter("lm").getBytes("iso-8859-1"),"utf-8")%>" readonly="readonly" class="input_on"/>								</td>
					</tr>
					<tr>
						<td width="127" align="center">
							模板
							
						</td>
						<td>
							<select size="1" name="lmmb" id="lmmb">			
							</select>
						</td>
					</tr>
					
					<tr>
						<td colspan="2" align="center" style="padding-left:20px;"><input type="button" onClick="saveMb();" value="保存修改" class="buttonStyle" /> </td>
					</tr>
				</table>
			</div>
			
	</body>
</html>
