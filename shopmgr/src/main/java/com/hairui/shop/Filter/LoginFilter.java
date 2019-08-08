package com.hairui.shop.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        //获取请求路径
        String path = request.getServletPath();
        if(path.toLowerCase().indexOf("login")!=-1 || path.toLowerCase().indexOf("index.jsp")!=-1
                || path.toLowerCase().indexOf("resources")!=-1){
            /*toLowerCase()转换为全小写*/
            filterChain.doFilter(request,response);
        }else{
            HttpSession session = request.getSession();
            Object obj =session.getAttribute("user");
            if(obj!=null){
                filterChain.doFilter(request,response);
            }else{
                //使用绝对路径
                response.sendRedirect(request.getContextPath()+"/login?method=getJsp");
            }
        }
    }

    public void destroy() {
    }
}
