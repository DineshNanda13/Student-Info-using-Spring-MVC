# Student-Info-using-Spring-MVC
Illustration of basic architecture of spring MVC, writing the logic in controller java file, passing message to the model and using view templates to create the web application.

//File name : web.xml

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
	id="WebApp_ID" version="3.1">

	<display-name>student-info</display-name>

	<absolute-ordering />

	<!-- Spring MVC Configs -->

	<!-- Step 1: Configure Spring MVC Dispatcher Servlet -->
	<servlet>
		<servlet-name>dispatcher</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/Student-servlet.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<!-- Step 2: Set up URL mapping for Spring MVC Dispatcher Servlet -->
	<servlet-mapping>
		<servlet-name>dispatcher</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
	
</web-app>

//File name: Student-servlet.xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans
    	http://www.springframework.org/schema/beans/spring-beans.xsd
    	http://www.springframework.org/schema/context
    	http://www.springframework.org/schema/context/spring-context.xsd
    	http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">

	<!-- Step 3: Add support for component scanning -->
	<context:component-scan base-package="com.studentInfo" />

	<!-- Step 4: Add support for conversion, formatting and validation support -->
	<mvc:annotation-driven/>

	<!-- Step 5: Define Spring MVC view resolver -->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/view/" />
		<property name="suffix" value=".jsp" />
	</bean>

</beans>

//File name: HomeController.java

package com.studentInfo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String showHomePage() {
		
		return "welcome";
	}

}

//File name: EnterDetails.java

package com.studentInfo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EnterDetails {
	
	@RequestMapping("/showStudentForm")
	public String showStudentForm() {
		
		return "enter-details";
	}
	
	@RequestMapping("/processStudentForm")
	public String processStudentForm(HttpServletRequest request, Model model) {
		
		String theName = request.getParameter("studentName");
		theName = theName.toLowerCase();
		String result = "Hi "+theName;
		
		model.addAttribute("message",result);
		
		return "get-details";
	}

}

//file name : welcome.jsp

<!DOCTYPE html>

<html>

<body>

<h2>Welcome to Spring MVC</h2>

<a href="showStudentForm">Go to Form</a>

</body>

</html>

// file name : enter-details.jsp

<!Doctype html>

<html>

<body>

<h2>Student Form</h2>

<form action="processStudentForm" method="get">

<input type="text" name="studentName" placeholder="Enter your name"><br><br>

<input type="text" name="studentID" placeholder="Enter your ID"><br><br>

<input type="submit" value="Submit">

</form>

</body>

</html>

// file name : get-details.jsp

<!Doctype html>

<html>

<body>

<h2>Your Details</h2>

Student Name : ${param.studentName}
<br><br>
Student ID : ${param.studentID}
<br><br>
Message : ${message}

</body>

</html>
