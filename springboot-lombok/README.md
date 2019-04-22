## lombok
### 简介
lombok 是一种 Java™ 实用工具，可用来帮助开发人员消除 Java 的冗长，尤其是对于简单的 Java 对象（POJO）。它通过注解实现这一目的。
### 引入
在项目pom引入lombok依赖
```xml
<!--Lombok-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <scope>provided</scope>
</dependency>

```
### IDEA安装lombok插件
Mac系统：IntelliJ IDEA -> Preferences -> Plugins -> 搜索'lombok' -> 选中安装并重启IDEA

### 常用注解及作用

| 注解   |      位置      |  作用 |
|----------|-------------|------|
| @Data |  POJO类上 | 会为类的所有属性自动生成getter/setter、equals、canEqual、hashCode、toString方法 |
| @NoArgsConstructor |  POJO类上 | 会为类生成无参构造 |
| @AllArgsConstructor |  POJO类上 | 会为类生成全参构造 |
| @Slf4j | 业务类上 | 生成slf4j，在Slf4j注解类的方法内部可以使用log对象记录日志
| @Getter/@Setter |    POJO属性上   |   自动生成getter/setter方法 |
| @EqualsAndHashCode | POJO类上 |    默认将所有非静态（non-static）和非瞬态（non-transient）属性来生成equals和hasCode，也能通过exclude注解来排除一些属性。 |
| @ToString | POJO类上 | 默认将类名、所有属性（会按照属性定义顺序），用逗号来分割来生成toString。


### 优缺点
优点：
1. 能通过注解的形式自动生成构造器、getter/setter、equals、hashcode、toString等方法，提高了一定的开发效率
2. 让代码变得简洁，不用过多的去关注相应的方法
3. 属性做修改时，也简化了维护为这些属性所生成的getter/setter方法等

缺点：
1. 不支持多种参数构造器的重载
2. 虽然省去了手动创建getter/setter方法的麻烦，但大大降低了源代码的可读性和完整性，降低了阅读源代码的舒适度

参考：https://www.cnblogs.com/heyonggang/p/8638374.html


