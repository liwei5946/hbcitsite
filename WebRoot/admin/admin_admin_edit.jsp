<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="checkuser.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户权限编辑</title>
		<link href="css/subcss.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		<form method="POST" action="{pageContext.request.contextPath }/admin/admin_admin_edit_save.jsp"
			onsubmit="return FrontPage_Form1_Validator(this)" name="FrontPage_Form1">
			<div align="center">
				<p>
					<a href=javascript:history.back();>返回</a>
				</p>
				<table border="1" width="600" id="table1" cellspacing="0"
					cellpadding="7" style="border-collapse: collapse"
					bordercolor="#C0C0C0">
					<tr>
						<td align="center" colspan="2">
							设置<!--用户名称 -->栏目
						</td>
					</tr>
					<tr>
						<td width="127" align="center">
							以有栏目权限
						</td>
						<td>

							编号是:</td>
					</tr>
					<tr>
						<td width="127" align="center">
							栏 目
						</td>
						<td align="left">

						  <!--添加栏目选择 -->
				           <input  type=checkbox name=lmid value="70" id="">
				           	<label for="">校园快讯<font color=999999>(编号:70)</font></label><BR>
				       
						</td>
					</tr>
				</table>
			</div>
			<p align="center">
				<input type="submit" value="提交" name="B1">
				<input type="reset" value="重置" name="B2">
			</p>
		</form>
	</body>
</html>