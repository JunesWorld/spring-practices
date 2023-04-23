# spring-practices

## Project 생성

### [Project Explorer]
[new] - [Maven Project] - [Create a simple project] - [Group Id] com.juesworld / [Artifact Id] spring-practices / [Packaging] pom(부모) - src 삭제

### [Navigator]
[.gitignore] File 생성 
> **/.settings
> **/target
> **/.classpath
> **/.project

### [Github 연동]
- [Project Explorer] - [Team] -[Share Project] - 모두 check! - [Create Repository] - [Finish]
- [Git Repository] - [Remotes] - [Create Remotes] - [Configure fetch] -[Create] - Github URL 복사 후 입력 

### [하위 프로젝트 생성]
- (helloweb / MVC spring version)
- [new] - [Maven Project] - [Create a simple project] - [Module name] helloweb - [packaging] war(자식)
- Web.xml 생성 (DispatcherServlet 등록)
    - [Java EE tools] - Generate
- Pom.xml 설정
- Tomcat 설정
    - [properties] - [Targeted Runtimes] - [New] - [Apache Tomcat v9.0] - [Apply] - [Apply & Close]
- /WEB-INF/spring-servlet.xml 생성
    - 서블릿 애플리케이션 컨텍스트 설정 
    - Annotation(@Controller) 사용하기 위한 config 설정

---

## 개념

### [Structure]
- Spring Container FrameWork : Core
- Spring MVC : Bean들이 도와준다
- Spring DAO : Database & Business가 모두 들어 있다 / 여러 객체 & 의존성 관리

- Controller
    - Frontend : Servlet controller
    - Backend : 내가 만든 controller

### [Flow]
1. 사용자의 요청을 DispatcherServlet이 받는다.

2. 요청을 처리해야 하는 컨트롤을 찾기 위해 HandlerMapping에게 질의를 하고 HandlerMapping은 컨트롤 객체에 매핑되어 있는 URL를 찾아낸다.

3. DispatcherServlet은 찾은 컨트롤에게 요청을 전달하고 Controller는 서비스 계층의 인터페이스를 호출하여 적절한 비지니스를 수행한다.
	
4. 컨트롤러는 비지니스 로직의 수행결과로 받아낸 도메인 모델 객체와 함께 뷰이름을 ModelAndView 객체에 저장하여 반환한다.

5. DispatcherServlet은 응답할 View를 찾기 위해 ViewResolver에게 질의를 한다.

6. DispatcherServlet은 찾아낸 View 객체에게 요청을 전달한다.

---

1. 요청
> - Servlet이 받는다
> - 이 행위를 Tomcat이 관리(WAS -> Servlet/Enterprise Java Bean Container)
> - Enterprise Java Bean Container : 기업용 Application 비지니스를 구현해 놓은 Java로 만든 객체들 / 객체 재사용이 어려움

2. Tomcat
> - 설정 -> Web.xml -> Dispatch Servlet
> - /helloweb(Servlet -> DispatcherServlet)

3. init( ) { }
> - container 설정(Web Application Context)
>   - 설젇 File : spring-servlet.xml (Config 설정 필수!) 
>   - Bean 자동 생성(MVC 도와주는 세가지 Bean) / 2,3 -> Controller Return 처리 / Bean을 Annotation으로 생성하겠다.
>       1. HandlerMapping(Bean생성) -> Controller 실행
>           - URL : /hello
>           - Controller : 내부 Scanning / 필수) @RequestMapping, @Controller, Return
>           - Method Name : hello
>           - Parameter 정보 -> Servlet 처리
>           - ResponseBody
>       2. ViewResolver : JSP
>       3. MessageConvertor : Ajax 통신

4. Service

---
## ViewResolver

 1. ViewResolver는 HandlerMapping이 컨트롤러를 찾아주는 것 처럼, View 이름을 가지고  View 오브젝트를 찾아준다.

 2. ViewResolver 를 빈 등록하지 않으면 DispatcherServlet의 기본 ViewResolver 인 InternalResourceViewResolver가 사용된다. 

 3. 디폴트 사용에서는 View 로 이동하는 전체 경로를 다 적어 주어야 한다.

 4. prefix와 suffix를 지정하여 앞 뒤의 내용을 생략하여 매우 편리하게 View를 지정할 수 있다.     
