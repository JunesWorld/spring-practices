# spring-practices

## Project 생성

### - Project Explorer
[new] - [Maven Project] - [Create a simple project] - [Group Id] com.juesworld / [Artifact Id] spring-practices / [Packaging] pom(부모) - src 삭제

### - Navigator
[.gitignore] File 생성 
> **/.settings
> **/target
> **/.classpath
> **/.project

### - Github 연동
- [Project Explorer] - [Team] -[Share Project] - 모두 check! - [Create Repository] - [Finish]
- [Git Repository] - [Remotes] - [Create Remotes] - [Configure fetch] -[Create] - Github URL 복사 후 입력 

### - 하위 프로젝트 생성
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
