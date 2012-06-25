<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>后台管理登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<STYLE type="text/css">
	<!--
		a:link       {text-decoration: none; font-family: AdobeSm; color: #000000 }
		a:visited    {text-decoration: none; color: #000000 }
		A:hover      {COLOR: green; FONT-FAMILY: "宋体,MingLiU"; TEXT-DECORATION: underline}
		body         {font-size: 9pt; font-family: 宋体,MingLiU, Arial;color: #000000}
		TD           {FONT-SIZE: 9pt; FONT-FAMILY: "宋体,MingLiU, Arial";color: #000000;table-layout:fixed;word-break:break-all}
		p            {FONT-SIZE: 9pt; FONT-FAMILY: "宋体,MingLiU, Arial";color: #000000}
		input        {FONT-SIZE: 9pt; FONT-FAMILY: "宋体,MingLiU, Arial";color: #000000}
		body         {margin-top: 0; margin-bottom: 0;margin-left:0;margin-right:0; color: #000000}
		select       {FONT-SIZE: 9PT;}
		option       {FONT-SIZE: 9pt;}
		textarea     {FONT-SIZE: 9pt;}
-->
</STYLE>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js"></script>
	<script type="text/javascript">
		
		function changeImage(obj)
		{
			//获取当前的时间作为参数，无具体意义    
			var timenow = new Date().getTime();    
			//每次请求需要一个不同的参数，否则可能会返回同样的验证码    
			//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。    
			obj.src="/hbcitsite/servlet/ImageServlet?d="+timenow;   
		}
		//
		
		function check()
		{
			var uname =$('#user').val();
			if(uname.length==0 ){
				alert("用户名不能为空！！");
				return true;
			}
			
			var password =$('#pass').val();
			if(password.length==0){
				alert("密码不能为空！！");
				return false;
			}
		
			var valiCode =$('#XuasYzm').val();
			if(valiCode.length==0 ){
				alert("验证码不能为空！！");
				return false;
			}

			var systemCode;
			$.ajax({
			url :"/hbcitsite/servlet/LoginServlet",
			async:false,
			success :function(mm){
					systemCode=mm.replace(/\r\n/g,'');
				}
			});	
			alert("shurude ："+valiCode+" "+"session里的   "+systemCode);
			//
			if(valiCode!=systemCode)
			{
				alert("验证码输入错误,请重新输入!!");
				return;
			}
			
			//alert("马上调用form");
			//document.FrontPage_Form1.action="/hbcitsite/servlet/GotoMainWeb";
			
			//document.FrontPage_Form1.submit();
			return true;
		}
	</script>
  </head>
  
  <body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">

<form method="POST" action="${pageContext.request.contextPath }/servlet/GotoMainWeb"  name="FrontPage_Form1" id="FrontPage_Form1" >
<div align="center">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" id="table9" >
		<tr>
			<td style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">
			<div align="center">
				<table border="0" cellpadding="0" cellspacing="0"  background="${pageContext.request.contextPath }/admin/images/admin_login.jpg" width="563" height="364" id="table10">
					<tr>
						<td valign="top" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">
						<div align="center">
							<div align="center">
								<table border="0" cellpadding="0" cellspacing="0" width="100%" id="table11">
									<tr>
										<td width="207" height="130" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">　</td>
										<td height="130" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">		
											
										</td>
										<td width="37" height="130" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">　</td>
									</tr>
									<tr>
										<td width="207" height="111" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">　</td>
										<td height="111" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">
										
										<table border="0" cellpadding="5" cellspacing="0" width="319" id="table12">
											<tr>
												<td width="114" align="center" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">
												用户名</td>
												<td width="205" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">
												&nbsp;<!--webbot bot="Validation" s-display-name="用户名" b-value-required="TRUE" i-minimum-length="2" i-maximum-length="20" -->
												<input type="text" id="user" name="user" size="24" maxlength="20" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000"></td>
											</tr>
											<tr>
												<td width="114" align="center" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">
												密　码</td>
												<td width="205" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">
												&nbsp;<!--webbot bot="Validation" s-display-name="密码" b-value-required="TRUE" i-minimum-length="5" i-maximum-length="20" -->
												<input type="password" id="pass" name="pass" size="24" maxlength="20" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000"></td>
											</tr>
											<tr>
												<td width="114" align="center" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">
												验证码</td>
												<td width="205" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">
												&nbsp;<!--webbot bot="Validation" s-display-name="验证码" s-data-type="Integer" s-number-separators="x" b-value-required="TRUE" i-minimum-length="5" i-maximum-length="5" -->
												<input name="XuasYzm" type="text" id="XuasYzm" size="9" maxlength="5" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000">	
												<img id="img1"  name="img1" src="/hbcitsite/servlet/ImageServlet"  title="点击图片刷新验证码" onclick="changeImage(this);" style="cursor:pointer;"/>
												
												</td>
											</tr>
											</table>
										</td>
										<td width="37" height="111" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">　</td>
									</tr>
									<tr>
										<td width="207" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">　</td>
										<td style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">　</td>
										<td width="37" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">　</td>
									</tr>
									<tr>
										<td width="207" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">　</td>
										<td style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">
										<p align="center">
											<!--  <input type="image" src="${pageContext.request.contextPath }admin/images/login001.jpg" value="提交" name="B1" id="B1" onclick="check()">&nbsp;
											<a href="#" style="text-decoration: none; font-family: AdobeSm; color: #000000"  onclick="check();"><img  src="admin/images/login001.jpg"  name="B1" id="B1" >&nbsp;</a>-->
											<input type="submit" value="提交"/>
											
											<a href="#" style="text-decoration: none; font-family: AdobeSm; color: #000000">
											<img border="0" src="${pageContext.request.contextPath }/admin/images/login001.jpg" width="72" height="21" id="B1" name="B1" onClick="check()" ></a>
											<a href="#" onClick="window.opener=null;window.close()" style="text-decoration: none; font-family: AdobeSm; color: #000000">
											<img border="0" src="${pageContext.request.contextPath }/admin/images/login002.jpg" width="72" height="21" id="B2"></a></td>
										<td width="37" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">　</td>
									</tr>
									<tr>
										<td width="207" height="56" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">　</td>
										<td height="56" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">　</td>
										<td width="37" height="56" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">　</td>
									</tr>
									<tr>
										<td width="207" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">&nbsp;　<font color="#FFBEC6"><!--year(now())&"-"&Month(now())&"-"&day(now())&" "&hour(time())&":"&Minute(now())&":"&Second(now())--></font></td>
										<td style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all"><span style="letter-spacing: -1pt">　　　　　　&nbsp;&nbsp;&nbsp;</span>　　　　　　　&nbsp;&nbsp;　 &nbsp;</td>
										<td width="37" style="font-size: 9pt; font-family: 宋体,MingLiU, Arial; color: #000000; table-layout: fixed; word-break: break-all">　</td>
									</tr>
								</table>
							</div>
						</div>
						</td>
					</tr>
				</table>
			</div>
			</td>
		</tr>
	</table>
</div>
</form>

</body>

</html>
