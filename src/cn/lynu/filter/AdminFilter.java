package cn.lynu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminFilter implements Filter {


	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest Request=(HttpServletRequest)request;
		HttpServletResponse Response=(HttpServletResponse) response;
		HttpSession session=Request.getSession();
		
		if(session.getAttribute("adminName")==null){
			Response.sendRedirect("../admin/login.html");
			chain.doFilter(request, response);
		}
		else{
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
