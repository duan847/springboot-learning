## amqp

### 简介
AMQP（Advanced Message Queuing Protocol，高级消息队列协议）是一个提供统一消息服务的应用层标准高级消息队列协议,是应用层协议的一个开放标准,
为面向消息的中间件设计。基于此协议的客户端与消息中间件可传递消息，并不受客户端/中间件不同产品、不同开发语言等条件的限制。  
可以为程序解耦、等  

RabbitMQ是遵从AMQP协议的实现。

三种常用的交换器：direct、fanout、topic  

direct：点对点消息，严格按照路由键绑定的消息队列一对一的单发消息  

fanout： 不管路由键是什么，给所有绑定的消息队列全发消息  

topic：给符合路由键绑定的多个消息队列多发消息。如 *.news，#.news。（*：任意一个单词，#：任意多个单词）  

### 安装RabbitMQ（docker环境下安装）参考：springboot-docker/README.md

- 不包含延迟队列
```shell
sudo docker run -d --name rabbitmq --restart always -p 5672:5672 -p 15672:15672 -v ~/docker/rabbitmq/data:/var/lib/rabbitmq -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin rabbitmq:management
```


- 包含延迟队列
1. 在主机上下载延迟队列插件

`wget https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases/download/v3.8.0/rabbitmq_delayed_message_exchange-3.8.0.ez`
2. 编写`Dockerfile`文件制作带有延迟队列的rabbitmq镜像

```dockerfile
FROM rabbitmq
# wget https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases/download/v3.8.0/rabbitmq_delayed_message_exchange-3.8.0.ez
COPY rabbitmq_delayed_message_exchange-3.8.0.ez /plugins
RUN rabbitmq-plugins enable --offline rabbitmq_mqtt rabbitmq_federation_management rabbitmq_stomp rabbitmq_delayed_message_exchange
```

3. 制作`myrabbitmq`镜像
```shell script
docker build -t myrabbitmq .
```
3. 运行带有延迟队列的rabbitmq镜像

`sudo docker run -d --name rabbitmq --restart always -p 5672:5672 -p 15672:15672 -v ~/docker/rabbitmq/data:/var/lib/rabbitmq -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin myrabbitmq`

| 参数   |      含义      | 
|----------|-------------|
| --name | 容器名称 |
| -p 5672:5672 | RabbitMQ连接地址，程序连接需要的端口号 |
| -p 15672:15672 | RabbitMQ的Web端管理地址 |
| -v `pwd`/data:/var/lib/rabbitmq |数据存储在本地`/var/lib/rabbitmq`，容器重启后数据不会丢失 |
| -e RABBITMQ_DEFAULT_USER=admin | 默认的用户名 |
| -e RABBITMQ_DEFAULT_PASS=admin| 默认的密码 |
| rabbitmq:management | 镜像名 |

### 引入
在项目pom引入amqp依赖
```xml
    <dependencies>
        <!--springboot amqp-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>
    </dependencies>
```

### 子模块
#### consumer（消费者）
##### 介绍
从MQ队列中取出消息，并对消息作出处理

#### producer（生产者）
向MQ队列发送消息

### 配置mq连接信息（consumer、producer两模块都需要配置，本次使用RabbitMQ）
```yaml
spring:
  rabbitmq:
    host: 39.106.164.245
    username: admin
    password: admin
```

### 创建消息队列（consumer模块）
创建名称为`first`的消息队列，在`config`包中创建`QueueConfig.java`
```java
package com.duan.springboot.learning.ampq.consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 消息队列
 *
 * @author duanjw
 */
@Configuration
public class QueueConfig {

    /**
     * first队列
     * 如果没有此队列，启动时新增此队列
     *
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue("first");
    }
}
```
### 发送消息到消息队列
amqp为我们提供了发送消息到消息队列的```AmqpTemplate```对象。在需要发送消息到消息队列的Bean中直接注入```AmqpTemplate```，就可以发送消息咯。  
如创建测试类`AmqpProducerApplicationTest.java`
```java
package com.duan.springboot.learning.amqp.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmqpProducerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class AmqpProducerApplicationTest {

RabbitTemplate
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 向first队列发送消息
     */
    @Test
    public void test01sendToFirst() {
        amqpTemplate.convertAndSend("first", "你好，first队列");
    }
}
```

### 接收消息队列的消息（consumer模块）
在`listener`包中创建`ReceiveListener.java`
```java
package com.duan.springboot.learning.ampq.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 队列消息监听
 * @author duanjw
 */
@Component
@Slf4j
public class ReceiveListener {

    /**
     * 监听first队列消息
     * @param str
     */
    @RabbitListener(queues="first")
    public void firstListener(String str) {
        log.info("first队列收到消息：:{}", str);
    }
}
```

1. 启动`AmqpConsumerApplication`，等待消息队列的消息；
2. 运行`AmqpProducerApplicationTest`，发送消息到消息队列；
3. 可以在`consumer`模块的控制台看到消息：`first队列收到消息：:你好，first队列`
4. 同时，访问RabbitMQ的web管理端也能看到新建的消息队列：`Queues -> first`
5. 此时关闭`consumer`，用`AmqpProducerApplicationTest`再次发送消息到消息队列。当`consumer`启动后同样能收到队列中的消息
