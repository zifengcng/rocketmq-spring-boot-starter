# rocketmq-spring-boot-starter

阿里云RocketMQ   SpringBoot 版

[English](./README.md)

支持功能：
- [x] 普通消息三种发送方式：同步，异步，单向
- [x] 订阅消息集群,广播
- [x] 收发顺序消息
- [x] 收发事务消息
- [x] 收发延迟消息
- [x] 收发定时消息

# 提示：
 收发延时消息与定时消息：
 在官方例子中，延时消息与定时消息大同小异，本质上都为普通消息
 如果需要延时消息，与定时消息，建议使用定时任务（定时任务调度平台）
 达到延时或定时的目的。


## Quick Start

```xml
<!--在pom.xml中添加依赖-->
        <dependency>
            <artifactId>rocketmq-spring-boot-starter</artifactId>
            <groupId>com.github.thierrysquirrel</groupId>
            <version>2.0.0-RELEASE</version>
        </dependency>
```
 ### 配置文件
 
 ```properties
 ## application.properties
thierrysquirrel.access-key= #您在阿里云账号管理控制台中创建的 AccessKey，用于身份认证
thierrysquirrel.secret-key= #您在阿里云账号管理控制台中创建的 SecretKey，用于身份认证
thierrysquirrel.name-srv-addr= #设置 TCP 协议接入点，从控制台获取
 ```
# 启动RocketMQ
```java
@SpringBootApplication
@EnableRocketMQ
public class DemoApplication{
    public static void main(String[] args){
        SpringApplication.run(DemoApplication.class, args);
    }
   
}
```
#发送普通消息三种方式

```java
@RestController
@RocketMessage(groupID = "GID_common")
public class Common {

	@GetMapping("/commonA")
	@CommonMessage(topic = "commonA", tag = "commonA",messageSendType = MessageSendType.SEND)
	public String h() {
		return "commonA";
	}
	@GetMapping("/commonB")
	@CommonMessage(topic = "commonB", tag = "commonB",messageSendType = MessageSendType.SEND_ASYNC)
	public String hh() {
		return "commonB";
	}
	@GetMapping("/commonC")
	@CommonMessage(topic = "commonC", tag = "commonC",messageSendType = MessageSendType.SEND_ONE_WAY)
	public String hhh() {
		return "commonC";
	}
}
```
#发送顺序消息
```java
@RestController
@RocketMessage(groupID = "GID_order")
public class Order {
	@GetMapping("/order")
	@OrderMessage(topic = "order",tag = "order")
	public String order() {
		return "order";
	}
}
```
#发送事务消息
```java
@RestController
@RocketMessage(groupID = "GID_transaction")
public class Transaction {
	@GetMapping("/transaction")
	@TransactionMessage(topic = "transaction",tag = "transaction")
	public String transaction() {
		return "transaction";
	}
}
```
#发送延时消息或定时消息
```java
@RestController
@RocketMessage(groupID = "GID_delayed")
public class Delayed {
	@GetMapping("/delayed")
	@CommonMessage(topic = "delayed", tag = "delayed",startDeliverTime = 10)
	public String delayed() {
		return "delayed";
	}
}
```
#订阅普通、事务、延时、定时消息
```java
@RocketListener(groupID = "GID_message")
public class Delayed {
    @MessageListener(topic = "message",tag = "message")	
    public void delayed(String message) {
            return "message";
    }
}
```
#订阅顺序消息
```java
@RocketListener(groupID = "GID_message")
public class Delayed {
    @MessageListener(topic = "message",tag = "message", orderConsumer = true)
    public void delayed(String message) {
            return "message";
    }
}
```