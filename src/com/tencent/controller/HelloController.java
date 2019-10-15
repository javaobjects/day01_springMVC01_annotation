package com.tencent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
* <p>Title: HelloController</p>  
* <p>
*	Description: 
*   	处理器
* </p> 
* @author xianxian 
* @date 2019年10月15日
 */
@Controller
public class HelloController{

	@RequestMapping("/hello.action")//定访问路径
	public ModelAndView hello() {

		ModelAndView mav = new ModelAndView();
		
		//1. 保存数据，相当于request.setAttribute("message",""恭喜你，成功访问第一个springMVC环境........"");
		mav.addObject("message","恭喜你，成功访问第一个springMVC环境........");
		
		//2. 跳转页面,相当于request.getRequestDispather("/main.jsp").forward(request.response);
		mav.setViewName("/main.jsp");
		
		
		return mav;
	}

}
