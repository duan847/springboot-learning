## 4.Spring Boot Cache缓存
## cache
### 简介
将数据缓存到内存或Redis，增快查询效率。本文使用Redis作为缓存容器，可以适应单机和分布式系统。
### 引入
在项目pom引入swagger-ui依赖
```xml
    <dependencies>
        <!--springboot cache-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>

        <!--springboot redis-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
    </dependencies>
```
### 开启
方式1. 在项目的Application.java类（springboot启动类）上添加注解`@EnableCaching`

### 常用注解及注解的属性

| 注解   |      位置      |  作用 |
|----------|-------------|------|
| @Cacheable |  service实现类查询、查看方法上 | 新增、返回缓存数据。如果缓存的key不存在，缓存方法内返回的数据，否则，直接返回缓存的数据 |
| @CacheEvict | service实现类修改、删除方法上 | 删除缓存
| @CachePut |    service实现类修改方法上   |   更新缓存数据，缓存方法内返回的数据 |
| @Caching |    service实现类方法上   |   聚合多个CacheEvict、CachePut方法

#### @Cacheable

- 属性

| 属性   |  描述 |
|----------|------|
| key | 缓存的key。默认为参数组合
| cacheNames、value | 缓存的名称，与属性key组成缓存的整个key：cacheNames::key |

- 示例
```java
    /**
     * 根据id查看学生
     * 并放入缓存，默认的key是参数的值
     * @param id
     * @return Student
     */
    @Override
    @Cacheable(cacheNames = "student")
    public Student findById(Integer id) {
        log.info("开始缓存用户，用户id：{}，缓存的key为：student::{}", id, id);
        if(1 == id) {
            return new Student(1,"张三",new Date());
        }
        return new Student();
    }
``` 
- 第一次调用findById(1)  
![8bbd0bf03ccb6938d18850f38d9fc463.png](evernotecid://FADF9FBB-5D06-4F64-B19A-9EB45FD19F7D/appyinxiangcom/20065063/ENResource/p62)
redis中的值
![5adefa0c0e6829e03d82a7e65a02e61c.png](evernotecid://FADF9FBB-5D06-4F64-B19A-9EB45FD19F7D/appyinxiangcom/20065063/ENResource/p64)

- 第二次调用findById(1)
![14dc7aa716621554ba6e901cdaa9f12a.png](evernotecid://FADF9FBB-5D06-4F64-B19A-9EB45FD19F7D/appyinxiangcom/20065063/ENResource/p63)
redis中的值
![5adefa0c0e6829e03d82a7e65a02e61c.png](evernotecid://FADF9FBB-5D06-4F64-B19A-9EB45FD19F7D/appyinxiangcom/20065063/ENResource/p64)

#### @CachePut

- 属性

| 属性   |  描述 |
|----------|------|
| cacheNames、value | 缓存的名称，与属性key组成缓存的整个key：cacheNames::key |
| key | 缓存的key。默认为参数组合

- 示例
```java
/**
     * 根据id修改学生并且返回更新后的学生
     *
     * 直接更新缓存中的学生对象为传入的学生对象
     *
     * @param student 更新后的
     * @return student 更新后的
     */
    @Override
    @CachePut(cacheNames = "student",key = "#student.id")
    public Student updateByIdAndReturn(Student student) {
        log.info("根据id修改学生：{}", student);
        return student;
    }
```
调用updateByIdAndReturn(student)
![2a0ba597e97b7801a58a5d1620d677b1.png](evernotecid://FADF9FBB-5D06-4F64-B19A-9EB45FD19F7D/appyinxiangcom/20065063/ENResource/p66)



#### @CacheEvict

- 属性

| 属性   |  描述 |
|----------|------|
| key | 缓存的key。默认为参数组合
| cacheNames、value | 缓存的名称，与属性key组成缓存的整个key：cacheNames::key |
| allEntries | 是否清空所有。默认为false。若要清空所有，设置属性为true

- 示例
```java
/**
     * 根据id删除学生
     * @param id
     * @return true、false
     */
    @Override
    @CacheEvict(cacheNames = "student")
    public boolean deleteById(Integer id) {
        log.info("根据id删除学生：{}", id);
        return true;
    }
```
redis的值
![f0390831a6ab799bcd5f186592bef6eb.png](evernotecid://FADF9FBB-5D06-4F64-B19A-9EB45FD19F7D/appyinxiangcom/20065063/ENResource/p67)

### 问题

1. 如果更新返回的不是更新的数据，而是是boolean怎么处理？
答：目前采用删除key，再次查询、查看时重新获取，再缓存。
2. 如果批量更新、批量删除怎么处理？
答：目前采用删除所有key。

如果大家有更好的办法，请赐教

### 源码
springboot基于redis和cache的缓存：[Springboot-cache](github.com/duan847/springboot-learning/springboot-cache.git)
