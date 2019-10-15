package com.tencent.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

public class HelloController2 implements HttpRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//1. 保存数据，相当于request.setAttribute("message",""恭喜你，成功访问第一个springMVC环境........"");
				request.setAttribute("message","恭喜你，成功访问第一个springMVC环境.......HttpRequestHandler接口");
				
				//2. 跳转页面,相当于request.getRequestDispather("/main.jsp").forward(request.response);
				request.getRequestDispatcher("/main.jsp").forward(request, response);
	}

}
