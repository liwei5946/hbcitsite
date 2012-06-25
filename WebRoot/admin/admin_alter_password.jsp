<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="checkuser.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
	<script type="text/javascript">
		function fun1()
		{
		}
		//
		function fun2()
		{
			parentDialog.close();
		}
		//
		function alterpws(id)
		{
			var oldpws="";//$('#oldpws').val();
			var newpws1=$('#newpws1').val();
			var newpws2=$('#newpws2').val();
			/*
			if(oldpws.length==0)
			{
				Dialog.alert("原口令不能为空!");
				return false;
			}
			*/
			//
			if(newpws1.length==0)
			{
				Dialog.alert("新口令不能为空!");
				return false;
			}
			//
			if(newpws2.length==0)
			{
				Dialog.alert("重输新口令不能为空!");
				return false;
			}
			//
			if(newpws2!=newpws1)
			{
				Dialog.alert("两次输入的新口令不相同，请重输!");
				return false;
			}
			//
			
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/UserUpdateServlet",
					type : 'post',
				    data : 'id='+id+'&Condition=3&oldpws='+oldpws+'&newpws='+newpws1,
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								Dialog.alert("修改成功!");
							}
							else
								Dialog.alert("修改失败!");
						}
					});	
			
		}
	</script>
  </head>
  
  <body>
    <div id="forlogin">
      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
        <tr>
          <td  width="150" align="right">当前用户名：</td>
          <td >
          	<input type="text" id="name" name="name" value="<%=new String(request.getParameter("allInfo").getBytes("iso-8859-1"),"utf-8")%>" readonly="readonly" class="input_on"/>
          </td>
        </tr>
        <!--  
      
        <tr>
          <td width="150" align="right">原口令：</td>
          <td><input type="password" id="oldpws" name="oldpws"/></td>
        </tr>
          -->
        <tr>
          <td width="150" align="right">新口令：</td>
          <td><input type="password" id="newpws1" name="newpws1" /></td>
        </tr>
        <tr>
          <td align="right">重输新口令:</td>
          <td><input type="password" id="newpws2" name="newpws2" /></td>
        </tr>
        <tr>
          <td colspan="2" align="left" style="padding-left:120px;"><input type="button" onClick="alterpws(<%=request.getParameter("userid")%>)" value="修改" class="buttonStyle" /> <input type="button" value="关闭" onClick="fun2()" class="buttonStyle"   /></td>
        </tr>
      </table>
</div>
  </body>
</html>
