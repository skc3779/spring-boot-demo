## String Boot Demo 코드 구현 순서

1. 구동가능한 tomcat을 적용
2. 간단한 @Controller를 작성
3. view template인 thymeleaf 적용
4. HSQLDB로 JPA 구성
5. logback 으로 로깅처리
6. 외부 설정 적용및 Profile적용
7. WAR로 만들어 미리 정해진 tomcat에 배포

### 구동가능한 tomcat을 적용

* pom.xml에 아래의 코드를 적용

``` xml
<!-- 1. Embbeded tomcat 설치 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
</dependency>
```

* Main.java 생성

``` java
@Configuration
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
```

* Console 코드실행

``` java
#> mvn clean package
#> java -jar target/spring-boot-demo-1.0-SNAPSHOT.jar
#> mvn clean package && java -jar target/spring-boot-demo-1.0-SNAPSHOT.jar
```

### 간단한 @Controller를 작성

* MainController 작성

``` java
@Controller
@RequestMapping("/")
public class MainController {
    @RequestMapping
    @ResponseBody
    public String index() {
        return "Hello Spring Boot?";
    }
}
```

* Application에 @ComponentScan 추가

* 기존 Tomcat 종료 후 Console에서 아래코드 실행

``` java
#> mvn clean package && java -jar target/spring-boot-demo-1.0-SNAPSHOT.jar
```

### view template인 thymeleaf 적용