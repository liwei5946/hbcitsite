package cn.edu.hbcit.util;

import java.io.IOException;

import javax.servlet.Filter; 
import javax.servlet.FilterChain; 
import javax.servlet.FilterConfig; 
import javax.servlet.ServletException; 
import javax.servlet.ServletRequest; 
import javax.servlet.ServletResponse; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; 

public class CopartnerAuthFilter implements Filter { 
    public void destroy() {} 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{ 
       HttpServletRequest httpRequest = (HttpServletRequest) request; 
       HttpSession session = httpRequest.getSession(); 
       boolean isLoggedIn = session.getAttribute( "userName" ) != null; 
       if (!isLoggedIn) { 
    	   	request.getRequestDispatcher( "/admin/login.jsp" ).forward(request, response); 
    	   //((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/admin/login.jsp" );
    	   //((HttpServletResponse)response).sendRedirect("/hbcitsite/admin/login.jsp" ); 
       } else { 
           chain.doFilter(request, response); 
       } 
    } 
    public void init(FilterConfig arg0) throws ServletException {} 

} 

