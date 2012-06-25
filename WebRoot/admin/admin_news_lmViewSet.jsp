<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="checkuser.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css"> /*注意选择器的层叠关系*/
		.font {TEXT-ALIGN: center;COLOR: blue;FONT-SIZE: 12px; FONT-WEIGHT: bold;}
	 	.buttonStyle{width:64px;height:22px;line-height:22px;color:#369;text-align:center;background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left top;border:0;font-size:12px;}
		.buttonStyle:hover{background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left -23px;}
	 	 
	 	</style>
	 	<!--zoom  -->
	 	
		<!-- upload img -->
		<link href="${pageContext.request.contextPath }/admin/css/subcss.css" rel="stylesheet" />
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDrag.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDialog.js"></script>
		
		<link rel="stylesheet" type="text/css" href="/hbcitsite/admin/js/ajaxfileupload.css">
		<script type="text/javascript" src="/hbcitsite/admin/js/ajaxfileupload.js"></script>
	</head>
	<script language="JavaScript">
	<!--
		$(document).ready(function(){
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
		 });
		//保存版块修改
		function save(id,indexLmNo)
		{
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/IndexBanKuaiSetServlet",
					type : 'post',
				    data : 'id='+id+'&conditions=2&indexLmNo='+indexLmNo+'&lm='+$('#lm'+indexLmNo).val()+'&lm2='+$('#lm2'+indexLmNo).val()+'&lm3='+$('#lm3'+indexLmNo).val()+'&indexLmPic='+$('#indexLmPic'+indexLmNo).val()+'&notes='+$('#notes'+indexLmNo).val(),
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								Dialog.alert("保存成功!",function(){location.replace("${pageContext.request.contextPath}/servlet/IndexBanKuaiSetServlet?conditions=1");});
							}
							else
								Dialog.alert("保存失败!");
					}
			});	
		}
		//增加版块
		function add()
		{		
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/IndexBanKuaiSetServlet",
					type : 'post',
				    data : 'conditions=3',
					success :function(mm){
							window.location.reload();
					}
			});	
		}
		
		//
		function ajaxFileUpload(id){
            var par= $('#houseMaps'+id).val();
            if(par.length==0)
            {
            	alert("必须选择一张图片才能更改!");
            	return;
            }
            if(!confirm("您确定要更改"+id+"版块的顶部图片么？"))
            {
            	return;
            }
 
            //
			$.ajaxFileUpload(
                   {
		                url:'/hbcitsite/servlet/UploadLmImageServlet?file='+$('#houseMaps'+id).val()+'&id='+id,            //需要链接到服务器地址
		                secureuri:false,
		                fileElementId:'houseMaps1',                        //文件选择框的id属性
		                dataType: 'text',                                 //服务器返回的格式，可以是json
		                success: function (data, status)            //相当于java中try语句块的用法
		                {      
		                    Dialog.alert("更改成功!",function(){location.replace("${pageContext.request.contextPath}/servlet/IndexBanKuaiSetServlet?conditions=1");});
		                },
		                error: function (data, status, e)            //相当于java中catch语句块的用法
		                {
		                    Dialog.alert("更改失败!");
		                }
				});  
				}	
		//删除版块
		function deleLm(id,indexLmNo)
		{
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/IndexBanKuaiSetServlet",
					type : 'post',
				    data : 'conditions=4&id='+id+'&indexLmNo='+indexLmNo,
					success :function(mm){
							window.location.reload();
					}
			});	
		}
		//检索级联选择
		function getSubLm(lmLevel,id)
		{
			var objM1;var objM2;
			if(lmLevel==1)
			{
				objM1=document.getElementById("lm"+id);
				objM2=document.getElementById("lm2"+id);
			}
			if(lmLevel==2)
			{
				objM1=document.getElementById("lm2"+id);
				objM2=document.getElementById("lm3"+id);
			}
			objM2.length=0;
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/LmSelectServlet",
					type : 'get',
					dataType:'json',
				    data : 'Condition=2&id='+objM1.value+'&lmLevel='+lmLevel,
					success :function(mm){
							objM2.add(new Option("请选择","0"));
							var stuArr = mm.lm;
							for(var i in stuArr){
								if(stuArr[i].lm!=undefined)
								 	eval(objM2.add(new Option(stuArr[i].lm,stuArr[i].id)));
	                      	}
					}
			});	
		}
	//-->
	</script>
	<body topmargin="0" bgcolor="#ffffff"  >
		
		<div align="center" >
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td>
						<center>
							<table border="0" width="100%" id="table1" height="40"
								cellspacing="0" cellpadding="0">
								<tr>
									<td height=15>
										<div class="title">
											系统主页板块显示栏目设置
											<hr>
										</div>
									</td>
								</tr>
								<tr>
									<td height=10 align="left">
										<div><img src="/hbcitsite/admin/images/hand.gif" /><a href="#" onClick="add();">&nbsp;<span style="TEXT-ALIGN: center;COLOR: blue;FONT-SIZE: 14px; ">增加新版块</span></a>
										</div>
									</td>
								</tr>
							
							</table>
							
							<div align="center">
								<table border="1" width="100%" id="table2" cellpadding="3"
									style="border-collapse: collapse" cellspacing="0"
									bordercolor="#CCCCCC"  class="stripe_tb">
									<tr>
										
										<td align="center" >
											<font class="font">序号</font>
										</td>
										<td align="center"  >
											<font class="font">一级栏目</font>
										</td>
										<td align="center">
											<font class="font">二级栏目</font>
										</td>
										<td align="center" >
											<font class="font">三级栏目</font>
										</td>
										
										<td align="center" >
											
											<font class="font">版块顶部图片</font>
										</td>
										
										<td align="center" >
											
											<font class="font">说明</font>
										</td>
										<td align="center"  >
											<font class="font">操作</font>
										</td>
									</tr>
									
									<c:forEach items="${dylist}" var="bk">
										<tr >
											<!-- 序号 -->
											<td  align="center" >
												<c:if test="${bk.lm == ''}">
													<input style="width:15px;" type="text" value="${bk.indexLmNo}">
												</c:if>
												<c:if test="${bk.lm != ''}">
													${bk.indexLmNo}
												</c:if>
											</td>
											<!-- 栏目 -->
											<td  >
												<select name="${bk.indexLmNo}lm" id="lm${bk.indexLmNo}" onchange="getSubLm(1,${bk.indexLmNo});">
													<c:if test="${bk.lm == '0'}">
														<option value="0">请选择</option>
													</c:if>
													<c:if test="${bk.lm != '0'}">
														<option value="0">请选择</option>
													</c:if>
													<c:forEach items="${lmlist}" var="lm">
														<c:if test="${lm.lm != ''}">
															<c:if test="${lm.id==bk.lm}">
																<option value="${lm.id}" selected>${lm.lm}</option>
															</c:if> 
															<c:if test="${lm.id!=bk.lm}">
																<option value="${lm.id}">${lm.lm}</option>
															</c:if> 
														</c:if>
													</c:forEach>
												</select>
											</td>
											<!-- 栏目2 -->
											<td  >
												<select  name="${bk.indexLmNo}lm2" id="lm2${bk.indexLmNo}" onchange="getSubLm(2,${bk.indexLmNo});">
													<c:if test="${bk.lm2 == '0'}">
														<option value="0">请选择</option>
													</c:if>
													<c:if test="${bk.lm2 != '0'}">
														<option value="0">请选择</option>
													</c:if>
													<c:forEach items="${lmlist}" var="lm">
														<c:if test="${lm.lm2 != ''}">
															<!-- <c:if test="${lm.id!=bk.lm2}">
																<option value="${lm.id}">${lm.lm2}</option>
															</c:if>  -->
															<c:if test="${lm.id==bk.lm2}">
																<option value="${lm.id}" selected>${lm.lm2}</option>
															</c:if>
														</c:if>
													</c:forEach>
												</select>
											</td>
											<!-- 栏目3 -->
											<td >
												<select  name="${bk.indexLmNo}lm3" id="lm3${bk.indexLmNo}" >
													<c:if test="${bk.lm3 == '0'}">
														<option value="0">请选择</option>
													</c:if>
													<c:if test="${bk.lm3 != '0'}">
														
														<option value="0">请选择</option>
													</c:if>
													<c:forEach items="${lmlist}" var="lm">
														<c:if test="${lm.lm3 != ''}">
															<!-- <c:if test="${lm.id==bk.lm3}">
																<option value="${lm.id}" selected>${lm.lm3}</option>
															</c:if> -->
															<c:if test="${lm.id!=bk.lm3}">
																<option value="${lm.id}">${lm.lm3}</option>
															</c:if>
														</c:if>
													</c:forEach>
												</select>
											</td>
											<td  align="left">
												<img alt="${bk.indexLmNo}版块顶部图片" width="200" height=20 src="/hbcitsite/images/${bk.indexLmPic}" >
												&nbsp;&nbsp;<input type="file" size=8 id="houseMaps${bk.indexLmNo}" name="houseMaps${bk.indexLmNo}"/> 
											    <input type="button" value="更改图片" onclick="ajaxFileUpload(${bk.indexLmNo});"/>
											</td>
											<td  >
												<input type="text" size=5  value="${bk.notes}" id="notes${bk.indexLmNo}">
											</td>
											<td  >
												<img src="/hbcitsite/admin/images/save.jpg" /><a href="#" onClick="save(${bk.id},${bk.indexLmNo});">保存</a> 
												<img src="/hbcitsite/admin/images/delete.jpg" /><a href="#" onClick="Dialog.confirm('警告：您确认要删除${bk.indexLmNo}版块吗？',function(){deleLm(${bk.id},${bk.indexLmNo});});">删除</a> 
												
											</td>
										</tr>
										
									</c:forEach>
									
								
								</table>
							</div>
						</center>
					</td>
				</tr>
			</table>
		</div>


	</body>

</html>
