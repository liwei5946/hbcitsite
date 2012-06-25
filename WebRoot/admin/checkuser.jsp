<%
	if(session.getAttribute("userName") == null){
		//response.sendRedirect("<script type='text/javascript'> parent.location = '/WLPS/admin/login.do?method=closeSystem'; </script>");
		out.print("<script type='text/javascript'> parent.location = '/hbcitsite/admin/login.jsp'; </script>");
	}
%>