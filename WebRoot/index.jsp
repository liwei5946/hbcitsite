<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<META content="河北工业职业技术学院,河北工院,职业教育,工业,职业,材料,机电,法经,计算机,环境工程,自动化,建筑工程,职教集团,钢铁冶金,数控,冶金,汽车,会计,河北省高校"  name="keywords">
<META content="河北是中国钢铁第一大省,河北工业职业技术学院是河北省规模最大,办学历史最悠久的省属冶金类国家示范性高职院校。2006年，学院高职高专院校人才培养工作水平评估获得优秀；2007年，被河北省政府确定为'河北省示范性高等职业院校'和'河北省钢铁冶金职教集团牵头单位'；2008年，被教育部、财政部确定为'国家示范性高等职业院校建设计划'100所立项建设院校之一。 " name="description">
<title>河北工业职业技术学院</title>
<link rel="icon" href="${pageContext.request.contextPath }/img/HBCIT.ico">
<link rel="shortcut icon" href="${pageContext.request.contextPath }/img/HBCIT.ico">
<!--[if lt IE 7]>
<SCRIPT src="${pageContext.request.contextPath }/js/MinMax.js" type=text/javascript></SCRIPT>
<![endif]-->
<SCRIPT src="${pageContext.request.contextPath }/js/png_transparent.js" type=text/javascript></SCRIPT>
 <base target="_blank">
<style type="text/css">
<!--
BODY {
	  PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-SIZE: 12px; MIN-HEIGHT: 480px; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-TOP: 0px; FONT-FAMILY: Arial,Verdana, sans-serif; HEIGHT: 480px; /*background-image:url(/float_img/60_zty_bg.jpg);background-repeat: no-repeat;background-position: center top;*/background-image:url(${pageContext.request.contextPath }/images/bodybg.jpg);
}
-->
</style>
<link href="${pageContext.request.contextPath }/css/style-index.css" rel="stylesheet"/>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js"></script>


<%@ include file="ga.html" %> 
</head>
<body>

		<div class="area">
			<!-- 内容开始  start-->

			<!-- 头部 start -->
			<div id="top">
				<EMBED align="right" pluginspage=http://www.macromedia.com/go/getflashplayer
					src=${pageContext.request.contextPath }/images/top3.swf width=950 height=170
					type=application/x-shockwave-flash wmode="transparent"
					quality="high"/>
			</div>
			<!-- 导航 start -->
			<div>
				<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
					codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0"
					width="951" height="55">
					<param name="movie" value="${pageContext.request.contextPath }/images/b6.swf" />
					<param name="quality" value="high" />
					<param value="false" name="menu" />
					<param value="opaque" name="wmode" />
					<embed src="${pageContext.request.contextPath }/images/b6.swf" quality="high"
						pluginspage="http://www.macromedia.com/go/getflashplayer"
						type="application/x-shockwave-flash" width="951" height="55"></embed>
				</object>
			</div>
			<!-- 导航 end -->

			<!-- 头部  end-->

			<!-- 第一部分 start -->

			<div id="row1">
				<!-- 第一部分 第一列 start -->
				<div class="col1">
					<!-- 第一部分 第一列 第一部分 start -->
					<!-- falsh新闻滚动图片 start -->
					<div class="row1">
						<!--#include file=ce.asp -->
						<script type="text/javascript">
							<c:forEach items="${imgList}" var="myimg" varStatus="status"> 
								imgUrl${status.count}="${myimg.pic}";
								//imgtext${status.count}="${myimg.title}";
								imgtext${status.count}="${fn:substring(myimg.title, 0, 16)}";
								imgLink${status.count}=escape("/hbcitsite/servlet/ViewSelectNewToModelPage?newsid=${myimg.id}&condition=1");
							</c:forEach>
	
							 var focus_width=240;
							 var focus_height=175;
							 var text_height=18;
							 var swf_height = focus_height+text_height;
							 
							 var pics=imgUrl1+"|"+imgUrl2+"|"+imgUrl3+"|"+imgUrl4+"|"+imgUrl5;
							 var links=imgLink1+"|"+imgLink2+"|"+imgLink3+"|"+imgLink4+"|"+imgLink5;
							 var texts=imgtext1+"|"+imgtext2+"|"+imgtext3+"|"+imgtext4+"|"+imgtext5;
							 
							 document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ focus_width +'" height="'+ swf_height +'">');
							 document.write('<param name="allowScriptAccess" value="sameDomain"><param name="movie" value="${pageContext.request.contextPath }/swf/focus.swf"><param name="quality" value="high"><param name="bgcolor" value="#F0F0F0">');
							 document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
							 document.write('<param name="FlashVars" value="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'">');
							 document.write('<embed src="${pageContext.request.contextPath }/swf/focus.swf" wmode="opaque" FlashVars="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'" menu="false" bgcolor="#F0F0F0" quality="high" width="'+ focus_width +'" height="'+ focus_height +'" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />');  document.write('</object>');
	 					</script>
					</div>
					<!-- 第一部分 第一列 第一行 end -->
					
					<!-- 第一部分 第一列 第二部分 start -->
					<div class="row2">
						<img src="${pageContext.request.contextPath }/images/a-left02tit.jpg" style="padding: 10px 10px" />
						<ul>
							<li>
								<a href="/hbcitsite/1_1.jsp" target="_blank">学院概况</a>
							</li>
							<li>
								<a href="/hbcitsite/1_2.jsp" target="_blank">学院领导</a>
							</li>
							<li>
								<a href="/hbcitsite/1_3.jsp" target="_blank">校徽校训校歌</a>
							</li>
							<li>
								<a href="/hbcitsite/1_4.jsp" target="_blank">学院发展简史</a>
							</li>
							<li>
								<a href="/hbcitsite/1_5.jsp" target="_blank">成就与荣誉</a>
							</li>
							<li>
								<a href="/hbcitsite/1_6.jsp" target="_blank">媒体报道</a>
							</li>
							<li>
								<a href="/hbcitsite/1_7.jsp" target="_blank">组织机构</a>
							</li>
						</ul>
					</div>
					<!-- 第一部分 第一列 第二部分end -->
				</div>
				<!-- 第一部分 第一列  end -->

				<!-- 第一部分 第二列 start -->
				<div class="col2">
				
						<!--  <img src="${pageContext.request.contextPath }/images/a-c01-tit.jpg"  style="padding: 4px 10px" border="0" usemap="#Map" />  -->
						<img src="${news1TopPic}"  style="padding: 4px 10px" border="0" usemap="#Map" />
						<map name="Map" id="Map">
							<area shape="rect" coords="384,13,441,32" href="${pageContext.request.contextPath }/servlet/ViewSelectNewToModelPage?type=1&condition=2&cpage=1" target="_blank"/>
					    </map>
				    <!-- 置顶新闻显示 -->
					<div style="padding: 0px 10px" class="row2">
						<c:forEach items="${news1List}" var="cNews"> 
							<c:if test="${cNews.ontop==1}"><table width="100%" align="center">
								<tr align="center"><td align="center">
									<a href="/hbcitsite/servlet/ViewSelectNewToModelPage?newsid=${cNews.id}&condition=1"  title="${cNews.title}" style="color:red;font-size:12pt;font-family:黑体">
										<c:choose>
											<c:when test="${fn:length(cNews.title) > 20}">
												<c:out value="${fn:substring(cNews.title, 0, 20)}....." />
											</c:when>
											<c:otherwise>
												<c:out value="${cNews.title}" />
											</c:otherwise>
										</c:choose>
									</a>
									</td>
								</tr>
							</table></c:if>
						</c:forEach>
						<!--  
						<script TYPE="text/javascript" language="javascript"
							src="http://www.hbcit.edu.cn//newscode-news.asp?id=0&lm=69&list=1&ontop=5&font=9&nr=0&nrtop=150&nrcolor=000000&titleface=黑体&titlesize=16&titlen=40&titlebold=100&titlecolor=000000">
						</script>
						-->
						<br>
						<!-- 校园快讯内容 -->
						<table width="100%">
							<c:forEach items="${news1List}" var="cNews"> 
							<c:if test="${cNews.ontop!=1}"><!--//非置顶-->
								<tr>
									<td>
										${cNews.iconPath}
									</td>
									<td>
										  <a href="/hbcitsite/servlet/ViewSelectNewToModelPage?newsid=${cNews.id}&condition=1"  title="${cNews.title}">
											  <c:choose>
										          <c:when test="${fn:length(cNews.title) > 26}">
										              <c:out value="${fn:substring(cNews.title, 0, 26)}....." />
										          </c:when>
										         <c:otherwise>
										            <c:out value="${cNews.title}" />
										          </c:otherwise>
										      </c:choose>
										 </a>
									</td>
									<td align="right">
										<span style='color:#C0C0C0;'>${cNews.time}</span>
									</td>
								 </tr>
							 </c:if>
							</c:forEach>
						</table>
					<script TYPE="text/javascript" language="javascript" src="http://www.hbcit.edu.cn//newscodejs.asp?lm2=69&list=15&icon=img/dot.jpg&tj=0&font=9&hot=0&new=0&line=2&lmname=0&open=1&n=50&more=0&t=2&week=0&zzly=0&hit=0&pls=0">
					</script>
					</div>
				</div>
				<!-- 第一部分 第二列 end -->
				
				<!-- 第一部分 第三列 start -->
				<div class="col3">
					<ul>
						<li><a href="http://www.hbcit.edu.cn/cxzy"><img src="${pageContext.request.contextPath }/images/left-01-cxzy.png" border="0" width="236" height="63" />
							</a></li>
						<li><a href="http://gxpx1.ceat.edu.cn" target="_blank"><img src="${pageContext.request.contextPath }/images/left-02-xxzx.png" width="236" height="63" border="0" />
								</a></li>
						<li><a href="http://jpk.hbcit.edu.cn/" target="_blank"><img	src="${pageContext.request.contextPath }/images/left-03-jpkc.gif" border="0" width="236" height="63" />
								</a></li>
						<li><a href="http://www.hbcit.edu.cn/pjzt/index.asp" target="_blank"><img src="${pageContext.request.contextPath }/images/left-pjzt.gif" width="236" height="63" border="0" />
								</a></li>
						<li><a href="http://www.heosteel.cn/"><img src="${pageContext.request.contextPath }/images/left-05-zjjt.gif" border="0" width="236"	height="63" />
								</a></li>
						<li><a href="http://www.hbxgfy.com"><img src="${pageContext.request.contextPath }/images/left-06-xgfy.gif" border="0" width="236" height="63" />
								</a></li>
					</ul>
				</div>
				<!-- 第一部分 第三列 end -->
			</div>

			<!-- 第一部分 end -->

			<!-- 第二部分 start -->
			<div id="row2">
				<div style="float: left">
					<a href="http://222.30.178.10/"></a><img src="${pageContext.request.contextPath }/images/a-left03.jpg" border="0"  /></a>
				</div>
				<div style="float: left">
					<a href="http://www.hbcit.edu.cn/sfjs/index.asp"><img src="${pageContext.request.contextPath }/images/sfyxjszt.gif"
							width="711" height="85" border="0" />
					</a>
				</div>
			</div>
			<!-- 第二部分 end -->
		

			<!-- 第三部分 start -->
			<div id="row3">
				<!-- 第三部分 第一列  校园风景 start -->
				<div class="col1">
					<div style="padding: 3px 10px">
						<img src="${pageContext.request.contextPath }/images/a-left04tit.jpg" />
					</div>
					<div style="padding-left: 20px">
						<iframe src="http://www.hbcit.edu.cn/new/fengguang.asp" width="200" height="150"
							frameborder="0" scrolling="no" marginwidth="0" marginheight="0"></iframe>
					</div>
				</div>
				<!-- 第三部分 第一列 end -->
			
				<!-- 第三部分 第二列start-->
				<div class="col2">
					<div style="padding: 10px 10px">
						<!--  <img src="${pageContext.request.contextPath }/images/a-center03tit.jpg" border="0" usemap="#Map6" />  -->
						<img src="${news2TopPic}"  border="0" usemap="#Map6" />
						<map name="Map6" id="Map6">
							<area shape="rect" coords="377,13,428,32"
								href="${pageContext.request.contextPath }/servlet/ViewSelectNewToModelPage?type=2&condition=2&cpage=1" target="_blank" />
						</map>
					</div>
					<div style="padding-left: 10px">
						<script TYPE="text/javascript" 	src="http://www.hbcit.edu.cn/newscodejs.asp?lm2=72&list=6&icon=img/dot.jpg&tj=0&font=9&hot=0&new=0&line=2&lmname=0&open=1&n=66&more=0&t=0&week=0&zzly=0&hit=0&pls=0">
</script>
						<!-- 通知公告 -->
						<table width="100%">
							<c:forEach items="${news2List}" var="cNews1"> 
							<tr>
								<td>
									${cNews1.iconPath}
								</td>
								<td>
									  <a href="/hbcitsite/servlet/ViewSelectNewToModelPage?newsid=${cNews1.id}&condition=1" onclick="shows(${cNews1.id});" title="${cNews1.title}">
										  <c:choose>
									          <c:when test="${fn:length(cNews1.title) > 40}">
									              <c:out value="${fn:substring(cNews1.title, 0, 40)}..." />
									          </c:when>
									         <c:otherwise>
									            <c:out value="${cNews1.title}" />
									          </c:otherwise>
									      </c:choose>
									 </a>
								</td>
								
							 </tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<!-- 第三部分 第二列end -->
				
				<!-- 第三部分 第三列start -->
				<div class="col3">
					<div style="padding-left: 10px; padding-top: 6px">
						<!-- 工院讲坛 -->
						<!-- <img src="${pageContext.request.contextPath }/images/a-right03tit.jpg" border="0" usemap="#Map7" />  -->
						<img src="${news3TopPic}"  border="0" usemap="#Map7" />
						<map name="Map7" id="Map7">
							<area shape="rect" coords="149,6,205,31"
								href="${pageContext.request.contextPath }/servlet/ViewSelectNewToModelPage?type=3&condition=2&cpage=1" target="_blank" />
								
						</map>
					</div>
					<div style="padding-left: 10px ">
						<script TYPE="text/javascript" language="javascript" src="httt://www.hbcit.edu.cn/newscodejs.asp?lm2=87&list=7&icon=img/dot2.jpg&tj=0&font=9&hot=0&new=0&line=3&lmname=0&open=1&n=28&more=0&t=0&week=0&zzly=0&hit=0&pls=0">
						</script>
						
						<table width="100%">
							<c:forEach items="${news3List}" var="cNews1"> 
							<tr>
								<td>
									${cNews1.iconPath}
								</td>
								<td>
									  <a href="/hbcitsite/servlet/ViewSelectNewToModelPage?newsid=${cNews1.id}&condition=1" onclick="shows(${cNews1.id});" title="${cNews1.title}">
										  <c:choose>
									          <c:when test="${fn:length(cNews1.title) > 15}">
									              <c:out value="${fn:substring(cNews1.title, 0, 15)}....." />
									          </c:when>
									         <c:otherwise>
									            <c:out value="${cNews1.title}" />
									          </c:otherwise>
									      </c:choose>
									 </a>
								</td>
								
							 </tr>
							</c:forEach>
						</table>


					</div>

				</div>
				<!-- 第三部分 第三列end -->
			</div>
		
			
			<!-- 第三部分 end -->
		

			<!-- 第四部分 start -->
			<div id="row4">
				<!-- 第四部分 第一列start    友情链接     -->
				<div class="col1">
					<div style="padding-left: 8px; padding-top: 10px">
						<iframe src="http://www.hbcit.edu.cn/new/link.asp" width="222" height="160" frameborder="0"
							scrolling="no" marginwidth="0" marginheight="0"></iframe>
					</div>
				</div>
				<!-- 第四部分 第一列end -->
				
				<!-- 第四部分 第二列start -->
				<div class="col2">
					<div style="padding: 5px 10px">
						<img src="${pageContext.request.contextPath }/images/a-center04tit.jpg" />
					</div>
					<div style="padding-left: 10px;">
						<table width="431" height="122" cellpadding="0" cellspacing="0"
							style="line-height: 170%">
							<tr>
								<td width=70 height="58" valign="top" style="padding-top: 5px">
									<img src=${pageContext.request.contextPath }/images/dot3.gif>
										&nbsp;&nbsp;教学系部
										<td valign="top">
											<a href="http://clx.hbcit.edu.cn" target="_blank">材料工程系</a> |
											<a href="http://jdx.hbcit.edu.cn" target="_blank">机电工程系</a> |
											<a href="http://hhx.hbcit.edu.cn" target="_blank">环境与化学工程系</a>
											|
											<a href="http://zdhx.hbcit.edu.cn" target="_blank">信息工程与自动化系</a>
											<br>
												<a href="http://jsjx.hbcit.edu.cn" target="_blank">计算机技术系</a>
												| <a href="http://jgx.hbcit.edu.cn" target="_blank">建筑工程系</a>
												| <a href="http://fjx.hbcit.edu.cn/" target="_blank">工商管理与贸易系</a>
												| <a href="http://jcb.hbcit.edu.cn" target="_blank">基础教学部</a>
												<br>
													<a href="http://ztb.hbcit.edu.cn/" target="_blank">思想政治与公共体育教学部</a>
													| <a href="http://cjc.hbcit.edu.cn" target="_blank">成人教育部
													</a>
													<tr>
														<td height="42" valign="top" style="padding-top: 4px">
															<img src=${pageContext.request.contextPath }/images/dot3.gif>
																&nbsp;&nbsp;行政部门
																<td valign="top">
																	<a href="http://yb.hbcit.edu.cn" target="_blank">党委、院长办公室</a>
																	|
																	<a href="http://zxb.hbcit.edu.cn/" target="_blank">组织宣传部</a>
																	|
																	<a href="http://jjjc.hbcit.edu.cn" target="_blank">纪检处</a>
																	|
																	<a href="http://jwc.hbcit.edu.cn" target="_blank">教务处</a>
																	|
																	<a href="http://cwc.hbcit.edu.cn" target="_blank">财务处</a>
																	|
																	<a href="http://rsc.hbcit.edu.cn" target="_blank">人事处</a>
																	<br />
																	<a href="http://xsc.hbcit.edu.cn" target="_blank">学生处</a>
																	|
																	<a href="http://tuanwei.hbcit.edu.cn" target="_blank">团委
																		| </a>
																	<a href="http://xzc.hbcit.edu.cn" target="_blank">后勤管理处</a>
																	|
																	<a href="http://hq.hbcit.edu.cn" target="_blank">
																		后勤服务中心</a> |
																	<a href="http://yfzx.hbcit.edu.cn" target="_blank">应用技术研发中心
																	</a>
																	<br />
																	<a href="http://wsb.hbcit.edu.cn" target="_blank">国际教育交流中心</a>
						</table>
					</div>
				</div>
				<!-- 第四部分 第二列end -->
		
				<!-- 第四部分 第三列start -->
				<div
					style="float: left; background-image: url(images/a-right04.jpg); height: 180px; width: 240px">
					<div style="padding: 5px 10px">
						<img src="${pageContext.request.contextPath }/images/a-right04tit.jpg" />
					</div>
					<div style="padding-left: 24px">
						<table cellpadding="1" cellspacing="5">
							<tr>
								<td>
									<img src=${pageContext.request.contextPath }/images/dot2.gif>
										<a href="http://jw.hbcit.edu.cn/">教务管理系统</a>
										<td>
											<td>
												<img src=${pageContext.request.contextPath }/images/dot2.gif>
													<a href="http://dorm.hbcit.edu.cn/dorm">公寓管理系统</a>
													<tr>
														<td>
															<img src=${pageContext.request.contextPath }/images/dot2.gif>
																<a href="http://oa.hbcit.edu.cn/lkoa5/
">OA系统 </a>
																<td>
																	<td>
																		<img src=${pageContext.request.contextPath }/images/dot2.gif>
																			<a href="inpt/checkip.asp?id=1" target="_blank">学生学习平台</a>
																			<tr>
																				<td>
																					<img src=${pageContext.request.contextPath }/images/dot2.gif>
																						校历查询
																						<td>
																							<td>
																								<img src=${pageContext.request.contextPath }/images/dot2.gif>
																									<a href="inpt/checkip.asp?id=2" target="_blank">教师教学平台
																									</a>
																									<tr>
																										<td>
																											<img src=${pageContext.request.contextPath }/images/dot2.gif>
																												教学资源
																												<td>
																													<td>
																														<img src=${pageContext.request.contextPath }/images/dot2.gif>
																															<a href="inpt/checkip.asp?id=3"
																																target="_blank">校企合作平台 </a>
																															<tr>
																																<td>
																																	<img src=${pageContext.request.contextPath }/images/dot2.gif>
																																		软件下载区
																																		<td>
																																			<td>
																																				<img src=${pageContext.request.contextPath }/images/dot2.gif>
																																					<a href="inpt/checkip.asp?id=4"
																																						target="_blank">社会交流平台 </a>
						</table>
					</div>
				</div>
				<!-- 第四部分 第三列end -->
			</div>
			<!-- 第四部分 end -->

			<!-- 第五部分 start -->
			
			<div id="row5">
			
				<!-- 第五部分 第一列 start -->
				<div class="col1">
					<div style="padding: 5px 10px">
						<!--  <img src="${pageContext.request.contextPath }/images/a-di1tit.jpg" border="0" usemap="#Map9" />  -->
						<img src="${news4TopPic}"  border="0" usemap="#Map9" />
						<map name="Map9" id="Map9">
							<area shape="rect" coords="161,11,218,34"
								href="${pageContext.request.contextPath }/servlet/ViewSelectNewToModelPage?type=4&condition=2&cpage=1" target="_blank" />
						</map>
					</div>
					<div style="padding-left: 10px">
						<script TYPE="text/javascript" language="javascript"
							src="http://www.hbcit.edu.cn//newscodejs.asp?lm2=75&list=7&icon=img/dot2.jpg&tj=0&font=9&hot=0&new=0&line=3&lmname=0&open=1&n=28&more=0&t=0&week=0&zzly=0&hit=0&pls=0">
</script>

						<!-- 职教资讯 -->
						<table width="100%">
							<c:forEach items="${news4List}" var="cNews1"> 
							<tr>
								<td>
									${cNews1.iconPath}
								</td>
								<td>
									  <a href="/hbcitsite/servlet/ViewSelectNewToModelPage?newsid=${cNews1.id}&condition=1" onclick="shows(${cNews1.id});" title="${cNews1.title}">
										  <c:choose>
									          <c:when test="${fn:length(cNews1.title) > 15}">
									              <c:out value="${fn:substring(cNews1.title, 0, 15)}..." />
									          </c:when>
									         <c:otherwise>
									            <c:out value="${cNews1.title}" />
									          </c:otherwise>
									      </c:choose>
									 </a>
								</td>
								
							 </tr>
							</c:forEach>
						</table>
					
					
					</div>
				</div>
				
				
				<!-- 第五部分 第二列 start -->
				<div class="col2">
					<div style="padding: 6px 10px">
						<!--  <img src="${pageContext.request.contextPath }/images/a-di2tit.jpg" border="0" usemap="#Map8" />  -->
						<img src="${news5TopPic}"  border="0" usemap="#Map8" />
						<map name="Map8" id="Map8">
							<area shape="rect" coords="143,4,211,30"
								href="${pageContext.request.contextPath }/servlet/ViewSelectNewToModelPage?type=5&condition=2&cpage=1" target="_blank" />
						</map>
					</div>
					<div style="padding-left: 10px">
						<script TYPE="text/javascript" language="javascript"
							src="http://www.hbcit.edu.cn/newscodejs.asp?lm2=77&list=7&icon=img/dot2.jpg&tj=0&font=9&hot=0&new=0&line=3&lmname=0&open=1&n=28&more=0&t=0&week=0&zzly=0&hit=0&pls=0">
</script>
						<!-- 行业动态 -->
						<table width="100%">
							<c:forEach items="${news5List}" var="cNews1"> 
							<tr>
								<td>
									${cNews1.iconPath}
								</td>
								<td>
									  <a href="/hbcitsite/servlet/ViewSelectNewToModelPage?newsid=${cNews1.id}&condition=1" onclick="shows(${cNews1.id});" title="${cNews1.title}">
										  <c:choose>
									          <c:when test="${fn:length(cNews1.title) > 15}">
									              <c:out value="${fn:substring(cNews1.title, 0, 15)}..." />
									          </c:when>
									         <c:otherwise>
									            <c:out value="${cNews1.title}" />
									          </c:otherwise>
									      </c:choose>
									 </a>
								</td>
								
							 </tr>
							</c:forEach>
						</table>
					
					</div>
				</div>
				
				<!-- 第五部分 第三列 start -->
				
				<div class="col3">
					<div style="padding: 7px 10px">
						<!--  <img src="${pageContext.request.contextPath }/images/a-di3tit.jpg" border="0" usemap="#Map11" /> -->
						<img src="${news6TopPic}"  border="0" usemap="#Map11" />
						<map name="Map11" id="Map11">
							<area shape="rect" coords="140,4,210,28"
								href="${pageContext.request.contextPath }/servlet/ViewSelectNewToModelPage?type=6&condition=2&cpage=1" target="_blank" />
						</map>
					</div>
					<div style="padding-left: 10px">
						<script TYPE="text/javascript" language="javascript"
							src="http://www.hbcit.edu.cn//newscodejs.asp?lm2=80&list=7&icon=img/dot2.jpg&tj=0&font=9&hot=0&new=0&line=3&lmname=0&open=1&n=28&more=0&t=0&week=0&zzly=0&hit=0&pls=0">
</script>
					<!-- 合作交流 -->
						<table width="100%">
							<c:forEach items="${news6List}" var="cNews1"> 
							<tr>
								<td>
									${cNews1.iconPath}
								</td>
								<td>
									  <a href="/hbcitsite/servlet/ViewSelectNewToModelPage?newsid=${cNews1.id}&condition=1" onclick="shows(${cNews1.id});" title="${cNews1.title}">
										  <c:choose>
									          <c:when test="${fn:length(cNews1.title) > 15}">
									              <c:out value="${fn:substring(cNews1.title, 0, 15)}..." />
									          </c:when>
									         <c:otherwise>
									            <c:out value="${cNews1.title}" />
									          </c:otherwise>
									      </c:choose>
									 </a>
								</td>
								
							 </tr>
							</c:forEach>
						</table>
					
					</div>
				</div>
				
				<!-- 第五部分 第四列 start -->
				
				<div class="col4">
					<div style="padding: 5px 10px">
						<!--  <img src="${pageContext.request.contextPath }/images/a-di4tit.jpg" border="0" usemap="#Map10" /> -->
						<img src="${news7TopPic}"  border="0" usemap="#Map10" />
						<map name="Map10" id="Map10">
							<area shape="rect" coords="153,6,225,30"
								href="${pageContext.request.contextPath }/servlet/ViewSelectNewToModelPage?type=7&condition=2&cpage=1" target="_blank" />
						</map>
					</div>
					<div style="padding-left: 10px">
						<script TYPE="text/javascript" language="javascript"
							src="http://www.hbcit.edu.cn//newscodejs.asp?lm2=83&list=7&icon=img/dot2.jpg&tj=0&font=9&hot=0&new=0&line=3&lmname=0&open=1&n=28&more=0&t=0&week=0&zzly=0&hit=0&pls=0">
</script>
					
						<!-- 校报校刊 -->
						<table width="100%">
							<c:forEach items="${news7List}" var="cNews1"> 
							<tr>
								<td>
									${cNews1.iconPath}
								</td>
								<td>
									  <a href="/hbcitsite/servlet/ViewSelectNewToModelPage?newsid=${cNews1.id}&condition=1" onclick="shows(${cNews1.id});" title="${cNews1.title}">
										  <c:choose>
									          <c:when test="${fn:length(cNews1.title) > 15}">
									              <c:out value="${fn:substring(cNews1.title, 0, 15)}..." />
									          </c:when>
									         <c:otherwise>
									            <c:out value="${cNews1.title}" />
									          </c:otherwise>
									      </c:choose>
									 </a>
								</td>
								
							 </tr>
							</c:forEach>
						</table>
					
					
					
					</div>

				</div>
			</div>
			
			<!-- 第五部分 end -->
			
			<!-- 底部  start-->
			<div id="bottom">
				<div style="height: 5px; background-color: #212C37; font-size: 1px"></div>
				<div style="height: 80px; background-color: #1D5FA0">
					<div align="center" style="padding: 10px 20px; color: #FFFFFF">
						学校地址：
						石家庄红旗大街626号 邮编：050000 联系方式：0311-85239666
						<br />
						<br />
						版权所有：河北工业职业技术学院 &nbsp; 建议使用 1024*768 分辨率浏览 &nbsp;
						<a href=http://www.miibeian.gov.cn /><font color=#ffffff>
							冀ICP备07025343号</font></a> Copyright &copy; 2008-2010
					</div>
				</div>
				<div style="height: 5px; background-color: #212C37; font-size: 1px"></div>
			</div>
			<!-- 底部  end-->

			<!-- 内容  end-->
		</div>

	</body>

</html>


