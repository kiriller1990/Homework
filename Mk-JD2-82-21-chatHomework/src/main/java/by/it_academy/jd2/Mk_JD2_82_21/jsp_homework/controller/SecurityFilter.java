package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*@WebFilter(urlPatterns = {"/chats","/message"})
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();
        if((session!=null) &&(session.getAttribute("user") !=null)) {
            chain.doFilter(request,response);
        }else {
            resp.sendRedirect(contextPath +"/signIn");
        }
    }

    @Override
    public void destroy() {

    }
}*/
