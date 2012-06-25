<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="checkuser.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "  http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 

<html>
  <head>

    <title>新闻修改</title>
    
	<script src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/admin/js/jquery.ui.draggable.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/admin/js/jquery.alerts.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath }/admin/js/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />
	

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/admin/css/subcss.css"/>
	
    <script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDrag.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDialog.js"></script>
	
<style type="text/css">
			
			FIELDSET {
				border: solid 1px #CCC;
				-moz-border-radius: 16px;
				-webkit-border-radius: 16px;
				border-radius: 16px;
				padding: 1em 2em;
				margin: 1em 0em;
			}
			
			LEGEND {
				color: #666;
				font-size: 16px;
				padding: 0em .5em;
			}
			
			PRE {
				font-family: "Courier New", monospace;
				font-size: 11px;
				color: #666;
				background: #F8F8F8;
				padding: 1em;
				-moz-border-radius: 8px;
				-webkit-border-radius: 8px;
				border-radius: 8px;
			}
			
			/* Custom dialog styles */
			#popup_container.style_1 {
				font-family: Georgia, serif;
				color: #A4C6E2;
				background: #005294;
				border-color: #113F66;
			}
			
			#popup_container.style_1 #popup_title {
				color: #FFF;
				font-weight: normal;
				text-align: left;
				background: #76A5CC;
				border: solid 1px #005294;
				padding-left: 1em;
			}
			
			#popup_container.style_1 #popup_content {
				background: none;
			}
			
			#popup_container.style_1 #popup_message {
				padding-left: 0em;
			}
			
			#popup_container.style_1 INPUT[type='button'] {
				border: outset 2px #76A5CC;
				color: #A4C6E2;
				background: #3778AE;
			}
			
	</style>	
    

    <script language="javascript" >
	<!--
	$(document).ready(function(){
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
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
		}); 

	//
		function deleteid(id)
		{
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/NewsDeleteServlet",
					type : 'get',
				    data : 'id='+id,
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								Dialog.alert("删除成功!",function(){location.replace("${pageContext.request.contextPath}/servlet/AdminNewsListServlet?cpage=1");});
							}
							else{
								Dialog.alert("删除失败!",function(){window.location.reload();});
								}
						}
					});	
		}
		
		function tjid(id)
		{
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/NewsTjServlet",
					type : 'get',
				    data : 'id='+id,
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								Dialog.alert("推荐成功!",function(){location.replace("${pageContext.request.contextPath}/servlet/AdminNewsListServlet?cpage=1");});
							}
							else{
								Dialog.alert("推荐失败!",function(){window.location.reload();});
								}
						}
					});	
		}
		
		function ontopid(id)
		{
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/NewsOntopServlet",
					type : 'post',
				    data : 'id='+id,
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								Dialog.alert("置顶成功!",function(){location.replace("${pageContext.request.contextPath}/servlet/AdminNewsListServlet?cpage=1");});
							}
							else{
								Dialog.alert("置顶失败!",function(){window.location.reload();});
								}
						}
					});	
		}
		
		function shid(id,shuser)
		{
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/NewsShServlet",
					type : 'get',
				    data : 'id='+id+'&shuser='+shuser,
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								Dialog.alert("审核成功!",function(){location.replace("${pageContext.request.contextPath}/servlet/AdminNewsListServlet?cpage=1");});
							}
							else{
								Dialog.alert("审核失败!",function(){window.location.reload();});
								}
						}
					});	
		}
		
		function shows(id)
		{
			$.ajax({
				url :"${pageContext.request.contextPath }/servlet/ViewSelectNewToModelPage?condition=1",
				type : 'post',
			    data : 'newsid='+id,
				success :function(mm){
						}
				});	
		}
		//上移1个或n个
		function upAndUpn(n,id)
		{
			if(n==20)
			{
				jPrompt('请输入上移的行数:(不能超过10)', '10', '系统提示', function(r) {
				    if(Number(r)>10)
					{
						alert("你的输入超过最大值！");
						return;
					}
					$.ajax({
						url :"${pageContext.request.contextPath }/servlet/NewsMoveupOrMovedownServlet",
						type : 'get',
						async:false,
					    data : 'n='+r+'&moveType=1&id='+id,//moveType=1--标识上移
						success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								location.replace("${pageContext.request.contextPath}/servlet/AdminNewsListServlet?cpage=1");
							}
							else
								Dialog.alert("上移失败!");
						}
					});	
				});
			}
			//
			if(n==1)
			{
			    $.ajax({
						url :"${pageContext.request.contextPath }/servlet/NewsMoveupOrMovedownServlet",
						type : 'get',
						async:false,
					    data : 'n=1&moveType=1&id='+id,//moveType=1--标识上移
						success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								location.replace("${pageContext.request.contextPath}/servlet/AdminNewsListServlet?cpage=1");
							}
							else
								Dialog.alert("上移失败!");
						}
					});	
			}
		}
		//下移1个或n个
		function downAndDownn(n,id)
		{
			if(n==20)
			{
				jPrompt('请输入下移的行数:(不能超过10)', '10', '系统提示', function(r) {
				     
					if(Number(r)>10)
					{
						alert("你的输入超过最大值！");
						return;
					}
					$.ajax({
						url :"${pageContext.request.contextPath }/servlet/NewsMoveupOrMovedownServlet",
						async:false,
						type : 'get',
					    data : 'n='+r+'&moveType=2&id='+id,//moveType=2--标识下移
						success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								location.replace("${pageContext.request.contextPath}/servlet/AdminNewsListServlet?cpage=1");
							}
							else
								Dialog.alert("下移失败!");
						}
					});	
				});
			}
			//
			if(n==1)
			{
			    $.ajax({
						url :"${pageContext.request.contextPath }/servlet/NewsMoveupOrMovedownServlet",
						async:false,
						type : 'get',
					    data : 'n=1&moveType=2&id='+id,//moveType=2--标识下移
						success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								location.replace("${pageContext.request.contextPath}/servlet/AdminNewsListServlet?cpage=1");
							}
							else
								Dialog.alert("下移失败!");
						}
					});	
			}	
		}
		

		//search news----syj 2011-11-10
		function searchContent()
		{
			
			var diag = new Dialog();
			diag.Width = 440;
			diag.Height = 310;
			diag.Title = "搜索内容";
			diag.URL = "/hbcitsite/admin/admin_news_search.jsp";
			diag.MessageTitle = "搜索提示：";
			diag.Message = "请输入您搜索内容的条件，可部分输入！！";
			diag.show();
			
		}

	//-->
	</script>

  </head>

<body style="margin-top:10px;" bgcolor="#ffffff" >


<div class="title">
内容列表
<hr/>
</div>
<div align="left">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="/hbcitsite/admin/images/search.png" />&nbsp;&nbsp;<a href="#" title="搜索满足条件的内容" onclick="searchContent()">内容搜索</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<span style="color:blue">当前数据状态：
<c:if  test="${sessionScope.title!=null}">
	标题包含：${sessionScope.title}；
</c:if>
<c:if  test="${sessionScope.title==null}">
	全部标题；
</c:if>
<c:if  test="${sessionScope.content!=null}">
	内容包含：${sessionScope.content}；
</c:if>
<c:if  test="${sessionScope.content==null}">
	全部内容；
</c:if>
<c:if  test="${sessionScope.contentType!=null}">
	栏目：${sessionScope.contentType}；
</c:if>
<c:if  test="${sessionScope.contentType==null}">
	全部栏目；
</c:if>
<c:if  test="${sessionScope.Laiyuan!=null}">
	发布部门：${sessionScope.Laiyuan}；
</c:if>
<c:if  test="${sessionScope.Laiyuan==null}">
	所有部门；
</c:if>
<c:if  test="${sessionScope.stime!=null}">
	起始时间：${sessionScope.stime}；
</c:if>
<c:if  test="${sessionScope.etime!=null}">
	终止时间：${sessionScope.etime}；
</c:if>
</span>
</div>

<table border="1" width="100%" id="table3" class="stripe_tb" style="border-collapse: collapse" 	cellspacing="0" cellpadding="3" align="center">
		<tr style="font-weight:bold;" >
			<td align="center" height="10" width="40%"><font color="#000000">[编号]标题(阅读数)</font></td>
			<td align="center" height="10" width="10%"><font color="#000000">发布时间</font></td>
			<td align="center" height="10" width="12%"><font color="#000000">文章状态</font></td>
		    <td height="10" align="center" bgcolor="#D6D6D6" width="13%"><font color="#000000">作者</font></td>
			<td align="center" height="10" width="30%"><font color="#000000">操作</font></td>
		</tr>
        
        <c:forEach var="mynews" items="${sessionScope.adminNewsList}" varStatus="countItem">
        <tr style="line-height:1.5em;">
			<td height="5">
            [${mynews.id}]
            <a href="${pageContext.request.contextPath }/servlet/ViewSelectNewToModelPage?newsid=${mynews.id}&condition=1" target="_blank" title="${mynews.title}">
	      	<c:choose>
		          <c:when test="${fn:length(mynews.title) > 20}">
		              <c:out value="${fn:substring(mynews.title, 0, 20)}..." />
		          </c:when>
	         	  <c:otherwise>
            		<c:out value="${mynews.title}" />
          		  </c:otherwise>
      		</c:choose> 
            </a>
            <font color=999999>(${mynews.hit})</font>
            </td>
			<td align="center" height="5">${fn:substring(mynews.time, 0, 10)}</td>
			<td align="left" height="5">
            <c:if test="${mynews.sh == 0 }">
            	<b><font color="red">待审&nbsp;</font></b>
            </c:if>
            <c:if test="${mynews.ontop == 1}">
            	<b><font color="#0000FF">顶&nbsp;</font></b>
            </c:if>
            <c:if test="${mynews.tj == 1}">
            	<b><font  color="#993300">荐</font></b>
            </c:if>
            </td>
			<td align="center" height="5">${mynews.zz}</td>
			<td align="center" height="5">
				<c:forEach items="${admin}" var="admin">
					<c:if test="${admin.dj == 2 || admin.dj == 1}">
						<a title="推荐此文章" href="#" onclick="Dialog.confirm('提示：您确认要推荐第${mynews.id}号文章吗？',function(){tjid(${mynews.id});});">荐</a>&nbsp; 
						<a href="#" title="将此文章置顶" onclick="Dialog.confirm('提示：您确认要将第${mynews.id}号文章置顶吗？',function(){ontopid(${mynews.id});});">顶</a>&nbsp;
						<!--<a href="?id=" title='放入回收站中'> <img width=16 height=16 border=0 src='images/isdel2.gif'> </a> -->
				   		<a title="审核通过此文章" href="#" onclick="Dialog.confirm('提示：您确认要将第${mynews.id}号文章审核通过吗？',function(){shid(${mynews.id},'${userName}');});">审</a>
				   		<a title="上移1个位置" onclick="upAndUpn(1,${mynews.updat});"	href="#">上</a>
						<a title="上移n个位置" onclick="upAndUpn(20,${mynews.updat});"	href="#">上n</a> 
						<a title="下移1个位置" onclick="downAndDownn(1,${mynews.updat});"	 href="#">下</a>
						<a title="下移n个位置" onclick="downAndDownn(20,${mynews.updat});"	 href="#">下n</a> 
	                </c:if>
				</c:forEach>
				&nbsp;&nbsp;<a href="../servlet/NewsLocateServlet?id=${mynews.id}" title='编辑第${mynews.id}号文章'><span style="color:blue">编辑</span></a> 
			 	<a title="永久删除此文章" onclick="Dialog.confirm('警告：您确认要删除第${mynews.id}号文章吗？',function(){deleteid(${mynews.id});});"	href="#"><span style="color:red">删</span></a>
			 	
			  </td>
		</tr>
         </c:forEach>
	</table>

<br/>
<div align="center">
<c:forEach var="mynews" items="${sessionScope.adminNewsList}" varStatus="countItem" begin="1" end="1" step="1">
共${mynews.totalCount}条&nbsp;&nbsp;
每页显示${mynews.pageSize}条&nbsp;&nbsp;
第${sessionScope.cpage}页&nbsp;&nbsp;
共${mynews.totalPage}页	&nbsp;&nbsp;&nbsp;&nbsp;
     
     <c:if test="${mynews.hasLastPage == true}">
     	<a href="../servlet/AdminNewsListServlet?cpage=1">首页</a>&nbsp;
     	<a href="../servlet/AdminNewsListServlet?cpage=${mynews.lastPage}">上一页</a>&nbsp;
     </c:if>
     <c:if test="${mynews.hasLastPage == false}">
     	<span style="padding:20px;overflow:hidden;word-wrap:break-word;word-break:break:all; font-size:12px; line-height:18px;"><font disabled>
     	首页&nbsp;&nbsp;上一页 &nbsp;</font></span>
     </c:if>
     <c:if test="${mynews.hasNextPage == true}">
     	<a href='../servlet/AdminNewsListServlet?cpage=${mynews.nextPage}'>下一页</a>&nbsp;&nbsp;&nbsp;
     	<a href='../servlet/AdminNewsListServlet?cpage=${mynews.totalPage}'>尾页</a>
     </c:if>
     <c:if test="${mynews.hasNextPage == false}">
     	 <span style="padding:20px;overflow:hidden;word-wrap:break-word;word-break:break:all; font-size:12px; line-height:18px;"><font disabled>
     	 下一页&nbsp;&nbsp;&nbsp;尾页</font></span>
     </c:if>
     
</c:forEach>
</div>



</body>
</html>
