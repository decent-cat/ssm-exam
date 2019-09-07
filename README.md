## Spring + Spring MVC + TKMybatis + Thymeleaf整合

### 一. 项目整体结构

![](images/1.png)

### 二. 依赖配置

```xml
<dependencies>
    <!-- mybatis依赖 -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.4.6</version>
    </dependency>
    <!-- tk.mybatis依赖 -->
    <dependency>
      <groupId>tk.mybatis</groupId>
      <artifactId>mapper</artifactId>
      <version>4.1.5</version>
    </dependency>
    <!-- tkmybatis与spring整合的包 -->
    <dependency>
      <groupId>tk.mybatis</groupId>
      <artifactId>mapper-spring</artifactId>
      <version>1.1.5</version>
    </dependency>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.8</version>
      <scope>provided</scope>
    </dependency>
    <!-- mybatis分页插件 -->
    <dependency>
      <groupId>com.github.pagehelper</groupId>
      <artifactId>pagehelper</artifactId>
      <version>5.1.9</version>
    </dependency>
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.16</version>
    </dependency>
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.1.20</version>
    </dependency>
    <!-- spring 依赖 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.1.8.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-orm</artifactId>
      <version>5.1.8.RELEASE</version>
    </dependency>
    <!-- mybatis与spring整合依赖 -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>2.0.2</version>
    </dependency>
    
    <!-- thymeleaf模板引擎依赖 -->
    <dependency>
      <groupId>org.thymeleaf</groupId>
      <artifactId>thymeleaf</artifactId>
      <version>3.0.11.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.thymeleaf</groupId>
      <artifactId>thymeleaf-spring5</artifactId>
      <version>3.0.11.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3.1</version>
    </dependency>
    
    <!-- logback日志依赖 -->
    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-core</artifactId>
      <version>1.2.3</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/ch.qos.logback/logback-classic -->
    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-classic</artifactId>
      <version>1.2.3</version>
    </dependency>
    <!-- 依赖jsp于servlet相关的jar包 -->
    <dependency>
	    <groupId>javax.servlet</groupId>
	    <artifactId>javax.servlet-api</artifactId>
	    <version>3.0.1</version>
	    <!-- 
	    	provided: 在打包发布的时候，这个包排除掉。
	     -->
	    <scope>provided</scope>
	</dependency>
	<dependency>
	    <groupId>javax.servlet</groupId>
	    <artifactId>jsp-api</artifactId>
	    <version>2.0</version>
	    <scope>provided</scope>
	</dependency>
    
    <!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
	<dependency>
    	<groupId>org.aspectj</groupId>
    	<artifactId>aspectjweaver</artifactId>
    	<version>1.9.4</version>
	</dependency>
    
    <!-- spring aop依赖 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>5.1.8.RELEASE</version>
    </dependency>
    
</dependencies>
```

三. spring配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context-4.3.xsd
		http://www.springframework.org/schema/aop
		http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
		http://www.springframework.org/schema/tx
		http://www.springframework.org/schema/tx/spring-tx-4.3.xsd">

    <!-- 扫描基础包 -->
    <context:component-scan base-package="com.qf.ssm">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <context:property-placeholder location="classpath:db.properties" system-properties-mode="NEVER"/>

    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">

        <property name="url" value="${url}"></property>
        <property name="username" value="${username}"></property>
        <property name="password" value="${password}"></property>
        <property name="driverClassName" value="${driver_class}"></property>

        <property name="initialSize" value="5"></property>
        <property name="minIdle" value="2"></property>
        <property name="maxIdle" value="8"></property>
        <!-- 获取连接最大的等待时间，单位毫秒 -->
        <property name="maxWait" value="60000"></property>
        <!-- 检查连接是否有效, value 值根据数据库必须为有效的 sql -->
        <property name="validationQuery" value="select 1"></property>

        <!-- 当连接不够的时候，申请链接先执行以上 sql, 通常设置为 false，为提高性能，与 validationQuery 配合使用 -->
        <property name="testOnBorrow" value="false"></property>

        <!-- 归还连接的时候执行的，通常设置为 false，为提高性能，与validationQuery 配合使用 -->
        <property name="testOnReturn" value="false"></property>

        <!-- 空闲的时候执行查询，通常设置为 true,不影响性能，与 validationQuery配合使用 -->
        <property name="testWhileIdle" value="true"></property>

        <!-- 空闲连接在连接池中最小的生存时间 -->
        <property name="minEvictableIdleTimeMillis" value="300000"></property>

        <!-- 打开PSCache，并且指定每个连接上PSCache的大小 ,注意：如果用Oracle，则把 poolPreparedStatements 配置为 true，mysql 可以配置为 false。 -->
        <property name="poolPreparedStatements" value="false"></property>

        <property name="maxPoolPreparedStatementPerConnectionSize" value="20"></property>
    </bean>

    <!-- 获取SqlSessionFactory -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        
        <!-- 
			com.rsy.sme.pojo.Menus 生成一个别名：Menus	
			com.rsy.sme.model.Menus 也会生别名：Menus
			解决方法：1.在mapper文件中直接写全类名。
				     2. 创建mybatis.xml文件，在该文件中使用
				    	<typeAlias type="com.rsy.sme.pojo.Menus" alias="Menus"/>的方式类自己定义别名,
				      将该文件通过configLocation的方式引入过来。
				   <property name="configLocation" value="classpath:mybatis.xml"></property>   
				    3.两种方式混合使用。
				    
		 -->
        <property name="typeAliasesPackage" value="com.qf.ssm.pojo"/>
        <!-- 指定映射文件的位置 -->
        <property name="mapperLocations" value="classpath:/mapper/**/*.xml"/>
        <!-- 配置分页插件 -->
        <property name="plugins">
            <array>
                <bean class="com.github.pagehelper.PageInterceptor">
                    <property name="properties">
                        <props>
                            <prop key="helperDialect">mysql</prop>
                            <prop key="rowBoundsWithCount">true</prop>
                            <prop key="pageSizeZero">true</prop>
                            <prop key="reasonable">true</prop>
                        </props>
                    </property>
                </bean>
            </array>
        </property>
    </bean>

    <!-- 扫描接口 -->
    <bean class="tk.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.qf.ssm.dao"/>
    </bean>

    <!-- 配置事务 -->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!-- 启用注解的方式开启事务 -->
    <tx:annotation-driven transaction-manager="transactionManager"/>
</bean>
```

### 三. 数据库连接配置

```
driver_class=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/qf?useSSL=false&serverTimezone=UTC
username=root
password=
```

### 四. springmvc配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.3.xsd
		http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd">

    <context:component-scan base-package="com.qf.ssm">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <!-- 
		加上该句话后，可以支持日期的转换等
	 -->
    <mvc:annotation-driven></mvc:annotation-driven>

    <!-- 过滤静态资源 -->
    <mvc:default-servlet-handler/>

    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="UTF-8" />
        <!-- 文件大小，为 KB 1024KB=1M -->
        <property name="maxUploadSize" value="20971520" />
    </bean>

    <bean id="templateResolver" class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
        <property name="prefix" value="/WEB-INF/"/>
        <property name="suffix" value=".html"/>
        <property name="templateMode" value="HTML5"/>
        <property name="cacheable" value="false"/>
        <property name="characterEncoding" value="utf-8"/>
    </bean>

    <bean id="templateEngine" class="org.thymeleaf.spring5.SpringTemplateEngine">
        <property name="templateResolver" ref="templateResolver"/>
    </bean>

    <bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
        <property name="templateEngine" ref="templateEngine"/>
        <property name="characterEncoding" value="utf-8"/>
    </bean>

</beans>
```

### 五. 日志配置

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!-- scan: 当配置文件被修改后, 将会被重新载入。
	 scanPeriod: 置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。
	 debug: 当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。 -->
<configuration scan="true" scanPeriod="60 seconds" debug="false">

    <!-- 输出到控制台 -->
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <!-- 配置日志输出到控制台的格式 -->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} -- %-4relative %-5level %logger{32} %thread -- %msg%n</pattern>
            <charset>utf-8</charset>
        </encoder>
    </appender>

    <!-- 将日志记录到文件当中 -->
    <appender name="file" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 基于时间和大小的的滚动策略 -->
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <!--日志文件输出的文件名, 必须包含%i, 从0开始-->
            <FileNamePattern>D:/logs/logback.%d{yyyy-MM-dd}.%i.log</FileNamePattern>
            <!-- 日志文件保留天数 -->
            <MaxHistory>30</MaxHistory>
            <!-- 最大20KB 超过最大值，会重新建一个文件-->
            <maxFileSize>20MB</maxFileSize>
            <!-- 所有的日志加起来最大的大小 -->
            <totalSizeCap>400MB</totalSizeCap>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level %thread -- %msg%n</pattern>
            <charset>utf-8</charset>
        </encoder>
    </appender>

    <!-- root节点是必选节点，用来指定最基础的日志输出级别，只有一个level属性。 -->
    <!-- debug info warn error fatal -->
    <root level="info">
        <!-- 标识这个appender将会添加到这个loger。 -->
        <appender-ref ref="stdout"/>
        <appender-ref ref="file"/>
    </root>

</configuration>
```

### 六. web.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         id="WebApp_ID" version="3.1">

  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:application.xml</param-value>
  </context-param>

  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

  <servlet>
    <servlet-name>mvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

    <!-- 参数名参照 DispatcherServlet 源码中的 342行-->
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springmvc.xml</param-value>
    </init-param>

    <load-on-startup>1</load-on-startup>
  </servlet>

  <servlet-mapping>
    <servlet-name>mvc</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>

</web-app>
```

### 七. dao接口编写

```java
import com.qf.ssm.pojo.User;
import tk.mybatis.mapper.common.*;

public interface UserDao extends MySqlMapper<User>, ExampleMapper<User>, BaseMapper<User>, IdsMapper<User>, ConditionMapper<User> {
}
```

### 八. 服务层编写

```java
import com.github.pagehelper.PageInfo;
import com.qf.ssm.pojo.User;

public interface UserService {
    PageInfo<User> selectAll();
}
```

```java
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public PageInfo<User> selectAll() {
        PageHelper.startPage(1, 10);
        List<User> list = userDao.selectAll();
        PageInfo<User> pageInfo = PageInfo.of(list);
        return pageInfo;
    }
}
```

### 九. controller层编写

```
import com.github.pagehelper.PageInfo;
import com.qf.ssm.pojo.User;
import com.qf.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping
    public String getInfo(Map<String, Object> map) {
        PageInfo<User> pageInfo = userService.selectAll();

        map.put("userList", pageInfo.getList());

        System.out.println("总页数" + pageInfo.getPages());
        System.out.println("总数据量" + pageInfo.getTotal());

        return "user/index";
    }
}
```

### 十. pojo类的编写

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User {

    @Id
    private Integer id;
    private String name;
    private String password;
    private Character gender;
    private String email;
    private Date birthday;

    @Column(name = "createTime")
    private Date createTime;
}
```

### 十一. 页面编写

```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <ul th:each="user:${userList}">
        <li>
            <span th:text="${user.name}"></span> --
            <span th:text="${user.gender}"></span> --
            <span th:text="${user.email}"></span>
        </li>
    </ul>
</body>
</html>
```