# rocketmq-spring-boot-starter

AliYun RocketMQ Spring Book Edition

[中文](./README_zh_CN.md)

Support function:

- [x] Three modes of sending ordinary messages: synchronous, asynchronous and one-way
- [x] Subscribe to Message Cluster, Broadcast
- [x] Send and receive sequential messages
- [x] Transaction messages
- [x] Delay message
- [x] receive and receive timing messages

#Tips:
Delay message and timing message:
In the official case, delayed news is much the same as regular news, essentially ordinary news.
If delay message and timing message are needed, it is recommended to use timing task (timing task scheduling platform)
To achieve the purpose of delay or timing.


## Quick Start

```xml
<!--Adding dependencies to pom. XML-->
        <dependency>
            <artifactId>rocketmq-spring-boot-starter</artifactId>
            <groupId>com.github.thierrysquirrel</groupId>
            <version>2.0.0-RELEASE</version>
        </dependency>
```
 ### configuration file
     
 
 ```properties
 ## application.properties
thierrysquirrel.access-key= #The Access Key you created in the AliYun Account Management Console for authentication
thierrysquirrel.secret-key= #The SecretKey you created in the AliYun Account Management Console for authentication
thierrysquirrel.name-srv-addr= #Set up TCP protocol access point and get it from console
 ```
# Start RocketMQ
```java
@SpringBootApplication
@EnableRocketMQ
public class DemoApplication{
    public static void main(String[] args){
        SpringApplication.run(DemoApplication.class, args);
    }
   
}
```
# Three Ways to Send Common Messages

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
# Send sequential messages
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
# Send Transaction Messages
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
#Delay message or timing message
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
#Subscribe to regular, transactional, delayed, timed messages
```java
@RocketListener(groupID = "GID_message")
public class Delayed {
    @MessageListener(topic = "message",tag = "message")	
    public void delayed(String message) {
            return "message";
    }
}
```
#Subscription order message
```java
@RocketListener(groupID = "GID_message")
public class Delayed {
    @MessageListener(topic = "message",tag = "message", orderConsumer = true)
    public void delayed(String message) {
            return "message";
    }
}
```