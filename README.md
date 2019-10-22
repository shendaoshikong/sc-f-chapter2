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
仔细查看项目中 Project Structure 是否存在错误提示，及时改正回来，不然容易造成不易发觉的错误，导致项目
运行不成功，这种错误不会报错。
