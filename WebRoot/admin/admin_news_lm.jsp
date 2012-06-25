<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="checkuser.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css"> /*注意选择器的层叠关系*/
	 	 /*.font {TEXT-ALIGN: center;COLOR: blue;FONT-SIZE: 16px; FONT-WEIGHT: bold;}*/
	 	 
	 	 	.buttonStyle{width:64px;height:22px;line-height:22px;color:#369;text-align:center;background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left top;border:0;font-size:12px;}
			.buttonStyle:hover{background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left -23px;}
	 	 
	 	</style>
		<link href="${pageContext.request.contextPath }/admin/css/subcss.css" rel="stylesheet" />
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDrag.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDialog.js"></script>
	</head>
	<script language="JavaScript">
	<!--
		$(document).ready(function(){
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
		 });
		//alter password
		function alterLmName(id,LmLevel)
		{
		
			var diag = new Dialog();
			diag.Top =20;
			diag.Width = 350;
			diag.Height = 150;
			diag.Title = "修改系统栏目";
			diag.URL = "/hbcitsite/admin/admin_news_lm_alter.jsp?id="+id+'&lmLevel='+LmLevel,
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
		//add root lm
		function addLm(id,LmLevel)
		{
			var diag = new Dialog();
			diag.Top =20;
			diag.Width = 400;
			diag.Height = 150;
			diag.Title = "增加系统栏目";
			diag.URL = "/hbcitsite/admin/admin_news_lm_add.jsp?id="+id+"&LmLevel="+LmLevel;
			diag.OKEvent = function(){
				window.location.reload();
				//diag.close();
			};
			diag.ShowCloseButton=false;
			diag.MessageTitle = "增加栏目提示：";
			diag.Message = "填完栏目名称后不要忘记先”保存栏目“，然后才可关闭窗口";
			diag.show();
			diag.okButton.value="结果刷新";
			diag.cancelButton.value="关闭";
		}
		//set lm mb
		function setLmMb(id)
		{

			var diag = new Dialog();
			diag.Top =20;
			diag.Width = 400;
			diag.Height = 150;
			diag.Title = "设置栏目模板";
			diag.URL = "/hbcitsite/admin/admin_news_setlm_mb.jsp?id="+id+'&lm='+$('#'+id).val();
			diag.OKEvent = function(){
				window.location.reload();
				//diag.close();
			};
			diag.ShowCloseButton=false;
			diag.MessageTitle = "设置栏目模板提示：";
			diag.Message = "设置完栏目模板后不要忘记先”保存修改“，然后才可关闭窗口";
			diag.show();
			diag.okButton.value="结果刷新";
			diag.cancelButton.value="关闭";
		}
		//
		function deleteLm(id)
		{
			
			
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/LmDeleteServlet",
					type : 'get',
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
		//
		//
		function lmUnion()
		{
			var lmUnion1=$('#lmUnion1').val();
			var lmUnion2=$('#lmUnion2').val();
			if(lmUnion1=="0" )
			{
				Dialog.alert("您还没有选择被合并的栏目!");
				return false;
			}
			if(lmUnion2=="0")
			{
				Dialog.alert("您还没有选择合并的目标栏目!");
				return false;
			}
			if(lmUnion1==lmUnion2)
			{
				Dialog.alert("不允许选择相同栏目进行合并!");
				return false;
			}
			//明天接着来
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/LmUpdateServlet",
					type : 'get',
				    data : 'lmUnion1='+$('#lmUnion1').val()+'&lmUnion2='+$('#lmUnion2').val()+'&Condition=1',
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								Dialog.alert("合并成功!",function(){window.location.reload();});
							}
							else
								Dialog.alert("合并失败!",function(){window.location.reload();});
					}
			});	
			//setLmMb
			return true;
		}
		
	//-->
	</script>
	<body topmargin="0" bgcolor="#ffffff" leftmargin="0" rightmargin="0"
		bottommargin="0" marginwidth="0" marginheight="0" 	 >
		
		<div align="center" style="margin-top:0;">
			<table border="0" cellpadding="0" cellspacing="0" width="80%">
				<tr>
					<td>
						<center>
							<table border="0" width="100%" id="table1" height="80"
								cellspacing="0" cellpadding="0">
								<tr>
									<td height=20>
									<div class="title">
										系统栏目维护
										<hr>
									</div>
									</td>
								</tr>
								<tr>
									<td height=25 background="${pageContext.request.contextPath }/admin/images/bj3.jpg">
										<div align="center">
											<table border="0" cellpadding="0" cellspacing="0" width="744"	id="table3">
												<tr>
													<td width="40%" height=20 align=center><img src="/hbcitsite/admin/images/hand_point.png" />&nbsp;&nbsp;<a href="#" onClick="addLm(0,0);"><span style="font-size:10pt;">增加一级栏目</span></a></td>
													<td width="60%" height=20 align=center>
																											
														将栏目
														<select name="lmUnion1" id="lmUnion1"  >
															<option value="0">=请选择=</option>  
															<c:forEach items="${lmList}" var="lmUnion">
																<c:if test="${lmUnion.lmid == ''}">
																	<option value="${lmUnion.id}">${lmUnion.lm }</option>
																</c:if>
															</c:forEach>
														</select>
															合并到栏目
														<select name="lmUnion2" id="lmUnion2"  >
															<option value="0">=请选择=</option>  
															<c:forEach items="${lmList}" var="lmUnion">
																<c:if test="${lmUnion.lmid == ''}">
																	<option value="${lmUnion.id}">${lmUnion.lm }</option>
																</c:if>
															</c:forEach>
														</select>
														<input type="button" value="合并" name="B2" class="buttonStyle" onClick="lmUnion();">
													</td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
							<div align="center">
								<table border="1" width="100%" id="table2" cellpadding="3"
									style="border-collapse: collapse" cellspacing="0"
									bordercolor="#CCCCCC"  class="stripe_tb">
									<tr style="font-weight:bold">
										<td align="center" width="40%" background="${pageContext.request.contextPath }/admin/images/bj3.jpg" height="25">栏目名称</td>
										<td align="center" width="15%" background="${pageContext.request.contextPath }/admin/images/bj3.jpg" height="25">栏目下文章数量</td>
										<td align="center" width="15%" background="${pageContext.request.contextPath }/admin/images/bj3.jpg" height="25">显示使用的模版</td>
										<td align="center" width="30%" background="${pageContext.request.contextPath }/admin/images/bj3.jpg" height="25">操作</td>
									</tr>

									<!--从这里开始循环出各个栏目列示 -->
									<c:forEach items="${lmList}" var="lm">
										<c:if test="${lm.lmid == ''}"><!--root-->
												<tr >
													<td width="40%" align="left">
														<b><font color=""></font>
														</b>
														<font color=999999>[<a href="#"><font color="#999999">${lm.lm}</font></a>]</font>
													</td>
													<td width="15%" >
														${lm.documentsCount}
													</td>
													<td width="15%"  align=center>
														${lm.mb}
													</td>
													<td width="30%" align="right">
														<img src="/hbcitsite/admin/images/addlm.jpg" /><a href="#" onClick="addLm(${lm.id},2);">添子栏目</a>
														<input id="${lm.id}" type="hidden" value="${lm.lm}">
														<img src="/hbcitsite/admin/images/Mb.jpg" /><a href="#" onClick="setLmMb(${lm.id});">模版</a>
														<!-- <a href="admin_htmlsc.asp?lm=" 
															title="将此栏目及子栏目所有文章生成静态HTML">静</a>   
														<a href="admin_htmlqx.asp?lm="
															title="将此栏目及子栏目所有文章取消静态HTML">动</a>  
														<a href=# >调用</a>
															-->
														<img src="/hbcitsite/admin/images/alterlm.jpg" /><a href="#" onClick="alterLmName(${lm.id},0);">修改</a>
															
														<img src="/hbcitsite/admin/images/deleteLm.jpg" /><a onclick="Dialog.confirm('警告：删除“${lm.lm}”栏目将同时删除其所有子栏目，您确认吗？',function(){deleteLm(${lm.id});});" href="#">删除</a>
														
													</td>
												</tr>
												<!-- child -->
												<c:forEach items="${lmList}" var="lm1">
													<c:if test="${lm1.lmid eq lm.id}">
														<tr >
															<td width="40%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├
																<a href=""><font color=""></font></a>
																
																<font color=999999>[<a href="#"><font color="#999999">${lm1.lm2}</font></a>]</font>
															</td>
															<td width="15%" >
																${lm1.documentsCount}
															</td>
															<td width="15%"  align=center>
																${lm1.mb}
															</td>
															<td width="30%" align="right">
																<img src="/hbcitsite/admin/images/addlm.jpg" /><a href="#" onClick="addLm(${lm1.id},3);">添子栏目</a>
																<input id="${lm1.id}" type="hidden" value="${lm1.lm2}">
																<img src="/hbcitsite/admin/images/Mb.jpg" /><a href="#" onClick="setLmMb(${lm1.id});">模版</a>
																<!-- <a href="admin_htmlsc.asp?lm=" 
																		title="将此栏目及子栏目所有文章生成静态HTML">静</a>   
																	<a href="admin_htmlqx.asp?lm="
																		title="将此栏目及子栏目所有文章取消静态HTML">动</a>  
																	<a href=# >调用</a>
																		-->
																<img src="/hbcitsite/admin/images/alterlm.jpg" /><a href="#" onClick="alterLmName(${lm1.id},1);">修改</a>
																<img src="/hbcitsite/admin/images/deleteLm.jpg" /><a onClick="Dialog.confirm('警告：删除“${lm1.lm2}”栏目将同时删除其所有子栏目，您确认吗？',function(){deleteLm(${lm1.id});});"	href="#">删除</a>
																
															</td>
														</tr>
														<!-- leaf=true -->
														<c:forEach items="${lmList}" var="lm2">
															<c:if test="${lm2.lmid eq lm1.id}">
																<tr >
																	<td width="40%" align="left">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├
																		<a href=""><font color=""></font></a>
																		
																		<font color=999999>[<a href="#"><font color="#999999">${lm2.lm3}</font></a>]</font>
																	</td>
																	<td width="15%" >
																		${lm2.documentsCount}
																	</td>
																	<td width="15%"  align=center>
																		${lm2.mb}
																	</td>
																	<td width="30%" align="right">
																		
																		<input id="${lm2.id}" type="hidden" value="${lm2.lm3}">
																		<img src="/hbcitsite/admin/images/Mb.jpg" /><a href="#" onClick="setLmMb(${lm2.id});">模版</a>
																		<!-- <a href="admin_htmlsc.asp?lm=" 
																			title="将此栏目及子栏目所有文章生成静态HTML">静</a>   
																		<a href="admin_htmlqx.asp?lm="
																			title="将此栏目及子栏目所有文章取消静态HTML">动</a>  
																		<a href=# >调用</a>
																			-->
																		<img src="/hbcitsite/admin/images/alterlm.jpg" /><a href="#" onClick="alterLmName(${lm2.id},2);">修改</a>
													
																		<img src="/hbcitsite/admin/images/deleteLm.jpg" /><a onClick="Dialog.confirm('警告：您确认删除“${lm2.lm3}”栏目吗？',function(){deleteLm(${lm2.id});});"	href="#">删除</a>
																		
																	</td>
																</tr>
																<!-- leaf=true -->
															</c:if>
														</c:forEach>
													</c:if>
												</c:forEach>
										</c:if>
										
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
