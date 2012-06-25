<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"   pageEncoding="utf-8"%>
<%@ include file="checkuser.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath }/admin/css/subcss.css" type="text/css" rel="stylesheet" />
	
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDrag.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDialog.js"></script>
	<script language="JavaScript">
		<!--
		$(document).ready(function(){
			
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
			
		});
		//alter password
		function alterpass(userid)
		{
			
			var diag = new Dialog();
			diag.Top =20;
			diag.Width = 350;
			diag.Height = 140;
			diag.Title = "修改用户"+$("#"+userid).val()+"口令";
			diag.URL = "/hbcitsite/admin/admin_alter_password.jsp?userid="+userid+"&allInfo="+$("#"+userid).val();
			diag.show();
		}
		//add user
		function adduser()
		{
			var diag = new Dialog();
			diag.Top =20;
			diag.Width = 350;
			diag.Height = 250;
			diag.Title = "增加系统用户";
			diag.URL = "/hbcitsite/admin/admin_admin_add.jsp";
			diag.OKEvent = function(){
				window.location.reload();
				//diag.close();
			};
			
			diag.ShowCloseButton=false;
			diag.MessageTitle = "设置提示：";
			diag.Message = "默认增加的是系统录入人员，如果增加审核人员，请在保存前选择上‘审核员’选择框。新增用户默认密码是六个零";
			diag.show();
			diag.okButton.value="结果刷新";
			diag.cancelButton.value="关闭";
		}
		//alter user
		function alteruser(userid)
		{
			var diag = new Dialog();
			diag.Top =20;
			diag.Width = 350;
			diag.Height = 250;
			diag.Title = "修改系统用户信息";
			diag.URL = "/hbcitsite/admin/admin_alteruser.jsp?userid="+userid;
			diag.OKEvent = function(){
				window.location.reload();
				//diag.close();
			};
			
			diag.ShowCloseButton=false;
			diag.MessageTitle = "修改提示：";
			diag.Message = "修改完成后，请不要忘记“保存修改”后再关闭窗口";
			diag.show();
			diag.okButton.value="结果刷新";
			diag.cancelButton.value="关闭";
		}
		//
		function shows(userid)
		{
			var diag = new Dialog();
			diag.Title = "系统用户授权";
			diag.Top =20;
			diag.Width = 300;
			diag.Height = 500;
			diag.URL = "/hbcitsite/admin/admin_UserRights.jsp?userid="+userid;
			diag.MessageTitle = "当前授权用户："+$("#"+userid).val();
			diag.Message = "操作提示：选择或取消选择某一权限，然后关闭窗口，保存数据";
			diag.ShowButtonRow=true;
			diag.OKEvent = function(){
				Dialog.alert("保存成功!",function(){window.location.reload();});
				diag.close();
			};
			diag.show();
		}
		/*
		function showDatas(userid){
		   	var re=window.showModalDialog('/hbcitsite/admin/admin_UserRights.jsp?userid='+userid,'newwin', 'dialogHeight:460px;dialogWidth:200px;unadorne:yes;edge:Raised;location:no;menubar:no;toolbar:no;center:Yes;help:Yes;resizable:Yes;status:Yes;');
			window.location.href=window.location.href;
		 }
		*/
		function deleteid(id)
		{
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/UserDeleteServlet",
					type : 'post',
				    data : 'id='+id+'&Condition=1',
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								Dialog.alert("删除成功!",function(){window.location.reload();});
							}
							else
								Dialog.alert("删除失败!",function(){window.location.reload();});
						}
					});	
		}
		//-->
	</script>
</head>
<body style="margin-top:10px">


<div class="title">
中心用户维护
<hr>
</div>

<div align="center">
	<table border="1" width="100%" id="table3" class="stripe_tb" 
		style="border-collapse: collapse" bordercolor="#C0C0C0"
		cellspacing="0" cellpadding="3">
		<tr>
			<td colspan="3" background="/hbcitsite/admin/images/topBar_bg.gif">
				<font face="Wingdings"> 1</font><b>所有管理员 </b>(
				<a href="#" onClick="adduser();">增加系统用户</a>)
				<!--  (<a href="${pageContext.request.contextPath }/admin/admin_stat_user.jsp">查看用户文章排名</a>)  -->
			</td>
		</tr>
		
	<c:forEach items="${adminList}" var="user">
		<tr background="">
			<td width="30%">${user.user}		  
				 <c:if test="${user.dj == 3}"> <font color=red>[录入员]</font></c:if>
				 <c:if test="${user.dj == 2}"><font color=blue>[审核员]</font></c:if>
				 <font color=0000cc></font>${user.bumen}
				 <font color=dd00dd></font>${user.username}			
			</td>
			<td width="45%" align=center>	
				${user.lmid}
			</td>
			<td width="25%" align=center>			
				<!-- <a href="${pageContext.request.contextPath }/admin/admin_admin_edit.jsp" onclick="javascript:window.showModalDialog('${pageContext.request.contextPath }/admin/admin_UserRights.jsp','newwin', 'dialogHeight:360px;dialogWidth:300px;unadorne:yes;edge:Raised;location:no;menubar:no;toolbar:no;center:Yes;help:Yes;resizable:Yes;status:Yes;');"
				>栏目</a> -->
				<!--  <a href="#" onclick="javascript:window.showModalDialog('${pageContext.request.contextPath }/admin/admin_UserRights.jsp?userid=${user.id}&username=${user.user}','newwin', 'dialogHeight:460px;dialogWidth:200px;unadorne:yes;edge:Raised;location:no;menubar:no;toolbar:no;center:Yes;help:Yes;resizable:Yes;status:Yes;');"
				>栏目</a>
				-->
				<input type="hidden" id="${user.id}" value="${user.user}(${user.bumen})">
				<img src="/hbcitsite/admin/images/lmedit.jpg" /><a href="#" onClick="shows(${user.id});">栏目</a>
				<!-- <a href="${pageContext.request.contextPath }/admin/admin_admin_editpass.jsp">密码</a> -->
				<img src="/hbcitsite/admin/images/pwsedit.jpg" /><a href="#" onClick="alterpass(${user.id});">密码</a> 
				<!--  <a	onclick='{if(confirm("您确定删除吗?此操作将不能恢复!")){deleteid(${user.id});}}'	href="#">删除</a>  -->
				<img src="/hbcitsite/admin/images/edituser.jpg" /><a	onclick="alteruser(${user.id});" href="#">修改</a>
				<img src="/hbcitsite/admin/images/deleuser.jpg" /><a	onclick="Dialog.confirm('警告：您确认要删除${user.user}吗？',function(){deleteid(${user.id});});"	href="#">删除</a>
			</td>

		</tr> 
	</c:forEach>
		
	</table>
</div>
</body>
</html>
