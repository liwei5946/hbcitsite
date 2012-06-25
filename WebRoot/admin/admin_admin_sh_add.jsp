<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="checkuser.jsp"%>
<html>
	<head>
		<title>添加审核员</title>
		<link href="css/subcss.css" type="text/css" rel="stylesheet" />
		<script Language="JavaScript" Type="text/javascript">
<!--
function FrontPage_Form1_Validator(theForm)
{

  if (theForm.username.value == "")
  {
    alert("请在 用户名 域中输入值。");
    theForm.username.focus();
    return (false);
  }

  if (theForm.username.value.length < 2)
  {
    alert("在 用户名 域中，请至少输入 2 个字符。");
    theForm.username.focus();
    return (false);
  }

if (theForm.password.value=="")
	{
		alert("密码不能为空");
		theForm.password.focus();
		return (false);
	}
	if (theForm.password.value.length <5)
	{
		alert("密码不能少于5个字符");
		theForm.password.focus();
		return (false);
	}
  return (true);
}
-->
</script>
	</head>
	<body style="margin-top:30px">
		<form method="POST" action="admin_admin_sh_add_save.jsp"
			onsubmit="return FrontPage_Form1_Validator(this)"
			 name="FrontPage_Form1">
			<div align="center">
				<p>
				</p>
				<table border="1" width="600" id="table1" cellspacing="0"
					cellpadding="7" style="border-collapse: collapse"
					bordercolor="#C0C0C0">
					<tr>
						<td width="127" align="center">
							用户名
						</td>
						<td>
							&nbsp;
							<input type="text" value="" name="username" size="36"
								maxlength="10">
						</td>
					</tr>
					<tr>
						<td width="127" align="center">
							密 码
						</td>
						<td>
							&nbsp;
							<input type="text" name="password" size="36" maxlength="20">
						</td>
					</tr>

					<tr>
						<td width="127" align="center">
							部 门
						</td>
						<td>
							&nbsp;
							<input type="text" name="bumen" size="36" maxlength="20">
						</td>
					</tr>
					<tr>
						<td width="127" align="center">
							姓 名
						</td>
						<td>
							&nbsp;
							<input type="text" name="xingming" size="36" maxlength="20">
						</td>
					</tr>
					<tr>
						<td width="127" align="center">
							二级栏目权限
							<br>
							(仅添加文章时有效)
						</td>
						<td>
							<!-- 添加栏目权限选择 -->
							添加栏目权限选择
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