package com.hlf.provider.servlet;

import com.hlf.provider.handler.HttpServerHandle;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        //责任链设计模式
        new HttpServerHandle().handle(req, resp);
    }
}
