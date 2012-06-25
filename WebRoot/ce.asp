<!--#include file = conn.asp -->
<!--#include file = titleb.asp -->

<!--#include file = language.asp -->


<%
set rst1=server.createobject("adodb.recordset")
sqltext="select  * from news where tj='ÍÆ¼ö' order by updat desc"
rst1.open sqltext,conn,1,1
redim  a(6) 
redim  b(6) 
redim  c(6) 
i=0 
%>
<%do while not rst1.eof%>

<%a(i)=rst1("pic") %>
<%c(i)=left(rst1("title"),15) %>
<%b(i)="News_View.asp?NewsID="&rst1("id") %>
<%'=a(i)%>
<%
i=i+1
rst1.movenext  
	 loop 
	 	rst1.close%>





<script language="" type="text/javascript">
	<!--
	
	

	

    var focus_width=222;
    var focus_height=148;
    var text_height=30;
    var swf_height = focus_height+text_height

var swf_height = focus_height+text_height
var pics='<%=(a(0))%>|<%=a(1)%>|<%=a(2)%>|<%=a(3)%>|<%=a(4)%>|<%=a(5)%>'
var links='<%=(b(0))%>|<%=b(1)%>|<%=b(2)%>|<%=b(3)%>|<%=b(4)%>|<%=b(5)%>'
var texts='<%=(c(0))%>|<%=c(1)%>|<%=c(2)%>|<%=c(3)%>|<%=c(4)%>|<%=c(5)%>'

document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ focus_width +'" height="'+ swf_height +'">');
document.write('<param name="allowScriptAccess" value="sameDomain"><param name="movie" value="images/picswf.swf"><param name="quality" value="high">');
document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
document.write('<param name="FlashVars" value="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'">');
document.write('<embed src="images/picswf.swf" wmode="opaque" FlashVars="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'" menu="false" bgcolor="#F0EFEF" quality="high" width="'+ focus_width +'" height="'+ focus_height +'" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />');document.write('</object>');
//-->
</SCRIPT>
	
	
	
	

	
		 