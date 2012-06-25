<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%  

  session.invalidate();
   response.setHeader("refresh","0;URL=login.jsp") ;     
%>
