FROM openjdk:8-jdk-alpine
VOLUME /tmp
# jar包添加到docker容器中
ADD docker-1.0.0.jar app.jar
RUN sh -c 'touch /app.jar'
# 设置时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
ENV JAVA_OPTS=""
# 启动
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
