package com.hlf.provider.servlet;

import com.hlf.provider.handler.HttpServerHandle;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class DispatcherServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) {
        //责任链设计模式
        Object test = req.getAttribute("test");
        Optional.ofNullable(test).ifPresent(t -> System.out.println("test:" + t));
        new HttpServerHandle().handle(req, resp);
    }
}
