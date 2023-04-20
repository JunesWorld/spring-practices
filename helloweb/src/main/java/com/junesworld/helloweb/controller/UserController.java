package com.junesworld.helloweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * 추천!
 * @RequestMapping 클래스 + 메소드
 * 같은 URL, 다른 메소드 
 * 
 * 변수이름은 상세히, 파라미터는 보여지는 의미 없게
 * 
 * 기본값 세팅, 400으로 가지 않도록!
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "/WEB-INF/views/join.jsp";  // forward
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo vo) {
		System.out.println(vo);
		return "redirect:/";  // redirect
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public String update(@RequestParam("n") String name) {  
		// "n"; 변수 이름 같아도 써주기, 다르면 꼭 쓰기
		// localhost:8080/helloweb/user/update?n=junesworld 
		/*
		 * 만일 n이라는 이름의 URL 파라미터가 없는 경우
		 * 400(Bad Request) 에러가 발생한다.
		 */
		return "UserController.update(" + name + ")";
	}
	
	@ResponseBody
	@RequestMapping("/update2")
	// @RequestParam; value(변수에 대한 파라미터), required(필수여부), required=false(bad request가 뜨는게 싫으면)면 defaultValue 반드시 주기
	// required=true인데 입력하지 않을 경우 대비 예외처리하거나 그냥 defaultValue 주기
	public String update2(@RequestParam(value="n", required=true, defaultValue="") String name) {
		return "UserController.update(" + name + ")";
	}
	
	@ResponseBody
	@RequestMapping("/list")  // int여도 값 지정은 "1", int로 spring이 형변환 해줌
	public String list(@RequestParam(value="p", required=true, defaultValue="1") int pageNo) { 
		return "UserController.list(" + pageNo + ")";
	}
}

/* GET 방식
 - URL에 쿼리 스트링(데이터)을 붙여서 서버에 전송
 - 데이터를 Header(헤더)에 포함하여 전송
 - URL에 정보들이 그대로 노출되기 때문에 POST 방식보다 상대적으로 보안에 취약
 - 캐싱이 가능
 - POST 방식보다 상대적으로 전송 속도가 빠름
 - 전송하는 데이터양에 한계가 있음 (브라우저마다 GET 요청 길이 제한 존재)
 - 브라우저 히스토리에 기록이 남음
*/
/* POST 방식
 - 데이터들을 URL뒤에 붙여서 서버로 보내는 것이 아닌 Body에 담아서 전송
 - 요청 헤더의 Content-Type에 콘텐츠 타입을 명시
 - 데이터들이 URL에 노출되지 않기 때문에 GET 방식보다 상대적으로 보안적
 - 데이터들을 Body에 담기 때문에 서버로 보내는 데이터의 양은 제한 없음
 - URL에 데이터가 노출되지 않으므로 캐싱 불가
 - 클라이언트에서 인코딩, 서버에서 디코딩
 - 요청받는 시간제한 존재
 - 브라우저 히스토리에 기록이 남지 않음
*/ 
