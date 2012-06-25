<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="checkuser.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>系统用户授权</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  <script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDrag.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDialog.js"></script>
	  
	   <link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/zTreeStyle/zTreeStyle.css" type="text/css">
	  <script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDialog.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery.ztree-2.6.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/demoTools.js"></script>
	  <SCRIPT LANGUAGE="JavaScript">
	  /**
		//function   show() 
        //{ 
        	//var title='request.getParameter("username")';
            //self.document.title='设置'+title+'权限'; 
        //} 
		**/
	  <!--
		var zTree1;
		var setting;
		var zNodes;
		setting = {
				checkable: true,
				callback: {
					change:	zTreeOnChange
				}
			};
		
		
		$(document).ready(function(){
			//window.location.reload();
			getData();
			refreshTree();
			
		});
	
		function getData() {  
			$.ajax({
			url :"${pageContext.request.contextPath }/servlet/getUserAllRightsServlet",
			type : 'get',
			contentType: "application/json; charset=utf-8", 
			async:false,
			dataType:'json',
			data:'userid='+<%=request.getParameter("userid")%>,
			success :function(mm){
					zNodes=mm;
				},
			error:function(xhr,status,errorThrown){
					Dialog.alert("获取数据失败!");
					
					}
			});	 
		}
		function zTreeOnChange(event, treeId, treeNode) {
			getCheckedNodesLength();
		}
	
		function getCheckBoxType() {
			var py = $("#py").attr("checked")? "p":"";
			var sy = $("#sy").attr("checked")? "s":"";
			var pn = $("#pn").attr("checked")? "p":"";
			var sn = $("#sn").attr("checked")? "s":"";
			
			var type = { "Y":'ps', "N":'ps'};
			return type;
		}
	
		function refreshTree() {
			var checkType = getCheckBoxType();
			setting.checkType = checkType;
			
			zTree1 = $("#treeDemo").zTree(setting, clone(zNodes));
			$("#checkTypeCode").html("{\"Y\":" + checkType.Y + ", \"N\":" + checkType.N + "}");
			getCheckedNodesLength();
		}
	
		function getCheckedNodesLength() {
			var tmp = zTree1.getCheckedNodes();
			$("#checkedNum").html(tmp.length);
			tmp = zTree1.getChangeCheckedNodes();
			$("#checkedChangeNum").html(tmp.length);
			if ($("#changeYes").attr("checked")) {
				for (var i=0; i<tmp.length; i++) {
					tmp[i].checkedOld = tmp[i].checked;
				}
			}
		}
		//
		function  myClose()   
	    {   
			var treeNode=zTree1.getCheckedNodes();
	      	var nodes='';
	      	var hasechange=zTree1.getChangeCheckedNodes();
	      	if(hasechange.length!=0)
	      	{
		      	for(i=0;i<treeNode.length;i++)
		      	{

		      			if(nodes.length==0)//第一次追加
							nodes=treeNode[i].ename;
						else
							nodes=nodes+","+treeNode[i].ename;
		      	
		      	}
		      	
		      	
		      	//
		      	//if(confirm("您确定保存这次修改吗?"))
		      	//{
				    
		      		$.ajax({
						url :"${pageContext.request.contextPath }/servlet/updateUserRightServlet",
						type : 'get',
						async:false,
						data:'nodes='+nodes,
						success :function(mm){
							//Dialog.alert("保存权限成功！");
							},
						error:function(xhr,status,errorThrown){
								alert("保存权限失败！原因："+errorThrown);
								}
						});	
		      		
				//}
	      	}
		}
		
	  //-->
	  </SCRIPT>

  </head>
  
  <body onUnload="myClose()" > 
   <TABLE border=0 width="700" class="tb1">
		<TR>
			<TD width=340px align=center valign=top>
			<div class="zTreeDemoBackground">
				<ul id="treeDemo" class="tree"></ul>
			</div>		
			</TD>
		</TR>
	</TABLE>
  </body>
</html>
