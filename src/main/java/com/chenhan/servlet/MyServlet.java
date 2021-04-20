package com.chenhan.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*注解的方式配置servlet，还可以@Bean覆盖ServletRegistrationBean，在此类中添加，详见本项目ServletURLConfig*/
//@WebServlet(name = "myServlet", urlPatterns = {"/myServlet","/myServlet1"})
public class MyServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          //重写需要删除此行，此方法调用父类HttpServlet的doGet()方法，不需要
//        super.doGet(req, resp);

        System.out.println("reqURI:" + req.getRequestURI());
        System.out.println("reqURL:" + req.getRequestURL());

        resp.setContentType("text/html;charset=UTF-8");//必须在获取PrintWriter之前，否则无效
        PrintWriter writer = resp.getWriter();
        writer.print("已经返回了");

        writer.flush();
        writer.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    //此行同doGet
        //        super.doPost(req, resp);

        //如果在doPost里调doGet，那么无论前台是用Get还是Post方式，最终都会进入相同的处理逻辑
//        this.doGet(req, resp);
        System.out.println("post方法");


    }
}
