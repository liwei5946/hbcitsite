<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.6.min.js"></script>
<script type="text/javascript">  
12.        $(document).ready(function () {  
13.           
14.                $("#message").html('sdafsdaasdfsd');  
15.                $.ajax({  
16.                    type: "GET",  
17.                    url: "1_22.xml",  
18.                    dataType: "xml",  
19.                    success: function (ResponseText) {
						var table="";
						  $(ResponseText).find('list').each(function () {  
22.                            var name = $(this).find('name').text();  
23.                            var tit = $(this).find('tit').text();  
24.                            var info = $(this).find('info').text(); 
						table+= "<div style='text-align: center'><img src='"+name+"' ><br><br></div>" ;
						table+= "<div align=center style=font-weight:bold;font-size:15px;color:#000099>"+tit+"<br></div>";
						table+="<div  style=font-size:13px;line-height:150%;color:#000033><pre>&nbsp;&nbsp;&nbsp;&nbsp;"+info+"</pre></div>";
						}); 
		                   $("#message").append(table);  
		                   $("#message").append("adslkfjasdkl"); 
29.                    }  
30.                });  
31.          });
32.        
33.    </script>  
</head>
<body>
<div id=message></div>
</body>
</html>