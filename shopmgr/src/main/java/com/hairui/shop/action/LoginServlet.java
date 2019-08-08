package com.hairui.shop.action;

import com.hairui.shop.bean.User;
import com.hairui.shop.service.ShopService;
import com.hairui.shop.utils.Constants;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private HttpServletRequest request;
    private HttpServletResponse response;
    // 定义业务层对象
    private ShopService shopService;

    @Override
    public void init() throws ServletException {
        super.init();
        // 获取sping的容器。然后从容器中得到业务层对象
        ServletContext servletContext = this.getServletContext() ;
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        shopService = (ShopService) context.getBean("shopService");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.request = req;
        this.response= resp;
        String method = req.getParameter("method");
        if ("getJsp".equals(method)) {//跳转到登陆界面
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        } else if ("login".equals(method)) {
            login();
        }
    }

    private void login() {

        try {
            String LoginName = request.getParameter("loginName");
            String PassWord = request.getParameter("passWord");
            Map<String, Object> results = shopService.login(LoginName, PassWord);
            if((Integer) results.get("code") == 0){
                User user = (User)results.get("msg");
                request.getSession().setAttribute("user",user);
                //请求跳转带主界面的servlet
                response.sendRedirect(request.getContextPath()+"/list?method=getAll");
            }else{
                String msg = (String) results.get("msg");
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
