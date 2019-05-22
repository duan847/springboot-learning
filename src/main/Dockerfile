FROM openjdk:8-jdk-alpine
VOLUME /tmp
#设置时区
ADD video-1.0.0.jar app.jar
RUN sh -c 'touch /app.jar'
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
ENV JAVA_OPTS=""
#使用prd配置启动
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar --spring.profiles.active=prd" ]
