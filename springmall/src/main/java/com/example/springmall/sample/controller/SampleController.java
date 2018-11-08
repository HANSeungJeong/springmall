package com.example.springmall.sample.controller;

import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmall.sample.service.SampleService;
import com.example.springmall.sample.vo.Sample;

@Controller
/* @Controller는 Spring MVC의 Controller 클래스 선언을 단순화. 스프링 컨트롤러, 서블릿을 상속할 필요가 없으며, @Controller로 등록된 클래스 파일에 대한 bean을 자동으로 생성해준다
(component-scan 이용)*/
public class SampleController {
	@Autowired
	// @Autowired은 의존관계를 자동설정할 때 사용하며 타입을 이용하여 의존하는 객체를 삽입해 준다. (해당 타입의 빈객체가 존재하지 않거나 또는 2개 이상 존재할 경우 스프링은 예외를 발생시킨다.)주입 

	public SampleService sampleService;
	/*
	 * DATA(변수) + FUNCTION(제어문,연산자)
	 */
	
	
	// 4-1. 수정 폼
	/*@RequestMapping은 URL을 컨트롤러의 메서드와 매핑할 때 사용하는 스프링 프레임워크의 어노테이션이다.
		클래스나 메서드 선언부에 @RequestMapping과 함께 URL을 명시하여 사용한다. URL외에도 HTTP 요청 메서드나 헤더값에 따라 매핑되도록 -0=옵션을 제공한다. 메서드 레벨에서 정의한 @RequestMapping은 타입 레벨에서 정의된 @RequestMapping의 옵션을 상속받는다.
		참고로, 메서드 내에서 viewName을 별도로 설정하지 않으면 @RequestMapping의 path로 설정한 URL이 그대로 viewName으로 설정된다*/
	//	 수정 메서드 (update할 멤버를 가지고  update form 으로이동 )
	@RequestMapping(value = "/sample/modifySample", method = RequestMethod.GET)
	public String modifySample(Model model, @RequestParam(value = "sampleNo") int sampleNo) {
		Sample sample = sampleService.getSample(sampleNo);
		model.addAttribute("sample", sample);
		return "/sample/modifySample";
	}
	// 4-2. 수정 액션
	@RequestMapping(value = "/sample/modifySample", method = RequestMethod.POST)
	public String modifySample(Sample sample) {
		if(sampleService.modifySample(sample) == 1) {
			System.out.println(sample.getSampleNo()+"번 데이터 수정 성공");
		} else {
			System.out.println(sample.getSampleNo()+"번 데이터 수정 성공");
		}
		return "redirect:/sample/sampleList";
	}
	// 3-1. 입력 폼
	@RequestMapping(value = "/sample/addSample", method = RequestMethod.GET)
	public String addSample() {
		return "/sample/addSample";
		// jquery, bootstrap, Sample command객체를 사용할 것을 염두해두자
	}
	// 3-2. 입력 액션
	@RequestMapping(value = "/sample/addSample", method = RequestMethod.POST)
	public String addSample(Sample sample) { 
		// 커맨드 객체 멤버 변수의 이름과 input태그 name의 이름이 같아야함, setter를 호출하므로 표준 setter가 필요하다.
		if(sampleService.addSample(sample) == 1) {
			System.out.println("ID:"+sample.getSampleId()+"인 데이터 추가 성공");
		} else {
			System.out.println("ID:"+sample.getSampleId()+"인 데이터 추가 실패");
		}
		return "redirect:/sample/sampleList";
	}
	//2.삭제
	@RequestMapping(value="/sample/removeSample",method=RequestMethod.GET)
	public String removeSample(@RequestParam(value="sampleNo")int sampleNo) {
		if(sampleService.removeSample(sampleNo)==1) {
			System.out.println(sampleNo+"번 데이터 삭제성공");
		} else {
			System.out.println(sampleNo+"번 데이터 삭제실패");
		}
		return "redirect:/sample/sampleList";
	}
	//1. 샘플리스트
	@RequestMapping(value="/sample/sampleList",method=RequestMethod.GET)
	public String sampleList(Model model,@RequestParam(value="currentPage",required=false, defaultValue="1") int currentPage) {	//Model model = new Model();;
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("currentPage", currentPage);
		System.out.println("currentPage:"+currentPage);
		List<Sample> sampleList = sampleService.getSampleAll(map);
		model.addAttribute("sampleList",sampleList);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("lastPage",(int)map.get("lastPage"));
		System.out.println("lastPage:"+(int)map.get("lastPage"));
		return "/sample/sampleList";		
	}	
}
