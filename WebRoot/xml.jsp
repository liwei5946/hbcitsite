<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<%@ include file="top001.jsp" %> 
<%@ include file="left0011.jsp" %> 
<%
String fname=new String();
fname=request.getParameter("fname").toString();


/*判断文件是否存在
String path=application.getRealPath(request.getRequestURI());  
String dir=new File(path).getParent();  
request.getContextPath();
out.println(request.getContextPath());


String filePath=request.getRealPath("").toString;
filePath+="/"+fname;
filePath=filePath.replace("/","//");
out.println(fielPath);

File file=new File(filePath );
         if(!file.exists())
             {
              out.print("文件不存在");
              return;
              }
*/
 %>	
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js"></script>

    <script type="text/javascript">  
        $(document).ready(function () {  
           
               $("#message").html('');  
               $.ajax({  
                    type: "GET",  
                    url: "<%=fname%>",  
                   dataType: "xml",  
                   success: function (ResponseText) {
						var table="";
						  $(ResponseText).find('list').each(function () {  
                            var name = $(this).find('name').text();  
                         	var tit = $(this).find('tit').text();  
                            var info = $(this).find('info').text(); 
						table+= "<div style='text-align: center'><img src='"+name+"' ><br><br></div>" ;
						table+= "<div align=center style=font-weight:bold;font-size:15px;color:#000099>"+tit+"<br></div>";
						table+="<div  style=font-size:13px;line-height:150%;color:#000033><pre>&nbsp;&nbsp;&nbsp;&nbsp;"+info+"</pre></div>";
						}); 
		                   $("#message").append(table);  
		              
                   }  
               });  
          });
        
    </script>  

 
  <!-- 内容right  start -->

  
  <div style="float:left ; ">
  
  <div  style="float:left ; ">
   <div style="background-image:url(images/1-right1.jpg); height:20px;width:762px  ">   </div>
   
   <div id="message" style=" padding-left:22px " >




 </div>
 </div>
      <div style="float:left; background-image:url(images/1-right2.jpg); width:40px; height:270px">
	    </div>
  <div class="clear"></div>
  </div>
   <!--内容right  end -->
   
   <%@ include file="foot001.jsp" %> 

