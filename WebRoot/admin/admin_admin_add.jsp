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
		function alterpws()
		{
			var name=$('#username').val();
			var password=$('#password').val();
			var bumen=$('#bumen').val();
			var xingming=$('#xingming').val();
			var manager;
			//
			if(username.length==0)
			{
				Dialog.alert("用户名不能为空!");
				return false;
			}
			//
			if(password.length==0)
			{
				Dialog.alert("口令不能为空!");
				return false;
			}
			//
			if(bumen.length==0)
			{
				Dialog.alert("部门不能为空!");
				return false;
			}
			//
			if(xingming.length==0)
			{
				xingming="";
			}
			//
			if($("#manager").attr('checked')==true) //判断是否已经打勾 
			{
				manager=1;
			}
			else
			{
				manager=2;
			}

			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/UserAddServlet",
					type : 'post',
				    data : 'name='+name+'&password='+password+'&bumen='+bumen+'&xingming='+xingming+'&manager='+manager,
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
				<table border="1" width="300" id="table1" cellspacing="0"
					cellpadding="7" style="border-collapse: collapse"
					bordercolor="#C0C0C0">
					<tr>
						<td width="127" align="center">
							用户名
						</td>
						<td>
							&nbsp;
							<input type="text" value="" name="username" id="username" size="36" maxlength="10">
						</td>
					</tr>
					<tr>
						<td width="127" align="center">
							密 码
						</td>
						<td>
							&nbsp;
							<input type="password" name="password" id="password" disabled size="10" maxlength="20" value="000000">(默认密码:000000)
						</td>
					</tr>
					<tr>
						<td width="127" align="center">
							部 门
						</td>
						<td>
							&nbsp;
							<input type="text" name="bumen" id="bumen" size="36" maxlength="20">
						</td>
					</tr>
					<tr>
						<td width="127" align="center">
							姓 名
						</td>
						<td>
							&nbsp;
							<input type="text" name="xingming" id="xingming" size="36" maxlength="20">
						</td>
					</tr>
					<tr>
						
						<td colspan="2" align="left" style="padding-left:20px;"><input type="checkbox" name="manager" id="manager" value="45">审核员&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onClick="alterpws()" value="保存修改" class="buttonStyle" /> </td>
					</tr>
				</table>
			</div>
			
	</body>
</html>
