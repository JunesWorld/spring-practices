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
- /WEB-INF/applicationContext.xml
	- Business 구현 : DAO, Service

### logex
- 기본적으로 사용하고 있는 coommon-logging 말고 성능이 좋은 Logback 사용
- pom.xml -> JCL 제외 & Logback 추가!
- web.xml : Dispather Servlet 설정!
- logback.xml 설정(package 이름 & level) 후 Tomcat 실행
	- localhost:8080/logex/ex2 실행
	- VScode에서 logex2.log 확인
	- Level 순위 
		1. Debug
		2. Info
		3. Warn
		4. Error
### Interceptor
- helloweb
- [Package] com.junesworld.helloweb.Interceptor
	- [Class] MyInterceptor01(추상 method를 만들고 override 하는 방법)
		- [Add Interface] HandlerInterceptor
		- return을 true로 해주어야 Interceptor가 응답하고 다음 controller로 넘어간다.
	- [Class] MyInterceptor02(부모에서 만들고 필요한 것만 overriding해서 사용) -> 1번 기본 구현 다 되어있다.
		- [Superclass] HandlerInterceptorAdaptor
- spring-servelt.xml 설정 -> <mvc:interceptor>
- UserController
	- /user/login 오는 것을 LoginInterceptor를 만들고 return false;
	- Controller까지 가지 않아도 된다.
	- logout도 마찬가지
	
### FileUpload
- multipart -> Spring에 있는 Multipart Resolver(Common fileupload(Library))가 처리 -> MultipartFile에 담아서 Controller에 넘겨주어 Handler 넣어 처리
	- name=이성준&email=asdfa@gmail.com&password=1234&file=asdfasf...가 아니고 part별로 분리하여(name/email/file) 분리하여 표현  
	- name, email은 encoding / file은 encoding X
- pom.xml & spring-servlet.xml 설정
- 유저들이 파일을 업로드 할 때 똑같은 이름으로 할 경우 덮어쓰기 때문에 Server를 통과하고 저장할 때 파일 이름을 유일한 내용으로 변경(초 단위로 변경)해서 저장해야한다. 다만 확장자는 유지해야한다.
- Controller & Service 
- 파일 저장 위치는 Server!
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
> - 이 행위를 Tomcat이 관리(WAS -> Servlet/Enterprise Java Bean Container) : WAS에서 DispathServlet을 처리해준다. 
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
# [MySite]
## 정적 자원 접근 실패 
   - html, css, js 등의 파일 접근에 실패한다.

   - DispatcherServlet 이 모든 URL처리에 서블릿 매핑을 하였기 때문에 톰캣은 정적 자원에 대한 URL처리도
      DispatcherServlet에게 넘기기 때문이다. ( 즉, DefaultServlet에 위임을 하지 못한다. )
      
   - Spring MVC 에서 DefaultServlet 위임 처리하기
   
   - HandlerMapping이 URL과 컨트롤러의 메소드(핸들러) 와의 매핑 정보를 가지고 있다.

   - HandlerMapping에서 정적 자원에 대한 URL은 DefaultServlet으로 위임할 수 있도록 설정 해주어야 한다.

   - spring-servlet.xml 파일에서 

	<validator, conversionService, messageConverter를 자동으로 등록>
		- <mvc:annotation-driven />
	<서블릿 컨테이너의 디폴트 서블릿 위임 핸들러>
		- <mvc:default-servlet-handler/>

---
## ViewResolver(수동 Bean설정)

 1. ViewResolver는 HandlerMapping이 컨트롤러를 찾아주는 것 처럼, View 이름을 가지고  View 오브젝트를 찾아준다.

 2. ViewResolver 를 빈 등록하지 않으면 DispatcherServlet의 기본 ViewResolver 인 InternalResourceViewResolver가 사용된다. 

 3. 디폴트 사용에서는 View 로 이동하는 전체 경로를 다 적어 주어야 한다.

 4. prefix와 suffix를 지정하여 앞 뒤의 내용을 생략하여 매우 편리하게 View를 지정할 수 있다.     

<!–- ViewResolver 설정 (spring-servlet.xml)-->
```
<bean id=＂viewResolver＂ class=＂org.springframework.web.servlet.view.InternalResourceViewResolver＂>
   <property name=＂viewClass" value="org.springframework.web.servlet.view.JstlView" />
   <property name="prefix" value="/WEB-INF/views/" />
   <property name="suffix" value=".jsp" />
</bean>
```
- prefix & suffix = 접두사 & 접미사
	- /WEB-INF/views/(view name).jsp 
---
## [Mysite03]
> - Root Application Cotext 
> - UserService(UserRepository userRepository -> DI(의존성 주입)) / UserRepository
> - UserController(UserService userService ->  DI(의존성 주입))
 
- com.bitacademy.mysite.controller
	- UserController(/user) : /join, /login, /update, /logout 
- com.bitacademy.mysite.service
 	- UserService
- com.bitacademy.mysite.repository
- com.bitacademy.mysite.vo
- com.bitacademy.mysite.exception
	- UserRepositoryException : 생성 시 superclass(RuntimeException으로 변경할 것)
		- Repository에서 RuntimeException으로 감싸서 윗단으로 올리는 것
		- 위 Class 생성하면 Service, Controller에서 SQLException 처리 할 필요 X 
	- ControllerExceptionHandler -> Spring-servlet.xml에서 설정해야 한다.
		- @ControllerAdvice : 모든 Controller에 적용

- AOP = Exception 처리 (try~catch)
	- Repsitory에서 try/catch를 하지 않고 throws를 하면 윗단인 Service에서 try~catch해줘야한다(=throws는 안좋은 방법)
	- 즉, 아랫단에서 Runtime Exception으로 Mapping 후 윗 단(Controller)에서 try~catch 처리한다.
		- Repository에서 RuntimeException 
	- 결국, Controller까지 throws를 하면 Servlet에서 처리해야한다.(Web.xml -> Common Error Page처리 후 500.jsp 페이지 만들어야 한다.)
	- 모든 Controller의 Exception처리 -> GlobalExceptionHandler
	- 500.jsp Error : Servlet, Filter, Listener Error!
	- GlobalExceptionHandler(exception.jsp) Error : 내가 만든 코드(Controller/Service/Repository) Error!
	
- DataSource(pom.xml 설정 -> [Apache Common DBCP])
	- Bean설정(applicatinContext.xml -> DB정보 설정)
		- DB -> DataSource -> Repository(DataSource dataSource) -> Service(Repository repository)
		- UserRepository에서 Datasource 설정
	
### MyBatis(Library) = ORM(Object Relation Mapping) = JPA를 구현한 실체 => pom.xml에서 설정
- MyBatis = Object (Relation X) <-> SQL Mapping(Parameter & Result) => Mapper(XML, Interface)
- Session
	- Bean 설정 (Datasource, SessionFactory, SessionTemplate) => application-Context.xml에서 설정(xml에서 DI하는 과정)
	- Session Factory는 DataSource를 주입 받아야 하고 Mapper(XML)로 SQL, Param, Result 바인딩 
	- session = sf.getSession() -> Session Template은 Session Factory 주입 받아야 한다.
		```
		1. 연결하기 = session.getConnection
		2. Transaction 시작
		3. Statement 준비
		4. 파라미터 바인딩
		5. Query 실행 / 처리
		6. Result 처리
		7. Transaction 종료
		8. 연결종료 
		```
	- Repository는 SessionTemplate 주입 받아야 한다.
	- application.xml에서 SqlSessionFactoryBean, SqlSessionTemplete 설정


- src/main/resources/mybatis/
	- mappers/user.xml -> UserRepository에 있는 SQL문 옮겨주고 삭제
		- id = method명 / <!CDATA[ "SQL Query"]]>
	- configuration.xml -> user.xml에서 너무 길어지기 때문에 alias설정(parametertype - 소문자로 쓰는게 관례)
	
- 구조도
	Tomcat
	|- webapps
		|- mysite03
			|- WEB-INF
				|- web.xml
				|- libs
				|- classes
					|- mybatis
						|- cofiguration.xml -> src/main/resources
					|- com
						|- bitacademy
							|- mysite
								|- controller
									|- MainController.class
- pom.xml -> spring jdbc 설정

### Logger
- concept
	- 원래 Exception을 sys.out으로 처리한 것을 logback 사용
- pom.xml -> jcloverslf4j & logback 설정
- src/main/resources/logback.xml -> appender : file과 연결
- ControllerExceptionHandler 수정
- Test : user.xml에서 Query문 일부로 Error 내고 테스트 진행

### Interceptor
- mysite03
- [Package] com.bitacademy.mysite.security
	- [Class] LoginInterceptor
		- [Add Interface] HandlerInterceptor
		- return false;
- spring-servelt.xml 설정 -> <mvc:interceptor>
- UserController
	- /user/login 오는 것을 LoginInterceptor를 만들고 return false;
	- Controller까지 가지 않아도 된다.
	- logout도 마찬가지
- AuthInterceptor
	- Handler가 실행이 되려면 Session(AuthInterceptor)에 authUser객체가 있는지 확인해보아야한다.
	- 인증이 필요한지 확인! (UserController -> @Auth)
	- 없으면 Login / 있으면 통과
	- @Auth
		- com.bitacademy.mysite.security -> [new -> Annotation] Auth
		- @Retension : Runtime / @Target : Method
	- @AuthUser -> update(회원정보수정) 
		- @Retension : Runtime / @Target : Parameter
		- com.bitacademy.mysite.security
		- spring-servlet.xml -> Argument-resolver 설정 / exclude!
		- AuthUserHandlerMethodArgumentResolver
		- [Add interface] HandlerMethodArgumentResolver
	- AuthInterceptor에서 1번 작업해줘야 spring-servlet.xml(exclude) Handler 작동!
- @Valid
```
public String join(@Valid UserVo userVo, BindingResult result, Model model) {
	if(result.hasError()) {
		// List<ObjectError> errors = result.getAllErrors();
		// for(ObjectError error : errors) {
		// 	System.out.println(error);
		// }
		
		model.addAllAttributes(result.getModel());
		return "user/join";
	}
	userService.join(userVo);
	return "redirect:/user/joinsuccess"; ```

	- UserVo validation검사 후 result에 담아준다.
	- join.jsp(Default Message) -> Binding : parameter객체에 form data를 bind 시킨다. / Form Tag 사용하면 간단해지니 추천!
	- src/main/resources/messages -> messages_ko.properties(앞글자는 대문자로 해주는게 좋다) : 오류메시지 메모장에 적고 복붙!
	- spring-servlet -> Message Source 설정(messages_ko.properties 내용을 받는다)
	- join.jsp에서 코드로 에러 메세지 출력할 수 있게 변경한다 -> <spring:message code="${코드내용}"/>
```

### mysite03-gallery(수업자료) 
- webapp/assets/css/(css파일 옮기기)
- webapp/assets/gallery-examples & images & jquery
- gallery.css 수업자료에 있는 것으로 수정
- DB 추가 (11/22 2번째 50분)
- pop-up layer -> 자바스크립트 (11/23)

