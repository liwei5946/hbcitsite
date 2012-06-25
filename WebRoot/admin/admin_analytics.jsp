<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="checkuser.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<!--admin_analytics.jsp-->
    <title>访问量统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/admin/css/subcss.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/admin/css/jquery.jqplot.min.css"> 
    
<style type="text/css"> /*注意选择器的层叠关系*/
	.font {TEXT-ALIGN: center;COLOR: blue;FONT-SIZE: 16px; FONT-WEIGHT: bold;}
	.buttonStyle{width:64px;height:22px;line-height:22px;color:#369;text-align:center;background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left top;border:0;font-size:12px;}
	.buttonStyle:hover{background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left -23px;}
</style>
    
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery.jqplot.min.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/excanvas.min.js"></script>
<![endif]-->  
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jqplot.categoryAxisRenderer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jqplot.canvasAxisTickRenderer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jqplot.canvasTextRenderer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jqplot.barRenderer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jqplot.pointLabels.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jqplot.highlighter.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/admin/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/zDialog.js"></script>
     
<script type="text/javascript" language="javascript">
 var pageviewsJson ;
 var visitsJson ;
 var visitorsJson ;
 var plot1;
function defquery(){
 if($('#st').val() <= $('#et').val()){
 $.ajax({
				url :"${pageContext.request.contextPath }/servlet/AnalyticsPageviewsServlet",
				type : 'post',
				async:false,
				dataType:'json',
			    data : 'st='+$('#st').val()+'&et='+$('#et').val(),
				success :function(mm){
					
					pageviewsJson = mm;
						}
				});	
  $.ajax({
				url :"${pageContext.request.contextPath }/servlet/AnalyticsVisitsServlet",
				type : 'post',
				async:false,
				dataType:'json',
			    data : 'st='+$('#st').val()+'&et='+$('#et').val(),
				success :function(mm){
					
					visitsJson = mm;
						}
				});	
   $.ajax({
				url :"${pageContext.request.contextPath }/servlet/AnalyticsVisitorsServlet",
				type : 'post',
				async:false,
				dataType:'json',
			    data : 'st='+$('#st').val()+'&et='+$('#et').val(),
				success :function(mm){
					
					visitorsJson = mm;
						}
				});	
  
    $('#chart').empty(); 
    plot1 = $.jqplot('chart', [pageviewsJson,visitsJson,visitorsJson], {
    
	axesDefaults: {
        tickRenderer: $.jqplot.CanvasAxisTickRenderer ,
        tickOptions: {
          angle: -40,
          fontSize: '10pt',
		  fontFamily: 'Tahoma',
		  fontWeight:'bold'
        }
    },
	seriesDefaults: {
        showMarker:true,
        pointLabels: {
          show: true,
          edgeTolerance: 5
        },
		markerOptions: {      
            show: true,             // 是否在图中显示数据点     
            style: 'filledCircle' // 各个数据点在图中显示的方式，默认是"filledCircle"(实心圆点),     
         }
		},
    series:[
			{label:'浏览量'},
            {label:'访问量'},
			{label:'访问人数'},
			{renderer:$.jqplot.CanvasAxisTickRenderer}
			],
	
	legend: {
            show: true,
            placement: 'outsideGrid',
			location: 'se'
        },
    
    axes: {
      xaxis: {
        renderer: $.jqplot.CategoryAxisRenderer
      }
    },
	highlighter: {
		show: true,
		sizeAdjust: 7,
		showTooltip: true,
		tooltipSeparator: ': '
		
	}
  });
	}//判断结束
	else{
		Dialog.alert("起始日期不能小于结束日期!");
	}
};
</script>

</head>
<body onLoad="defquery()" style="margin-top:10px;"  bgcolor="#ffffff" leftmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
<div align="center">
	<div class="title">外网访问量统计图<hr></div>
</div>

<div align="center" style="background-image:url(${pageContext.request.contextPath }/admin/images/bj3.jpg)">
<table border="0" cellpadding="0" cellspacing="0" width="744"	id="table3">
<tr>
<td width="60%" height=20 align=center>
请选择时段：
<input readonly id="st" type="text" name="st" size="18" value="" maxlength="50">
<img onClick="WdatePicker({el:'st'})" src="${pageContext.request.contextPath }/admin/My97DatePicker/skin/datePicker.gif"width="16" height="22" align="middle">至
<input readonly id="et" type="text" name="et" size="18" value="" maxlength="50">
<img onClick="WdatePicker({el:'et'})" src="${pageContext.request.contextPath }/admin/My97DatePicker/skin/datePicker.gif"width="16" height="22" align="middle">
<input type="button" value="开始统计" name="B2" class="buttonStyle" onClick="defquery();">
</td>
</tr>
</table>
</div>

<div align="center">
  <div id="chart" style="margin-top:2px; margin-left:20px; width:840px; height:460px;"></div>
</div>

</body>
</html>
