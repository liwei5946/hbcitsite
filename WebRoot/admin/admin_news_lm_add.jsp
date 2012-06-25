<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="checkuser.jsp"%>
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

		function fun1()
		{
		}
		//
		function fun2()
		{
			parentDialog.close();
		}
		//
		function saveLm()
		{
			var lmname=$('#LmName').val();
			//
			if(lmname.length==0)
			{
				Dialog.alert("栏目名不能为空!");
				return false;
			}
			//
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/LmAddServlet",
					type : 'get',
				    data : 'id='+$('#id').val()+'&LmName='+$('#LmName').val()+'&LmLevel='+$('#LmLevel').val(),
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								Dialog.alert("添加成功!",function(){window.location.reload();});
							}
							else
								Dialog.alert("添加失败!");
						}
					});	
		}
//-->
</script>
	<body style="margin-top:30px">
			<div align="center">
				<p>
				</p>
				<table  width="100%" id="table1" cellspacing="0"
					cellpadding="7" style="border-collapse: collapse"
					bordercolor="#C0C0C0">
					<tr>
						<td width="100" align="center">
							栏目名称：
						</td>
						<td>
							
							<input type="text" value="" name="LmName" id="LmName" size="36" maxlength="10">
							<input type="hidden" value="<%=request.getParameter("id")%>" name="id" id="id" >
							<input type="hidden" value="<%=request.getParameter("LmLevel")%>" name="LmLevel" id="LmLevel" >
						</td>
					</tr>
					<tr>
						<td  align="center" colspan="2">
							<input type="button" value="保存栏目" name="save" id="save" onclick="saveLm();" class="buttonStyle">
						</td>
					</tr>
				</table>
			</div>

	</body>
</html>
