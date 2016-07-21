## String Boot Demo 코드 구현 순서

1. 구동가능한 tomcat을 적용
2. 간단한 @Controller를 작성
3. view template인 thymeleaf 적용
4. HSQLDB로 JPA 구성
5. logback 으로 로깅처리
6. 외부 설정 적용및 Profile적용
7. WAR로 만들어 미리 정해진 tomcat에 배포

### String Boot 프로젝트

링크 : https://github.com/spring-projects/spring-boot/

### spring-boot-demo의 spring-boot version v1.4.0.RC1

링크 : https://github.com/spring-projects/spring-boot/tree/v1.4.0.RC1

Company Mac의 githubFriends 폴더에 샘플을 설치함.

### Download a specific tag with Git

After the clone, you can list the tags with git tag -l and then checkout a specific tag: git checkout tags/<tag_name>

``` console

#> git checkout tags/1.4.0.RC1

```

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

``` console
#> mvn clean package
#> java -jar target/spring-boot-demo-1.0-SNAPSHOT.jar
#> mvn clean package && java -jar target/spring-boot-demo-1.0-SNAPSHOT.jar
#> mvn spring-boot:run
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
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<!-- 4. lombok 적용 -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.16.8</version>
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


### HSQLDB로 JPA 구성

* Item 자바코드 생성

```java
@Entity
@AllArgsConstructor
@ToString
@Data
@Table(name="Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String text;
}
```

* ItemRepository 자바코드 생성

```java
public interface ItemRepository extends JpaRepository<Item, Long> {
}
```
* ItemService Interface 와  ItemServiceImpl Class 자바코드 생성

* ListController 생성

```java
@Controller
@Slf4j
public class ListController {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView list()
    {
        List<Item> items = itemService.getAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("config", configProperties);
        map.put("items", items);
        return new ModelAndView("list", map);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ModelAndView write(@ModelAttribute Item item) {
        itemService.save(item);
        return new ModelAndView("redirect:/list");
    }
}
```

* Application Class 

```java
@SpringBootApplication
public class SpringBootDemoApplication {
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return filter;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
```

@ComponentScan("**") 은 자신이 사용된 폴더 밑을 기본적으로 스켄한다.
만일 하위 디렉토리나 동일레벨 다른 디렉토리의 파일을 ComponentScan 해야 할 경우
@ComponentScan(basePackages = {""}), @ComponentScan(value = {""}) 를 사용하면 된다.


### 유닛 테스트 코드 생성

스프링 부트 1.4 버전부터 새로 생성된, @SpringBootTest, @DataJpaTest 및 JacksonTester를 이용해 추가적인 방식으로 유닛 테스트 코드 생성
점점 편해지는 방식을 제공하고 있네요..

* ListControllerTest
  스프링을 이용한 테스트 방식.

* ListControllerTest02
  스프링 부트 1.3의  @WebIntegrationTest를 이용한 테스트, @WebIntegrationTest은 스프링 부트 1.4에서 deprecated 될 예정임.

* ListControllerTest03
  스프링 부트 1.4.0.RC1를 이용한 테스트, @SpringBootTest을 이용한 테스트 방식

* ListControllerTest04
  스프링 부트 1.4.0.RC1를 이용한 테스트 (Mock을 이용한 테스트), @SpringBootTest을 이용과 Mock을 이용한 테스트 방식

* ItemRepositoryTests
  Repository 유닛 테스트

* ItemJsonTests
  Json 유닛 테스트


### logback-spring 으로 로깅처리

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
<include resource="org/springframework/boot/logging/logback/base.xml"/>
<appender name="dailyConsoleAppender" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
        <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{5} - %msg%n</pattern>
    </encoder>
</appender>
<appender name="dailyRollingFileAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>./logs/task.log</file>
    <prudent>true</prudent>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <fileNamePattern>./logs/task.%d{yyyy-MM-dd}.log</fileNamePattern>
        <maxHistory>30</maxHistory>
    </rollingPolicy>
    <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
        <level>INFO</level>
    </filter>

    <encoder>
        <pattern>%d{yyyy:MM:dd HH:mm:ss.SSS} %-5level --- [%thread] %logger{35} : %msg %n</pattern>
    </encoder>
</appender>

<logger name="org.springframework.web" level="INFO"/>

<root level="INFO">
    <appender-ref ref="dailyConsoleAppender"/>
    <appender-ref ref="dailyRollingFileAppender" />
</root>
</configuration>
```

위의 내용 중

```xml
<property name="LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${PID:- } [%t] --- %-40.40logger{39} : %m%n%wex"/>
<conversionRule conversionWord="wex" converterClass="org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter"/>
```

위의 내용을 기존 logback에 위의 표멧을 사용하면 로그 결과값이 보기좋게 나타남.

```log
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.1.7.RELEASE)

2014-10-08 17:32:56.762  INFO 24422 [main] --- c.i.rt.execution.junit.JUnitStarter      : Starting JUnitStarter ~
2014-10-08 17:32:56.788  INFO 24422 [main] --- s.c.a.AnnotationConfigApplicationContext : Refreshing ~
2014-10-08 17:32:57.723  INFO 24422 [main] --- o.h.validator.internal.util.Version      : HV000001: Hibernate  ~
```
