<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="checkuser.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css"> /*注意选择器的层叠关系*/
		 
	 	 .font {TEXT-ALIGN: center;COLOR: blue;FONT-SIZE: 16px; FONT-WEIGHT: bold;}
	 	 
	 	 	.buttonStyle{width:64px;height:22px;line-height:22px;color:#369;text-align:center;background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left top;border:0;font-size:12px;}
			.buttonStyle:hover{background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left -23px;}
	 	 
	 	</style>
		<link href="${pageContext.request.contextPath }/admin/css/subcss.css" rel="stylesheet" />
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDrag.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/My97DatePicker/WdatePicker.js"></script>
	</head>
	<script language="JavaScript">
	<!--
		function createExcel(st,et){ 
		    window.location.href="${pageContext.request.contextPath }/servlet/ExportExcelServlet?st="+st+"&et="+et; 
		  } 
		//
		function query()
		{
			
			var tab="<table border='1' width='100%' id='table2' cellpadding='3'	style='border-collapse: collapse' cellspacing='0' bordercolor='#CCCCCC'  class='stripe_tb'><tr style='align:center;FONT-WEIGHT: bold;FONT-SIZE: 14px;'><td>部门名称</td><td>内容数量</td></tr>";
			
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/NewsBumenCountServlet",
					type : 'get',
					async:false,
					dataType:'json',
				    data : 'st='+$('#st').val()+'&et='+$('#et').val(),
					success :function(mm){
							
							
							var stuArr = mm.couns;
							//
							var row=1;
							for(var i in stuArr){
								if(stuArr[i].bm!=null)
								{
									
									if((row % 2)==0)
									{
										
										tab=tab+"<tr ><td background='#ecf6fc'> "+stuArr[i].bm+"</td><td background='#ecf6fc'>"+stuArr[i].counts+"</td></tr>"
									}
									else
									{	
										
										tab=tab+"<tr background='#FEF3D1'><td background='#ecf6fc'>"+stuArr[i].bm+"</td><td background='#ecf6fc'>"+stuArr[i].counts+"</td></tr>"
									}
									row++;
								}
							}
							tab=tab+"</table>";
							//
    						$("#view").html(tab);
					},
					error:function()
					{
						alert("读取失败！！");
					}
			});	
		}
		//
		
		
	//-->
	</script>
	<body topmargin="0" bgcolor="#ffffff" leftmargin="0" rightmargin="0"
		bottommargin="0" marginwidth="0" marginheight="0" 	 >
		
		<div align="center" style="margin-top:0;">
			<table border="0" cellpadding="0" cellspacing="0" width="80%">
				<tr>
					<td>
						<center>
							<table border="0" width="100%" id="table1" height="80"
								cellspacing="0" cellpadding="0">
								<tr>
									<td height=25>
									<div class="title">
										部门发布内容数量统计
										<hr>
									</div>
									</td>
								</tr>
								<tr>
									<td height=25 background="${pageContext.request.contextPath }/admin/images/bj3.jpg">
										<div align="center">
											<table border="0" cellpadding="0" cellspacing="0" width="744"	id="table3">
												<tr>
													<td width="60%" height=20 align=center>
																											
														请选择时段：
														 <input readonly id="st" type="text" name="st" size="18" value="" maxlength="50">
                										 <img onClick="WdatePicker({el:'st'})" src="${pageContext.request.contextPath }/admin/My97DatePicker/skin/datePicker.gif"width="16" height="22" align="middle">

															至
														 <input readonly id="et" type="text" name="et" size="18" value="" maxlength="50">
                										 <img onClick="WdatePicker({el:'et'})" src="${pageContext.request.contextPath }/admin/My97DatePicker/skin/datePicker.gif"width="16" height="22" align="middle">

														<input type="button" value="开始统计" name="B2" class="buttonStyle" onclick="query();">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="/hbcitsite/admin/images/hand_point.png" />&nbsp;&nbsp;<a href="#"  onclick="createExcel($('#st').val(),$('#et').val());">生成Excel</a>
													</td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
							<div align="center" id="view">
							</div>
						</center>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
