package com.example.springmall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller	//스프링에서는 콘트롤러를 갖고있으면 서블릿으로 처리한다
public class HiController {
	@RequestMapping("/hi")
	public String hi() {
		System.out.print("Hi Spring boot!");
		return "hi";	//forward -> WEB-INF/jsp/hi.jsp
	}
}
