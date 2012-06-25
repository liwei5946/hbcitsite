<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ include file="checkuser.jsp"%>
<%--
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>添加新闻</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${pageContext.request.contextPath }/admin/css/subcss.css" type="text/css" rel="stylesheet" />
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDrag.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDialog.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/admin/My97DatePicker/WdatePicker.js"></script>
    <script language="javascript">
	<!--
	function getmsg(){
	<% 
		if(session!=null){
			if(session.getAttribute("msg")!=null){
			%>
				Dialog.alert("${sessionScope.msg}");
			<%
				session.removeAttribute("msg");
			}
		}
	%>
	}
	
    function submitcheck(){
			var title=$('#title').val();
			var laiyuan=$('#laiyuan').val();
			var zz=$('#zz').val();
			var mytime=$('#time').val();
			var mylm=$('#lm').val();
			var hp=$('#homepagepic').val();
			var checkhp;
			//
			if(title.length==0)
			{
				Dialog.alert("文章标题不能为空!");
				return false;
			}
			//
			if(laiyuan.length==0)
			{
				Dialog.alert("来源部门不能为空!");
				return false;
			}
			//
			if(zz.length==0)
			{
				Dialog.alert("作者不能为空!");
				return false;
			}
			//
			if(mytime.length==0)
			{
				Dialog.alert("发布日期不能为空!");
				return false;
			}
			//
			if(mylm=="0")
			{
				Dialog.alert("请选择栏目!");
				return false;
			}
			if(!(hp==null)&&!(hp=="")){
				checkhp=hp.substring(hp.length-3,hp.length);
				checkhp=checkhp.toLowerCase();
				if(!(checkhp=="jpg")){
					Dialog.alert("首页新闻轮转只支持‘JPG’格式图片，而您选择的文件是‘"+checkhp+"’格式的，请选择其他文件或转换文件格式。");
					return false;
				}
			}
			
			
	}
	//
	function checkoutlm()
	{
		var lm=$("#outlm").val();
		$.ajax({
					url :"${pageContext.request.contextPath }/servlet/IndexBanKuaiSetServlet",
					type : 'get',
				    data : 'lm='+lm+'&conditions=4',
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="error")
							{
								Dialog.alert("您选择的栏目没有设置,请与管理员联系!");
								$("#outlm").val("0");
							}
						}
					});	
	}
	//
	function checkinnerlm()
	{
		var lm=$("#innerlm").val();
		$.ajax({
					url :"${pageContext.request.contextPath }/servlet/IndexBanKuaiSetServlet",
					type : 'get',
				    data : 'lm='+lm+'&conditions=4',
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="error")
							{
								Dialog.alert("您选择的栏目没有设置,请与管理员联系!");
								$("#innerlm").val("0");
							}
						}
					});	
	}
	//
	function timeSet(){
		var today=new Date();
		date=today.getDate();
		month=today.getMonth();
		month=month+1;
		if(date<=9)
		{
			date="0"+date;
		}
		if(month<=9)
		{
			month="0"+month;
		}
		year=today.getFullYear();
		var nowDate=year+'-'+month+'-'+date;
	   	$("#time").val(nowDate);
	}
    //-->
    </script>
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>

  </head>
  
 <body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" bgcolor="#EFEFDE" >
<div align="center">
<table border="0" width="760" id="table1" cellspacing="0" cellpadding="0" >
	<tr>
		<td>
		<div align="center">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" id="table3">
				<tr>
					<td>
					　</td>
					<td>　</td>
					<td >
					<p align="right">
					　</td>
				</tr>
			</table>
		</div>
		</td>
	</tr>
	<tr>
		<td  align=center>
<form onSubmit="return submitcheck();"  method="POST" action="${pageContext.request.contextPath}/servlet/NewsAddServlet" name="newsadd">
			<table border="0" width="753" id="table2" cellpadding="5" style="border-collapse: collapse" bordercolor="#80ABF4">
				<tr>
					<td width="12%" align="center">内容标题</td>
					<td width="48%">
					&nbsp;<input type="text" name="title" id="title" size="48" maxlength="100" value='' onClick="timeSet();"><span class="STYLE1">*必填</span></td>
					<td width="45%">
					
											<select size="1" name="titlecolor">
												<option value="">默认颜色</option>
												<OPTION value="#000000" style="background-color: #000000"></OPTION>
												<OPTION value="#FFFFFF" style="background-color: #FFFFFF"></OPTION>
												<OPTION value="#008000" style="background-color: #008000"></OPTION>
												<OPTION value="#800000" style="background-color: #800000"></OPTION>
												<OPTION value="#808000" style="background-color: #808000"></OPTION>
												<OPTION value="#000080" style="background-color: #000080"></OPTION>
												<OPTION value="#800080" style="background-color: #800080"></OPTION>
												<OPTION value="#808080" style="background-color: #808080"></OPTION>
												<OPTION value="#FFFF00" style="background-color: #FFFF00"></OPTION>
												<OPTION value="#00FF00" style="background-color: #00FF00"></OPTION>
												<OPTION value="#00FFFF" style="background-color: #00FFFF"></OPTION>
												<OPTION value="#FF00FF" style="background-color: #FF00FF"></OPTION>
												<OPTION value="#FF0000" style="background-color: #FF0000"></OPTION>
												<OPTION value="#0000FF" style="background-color: #0000FF"></OPTION>
												<OPTION value="#008080" style="background-color: #008080"></OPTION>
											</select>		</td>
			  </tr>
				<tr>
					<td width="12%" align="center">副标题</td>
					<td width="48%">&nbsp;<input type="text" name="htitle" size="48" maxlength="100" value=''></td>
					<td width="45%"></td>
			  </tr>
              <tr>
					<td width="12%" align="center">发布去向</td>
					<td colspan="2">
                    <input name="newstarget" type="radio" value="2" checked>仅发布到内网&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="newstarget" type="radio" value="1">仅发布到外网&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="newstarget" type="radio" value="0">同时发布到内网和外网
			    </td>
			  </tr>
              
              <tr>
					<td width="12%" align="center">栏目选择</td>
					<td colspan="2">外网栏目&nbsp;
  <select size="1" name="outlm" id="outlm" onChange="checkoutlm();">
    <option value="0">请选择</option>
    <c:forEach items="${currentUserright}" var="right">
      <c:if test="${right.lmLevel eq 1}">
        <option value=${right.rightName}>${right.rightName}</option>
        <c:forEach items="${currentUserright}" var="right1">
          <c:if test="${(right1.pRightID eq right.rightID) && (right1.lmLevel eq 2)}">
            <option value=${right1.rightName}>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${right1.rightName}</option>	
            <c:forEach items="${currentUserright}" var="right2">
              <c:if test="${(right2.pRightID eq right1.rightID) && (right2.lmLevel eq 3)}">
                <option value=${right2.rightName}>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${right2.rightName}</option>
                </c:if>
              </c:forEach>
            </c:if>
          </c:forEach>
        </c:if>
      </c:forEach>
    
  </select>
  <!--span class="STYLE1">*必填</span-->
					  &nbsp;&nbsp;&nbsp;&nbsp;
                      内网栏目&nbsp;
		<select size="1" name="innerlm" id="innerlm" onChange="checkinnerlm();">
					    <option value="0">请选择</option>
					    <c:forEach items="${currentUserright}" var="right">
					      <c:if test="${right.lmLevel eq 1}">
					        <option value=${right.rightName}>${right.rightName}</option>
					        <c:forEach items="${currentUserright}" var="right1">
					          <c:if test="${(right1.pRightID eq right.rightID) && (right1.lmLevel eq 2)}">
					            <option value=${right1.rightName}>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${right1.rightName}</option>	
					            <c:forEach items="${currentUserright}" var="right2">
					              <c:if test="${(right2.pRightID eq right1.rightID) && (right2.lmLevel eq 3)}">
					                <option value=${right2.rightName}>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${right2.rightName}</option>
					                </c:if>
					              </c:forEach>
					            </c:if>
					          </c:forEach>
					        </c:if>
				        </c:forEach>
					    
  </select>
&nbsp;			    </td>
			  </tr>
              
				<tr>
					<td colspan="3">
					<!-- 此处是文件编辑器 -->

<!-- form action="result.jsp" method="post"-->
		<FCK:editor instanceName="content" height="400pt">
   			<jsp:attribute name="value">
   			请输入内容
   			
   			</jsp:attribute>
		</FCK:editor>       
	


   <!-- input type="submit" value="提交"/-->
   <!-- input type="reset" value="重置"/-->   
<!-- /form-->

					
					
	<!-- 2.3旧版FCKeditor -->
	<!-- >FCK:editor id="content" basePath="FCKeditor/" height="500"
        imageBrowserURL="../filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector"
        linkBrowserURL="../filemanager/browser/default/browser.html?Connector=connectors/jsp/connector"
        flashBrowserURL="../filemanager/browser/default/browser.html?Type=Flash&Connector=connectors/jsp/connector"
        imageUploadURL="../filemanager/upload/simpleuploader?Type=Image"
        linkUploadURL="../filemanager/upload/simpleuploader?Type=File"
        flashUploadURL="../filemanager/upload/simpleuploader?Type=Flash"
        toolbarSet="Default"-->
             <!-- 请输入文章内容 --> 
	<!--/FCK:editor-->					</td>
				</tr>
				<tr>
					<td colspan="2">
					
					&nbsp;<!--input type="checkbox" name="tuwen" value="1" id="tu"><label for=tu>图片新闻</label-->
					<!--input readonly type="checkbox" name="tj"  value="0" id="tj"><label for=tj>推荐新闻</label-->　
					<!--input readonly type="checkbox" name="ontop" value="0" id="ontop"><label for=ontop>置顶</label-->
					<!--input readonly type="checkbox"  name="html" value="1" id="html"><label for=html>生成静态页</label-->							</td>
					<td width="45%">&nbsp;</td>
			  </tr>
				<tr>
					<td width="12%" align="center"><div align="left">阅读数：</div></td>
					<td width="48%">&nbsp;<input type="text" name="hit" size="20" value="0" maxlength="9"></td>
					<td width="45%">　</td>
			  </tr>
				<c:forEach items="${admin}" var="admin">
                    
	               	<tr>
						<td width="12%" align="center"><div align="left">来源部门：</div></td>
						<c:if test="${admin.user=='admin'}">
							<td colspan="2">&nbsp;<input type="text" name="laiyuan" id="laiyuan" size="14" maxlength="40" value="${admin.user}">&nbsp;<span class="STYLE1">*发布者所属的部门</span></td>
				  		</c:if>
				  		<c:if test="${admin.user!='admin'}">
							<td colspan="2">&nbsp;<input type="text" name="laiyuan" id="laiyuan" size="14" maxlength="40" value="${admin.bumen}">&nbsp;<span class="STYLE1">*发布者所属的部门</span></td>
				  		</c:if>
				  	</tr>
	                 <tr>
						<td width="12%" align="center"><div align="left">文章作者：</div></td>
						<td colspan="2">&nbsp;<input type="text" name="zz" id="zz" size="18" value="${admin.user}" maxlength="50">&nbsp;<span class="STYLE1">*请添加真实姓名</span></td>
				  	</tr> 
                </c:forEach>
                 <tr>
                 <!-- alert syj 11-11-10  -->
                              
				<td width="12%" align="center"><div align="left">发布日期：</div></td>
				<td colspan="2">&nbsp;<input readonly id="time" type="text" name="time" size="18" value="" maxlength="50">
                <img onClick="WdatePicker({el:'time'})" src="${pageContext.request.contextPath }/admin/My97DatePicker/skin/datePicker.gif"width="16" height="22" align="middle">
                &nbsp;<span class="STYLE1">*请选择日期</span></td>
			  </tr> 
<tr>
					<td width="12%">
				  <p align="left">图片地址：</td>
					<td colspan="2">
					&nbsp;<input id="homepagepic" type="text" name="pic" size="47" value="">&nbsp;此地址将做为图片新闻的缩略图。</td>
</tr>		

<tr>
					<td width="12%">
				  <p align="left">相关内容：</td>
					<td width="48%">
					&nbsp;<input type="text" name="xgnews" size="20" value="">&nbsp;填写关键字</td>
					<td width="45%">
						<!-- syj 2011-11-10 -->
						<c:forEach items="${admin}" var="admin">
							<c:if test="${admin.dj=='1'}">
								<input type="hidden" name="sh" value="1">　
							</c:if>
							<c:if test="${admin.dj=='2'}">
								<input type="hidden" name="sh" value="1">　
							</c:if>
							<c:if test="${admin.dj=='3'}">
								<input type="hidden" name="sh" value="0">　
							</c:if>
						 </c:forEach>
						<input type="hidden" name="ztid" value="0">
						<input type="hidden" name="updat" value="0">
                        
						<input type="hidden" name="adduser" value="${sessionScope.userName}">
                        
						<input type="hidden" name="shUsername" value="nobody">
						<input type="hidden" name="googlemap" value="0">
						<input type="hidden" name="filename" value="0">
						<input type="hidden" name="updat" value="0">
						<input type="hidden" name="piczz" value="0">					</td>
			  </tr>

<tr>
					<td width="12%" align="center"><div align="left">跳转URL：</div></td>
					<td colspan="2">&nbsp;<input type="text" name="URL" size="47" value="">&nbsp;填写网址,自动打开此URL。不显示具体内容</td>
</tr>
		  
			
				
				</table>
		  <p align="center"><input type="submit" value="　保　存　" name="mysubmit">　　　<input type="reset" value="　重　置　" name="B2"></p>
		</form>
		<p>　</td>
	</tr>
</table>

</div>

</body>

</html>
