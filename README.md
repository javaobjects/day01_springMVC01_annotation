# springMVC注解方式环境搭建

#### 1. jar包导入

![](WebContent/Images/6.png)

#### 2. spring-mvc.xml web.xml配置

```xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:context="http://www.springframework.org/schema/context"
	   xmlns:aop="http://www.springframework.org/schema/aop"
	   xmlns:mvc="http://www.springframework.org/schema/mvc"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	   xsi:schemaLocation="http://www.springframework.org/schema/beans 
	                       http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
	                       http://www.springframework.org/schema/context
	                       http://www.springframework.org/schema/context/spring-context-4.1.xsd
	                       http://www.springframework.org/schema/aop
	                       http://www.springframework.org/schema/aop/spring-aop-4.1.xsd
	                       http://www.springframework.org/schema/mvc
	                       http://www.springframework.org/schema/mvc/spring-mvc-4.1.xsd">


<!--【非注解】start  -->
		<!-- 
			2. 【非注解】处理映射器:负责根据请求的url查找指定的处理器
			-->
		<!-- 方式一:BeanNameUrlHandlerMapping根据用户请求的url匹配bean的name属性值 -->
	<!-- 	<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"></bean> -->
		
		<!-- 方式二: SimpleUrlHandlerMapping 根据用户请求的url匹配bean的id属性值,属于方式一的增强版，与方式一共存 -->
<!-- 		<bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
			<property name="mappings">
				<props>
					<prop key="/hello2.action">helloController</prop>
					<prop key="/hello66.action">helloController2</prop>
				</props>
			</property>
		</bean> -->
		
		
		
	
		<!-- 
			4. 【非注解】处理适配器：负责适配执行处理器 
			-->
		<!-- 方式一:SimpleControllerHandlerAdapter,适配执行所有实现了org.springframework.web.servlet.mvc.Controller接口类 -->
<!-- 		<bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"></bean> -->
		<!-- 方式二:HttpRequestHandlerAdapter适配执行所有实现了org.springframework.web.servlet.mvc.
		HttpRequestHandlerAdapter接口类 ,可以与方式一共存-->
		<!-- <bean class="org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter"></bean> -->
		
			<!-- 3. 处理器 -->
	<!-- 	<bean id="helloController" name="/hello.action" class="com.tencent.controller.HelloController"></bean>
		<bean id="helloController2"  class="com.tencent.controller.HelloController2"></bean> -->
		
		<!-- 5. 视图解析器 -->
	<!-- 	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"></bean> -->
							<!-- 【非注解】end -->
							<!-- 【注解】start -->



	<!-- 
			2. 【注解】处理映射器:负责根据请求的url查找指定的处理器
			-->
  		<!-- <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping"></bean> -->

	<!-- 4. 【注解】处理器适配器：负责适配执行处理器 -->
		<!-- <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"></bean> --> 
							<!-- 【注解】end -->

		<!-- 用于替换步骤2、4的标签，同时开启注解方式的映射器与适配器 -->
		<mvc:annotation-driven></mvc:annotation-driven>
			<!-- 3. 处理器 -->
		<!-- <bean id="helloController"  class="com.tencent.controller.HelloController"></bean> -->
		
		<!-- 用于替换步骤3中对controller的管理 -->
		<context:component-scan base-package="com.tencent.controller"></context:component-scan>
		
		<!-- 5. 视图解析器 -->
		<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"></bean>
</beans>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
  <display-name>day01_springMVC00_noannotation</display-name>
  
  <!-- 1. 前端控制器:负责接收请求与响应结果 -->
  <servlet>
  		<servlet-name>springMVC</servlet-name>
  		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  		
  		<!-- 配置springMVC的核心配置文件路径spring-mvc.xml -->
  		<init-param>
  			<param-name>contextConfigLocation</param-name>
  			<param-value>classpath:spring-mvc.xml</param-value>
  		</init-param>
  </servlet>
  
  <servlet-mapping>
  		<servlet-name>springMVC</servlet-name>
  		<url-pattern>*.action</url-pattern>
  </servlet-mapping>
  
  
  <!-- 欢迎首页 -->
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
</web-app>
```
#### 3. Java和前端代码

```Java
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
```

```	Jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="hello.action">测试springMVC【注解】环境搭建</a><br/><br/>
	
</body>
</html>
```
```Jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${message}
</body>
</html>
```

#### 4. 测试


