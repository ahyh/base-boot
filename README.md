# base-boot
springboot搭建基础开发框架
base-boot使用springboot搭建的基础开发框架

### 1、定义dao/service/controller实现增删改查，引入mybatis
### 2、使用swaggerui组件，使用页面生成的文档
### 3、拦截器定义及使用
### 4、Listener、Filter定义及使用
### 5、自定义注解和AOP切面编程使用
### 6、mybatis拦截器定义及使用，使用mybatis拦截器实现密码数据加密存储
### 7、自定义springboot的场景启动器

自定义starter需要确定的问题
1-starter场景需要导入的依赖是什么？ 2-starter需要的自动配置是什么？
自动配置类要能加载，需要将启动就能加载的类放在META-INF/spring-factories.xml

自定义starter的步骤
1、创建一个空的工程 
2、创建一个**-spring-boot-starter子模块，表示场景启动器
3、创建一个**-spring-boot-starter-autoconfigurer的springboot子模块， 删除主配置类，删除application.properties文件
，pom文件引入spring-boot-starter的依赖，去掉plugins模块 
在此模块中写入@Configuration，@ConfigurationProperties定义的组件 
在resources目录下创建目录META-INF，在此目录下新建spring.factories文件
在spring.factories文件中定义org.springframework.boot.autoconfigure.EnableAutoConfiguration=自己定义的配置类

### 8、使用rocketmq生产者demo
### 9、使用rocketmq消费者demo
### 10、SpringBoot+dubbo服务注册
### 11、SpringBoot+dubbo服务消费
### 10、搭建redis集群，SpringBoot+redis集群
### 11、SpringBoot+redis集群实现Session共享
