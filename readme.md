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

spring에서 적극 지원하고 있는 thymeleaf html viewer 엔진

* pom.xml 추가

``` xml
<!-- 3. view template인 thymeleaf 적용 -->
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf-spring4</artifactId>
    <version>2.1.3.RELEASE</version>
</dependency>
<dependency>
    <groupId>nz.net.ultraq.thymeleaf</groupId>
    <artifactId>thymeleaf-layout-dialect</artifactId>
    <version>1.2.5</version>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.14.8</version>
    <scope>compile</scope>
</dependency>
```
* ThymleafController 생성

``` java
@Controller
@Slf4j
public class ThymleafController {

    @Autowired
    private ConfigProperties configProperties;

    @RequestMapping("/thymeleaf")
    @ResponseBody
    public ModelAndView index()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("config", configProperties);
        log.info("config name/text, {}/{}", configProperties.getName(), configProperties.getText());
        return new ModelAndView("thymeleaf", map);
    }
}
```

* resources/templates/thymeleaf.html 생성


### h2database를 이용 JPA 구성

스프링부트는 임베디드 H2, HSQL, Derby 데이터베이스에 대해 오토설정을 제공한다.

* pom.xml 추가

``` xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>com.mysema.querydsl</groupId>
    <artifactId>querydsl-sql</artifactId>
    <version>3.5.0</version>
</dependency>
```

* resources 폴더에 DML 생성

``` sql
insert into ITEM (TEXT) values ('데이터 01');
insert into ITEM (TEXT) values ('데이터 02');
insert into ITEM (TEXT) values ('데이터 03');
insert into ITEM (TEXT) values ('데이터 04');
insert into ITEM (TEXT) values ('데이터 05');
insert into ITEM (TEXT) values ('데이터 06');
```

* Item.java, ItemRepository.java, listController.java 생성
