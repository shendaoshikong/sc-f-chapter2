# SpringCloud
基于springcloud搭建的微服务程序

## 使用技术
- 版本：springcloud Camden.SR6
- 注册中心：eureka


## 项目结构
- 注册中心 discovery
- 服务1 service（服务提供者）
- 网关 gateway

## 启动项目

1. 启动discovery main方法，访问端口网址类似：http://localhost:8081，可以看见有spring eureka
的界面
当服务提供者向注册中心注册时，它会提供一些元数据，例如主机和端口，URL，主页等。Eureka server 从
每个client实例接收心跳消息。 如果心跳超时，则通常将该实例从注册server中删除。
2. 启动service main 8082
这时候可以看见在注册中心的界面上已经注册了相对应的服务提供者的名字，表明该服务已经上线了
你会发现一个服务已经注册在服务中了，服务名为SERVICE ,端口为8082
但是在接下来访问网址：http://localhost:(服务提供者的端口，**千万别搞错了)8082/service/（GetMapping）hi
3. 启动gateway main 8084
注意：在修改代码后要重启服务才能应用当前的代码！****
## 实现功能
通过注册中心discovery的配置，可以在网关gateway模块，访问service模块上的接口。

二：负载均衡
1,一个服务注册中心，eureka-server,端口为 8001
2,service工程跑了两个实例，端口分别为 8002,8002，在一个实例启动后，修改Run配置，在最右上角点击
Allow parallel run ，修改端口号再次启动，分别向服务注册中心注册
3,sercvice-ribbon 端口为 8004,向服务注册中心注册
4,当 sercvice-ribbon 通过 restTemplate 调用 service-hi 的hi接口时，因为用 ribbon 进行了负载均
衡，会轮流的调用service：8002 和 8003 两个端口的hi接口；

结果：
在浏览器上多次访问http://localhost:8004/hi?name=forezp，浏览器交替显示：
hi forezp,i am from port:8002
hi forezp,i am from port:8003

这说明当我们通过调用restTemplate.getForObject(“http://SERVICE/hi?name=”+name,String.class)方法时，已经做了负载均衡，访问了不同的端口的服务实例。

其中会遇到的坑：
1，仔细查看项目中 Project Structure 是否存在错误提示，及时改正回来，不然容易造成不易发觉的错误，导致项目
运行不成功，这种错误不会报错。
2，defaultZone 的端口号要正确，否则会出现执行到一半没有反应

三：Ribbon，Feign，OpenFeign
Ribbon
Ribbon 是 Netflix开源的基于HTTP和TCP等协议负载均衡组件
Ribbon 可以用来做客户端负载均衡，调用注册中心的服务
Ribbon的使用需要代码里手动调用目标服务，请参考官方示例：https://github.com/Netflix/ribbon

Feign
Feign是Spring Cloud组件中的一个轻量级RESTful的HTTP服务客户端
Feign内置了Ribbon，用来做客户端负载均衡，去调用服务注册中心的服务。
Feign的使用方式是：使用Feign的注解定义接口，调用这个接口，就可以调用服务注册中心的服务
Feign支持的注解和用法请参考官方文档：https://github.com/OpenFeign/feign
Feign本身不支持Spring MVC的注解，它有一套自己的注解

OpenFeign
OpenFeign是Spring Cloud 在Feign的基础上支持了Spring MVC的注解，如@RequesMapping等等。
OpenFeign的@FeignClient可以解析SpringMVC的@RequestMapping注解下的接口，
并通过动态代理的方式产生实现类，实现类中做负载均衡并调用其他服务。

四：断路器
在微服务架构中，根据业务来拆分成一个个的服务，服务与服务之间可以相互调用（RPC），在
Spring Cloud可以用RestTemplate+Ribbon和Feign来调用。为了保证其高可用，单个服务通常
会集群部署。由于网络原因或者自身的原因，服务并不能保证100%可用，如果单个服务出现问题
，调用这个服务就会出现线程阻塞，此时若有大量的请求涌入，Servlet容器的线程资源会被消
耗完毕，导致服务瘫痪。服务与服务之间的依赖性，故障会传播，会对整个微服务系统造成灾难
性的严重后果，这就是服务故障的“雪崩”效应。
为了解决这个问题，业界提出了断路器模型。
Feign 自带断路器，所以推荐还是使用Feign的好，其中 openfeign 更是集合了Feign 和Ribbon
和SSM框架的标签

1，配置文件
2，fallback
3，实现接口

结果：当没有服务提供者的时候，将显示接口实现中的返回值。

五：路由网关(zuul)
********************************
大坑：
spring-cloud-netflix-eureka-client和spring-cloud-starter-netflix-eureka-client这两个
是不一样的，但都是自动导入的包，导入一定要小心查看，不然倒错了不容易发现，日了狗了，简
直就是浪费时间！
********************************
主要是配置文件的修改，main方法头上添加路由网关的@EnableZuulProxy

总结：客户端的请求首先经过负载均衡（zuul、Ngnix），然后再到服务网关（zuul集群），然后再到具体的服

六：
见sc-f-chapter3