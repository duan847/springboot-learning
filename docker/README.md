## 服务器：安装docker
```shell
## 安装docker，使用官方的安装脚本安装、启动&加入开机启动docker服务
curl -fsSL get.docker.com -o get-docker.sh && sudo sh get-docker.sh --mirror Aliyun && sudo systemctl start docker && sudo systemctl enable docker
```
## 本地：打包jar，并复制jar到服务器
```shell
cd docker && mvn clean package
## 1. 手动复制 docker/target/docker-1.0.0.jar、docker/src/main/java/Dockerfile到服务器同一目录
## 2. 也可以使用unix系统的scp命令复制：scp target/docker-1.0.0.jar src/main/java/Dockerfile root@129.204.29.217:~
```

## 服务器：编译jar到docker镜像、启动
```shell
docker build -t docker-web . && docker stop docker-web;docker rm video-web;docker run -d --restart=always -p 80:8080 --name=docker-web docker-web
```

## 服务器：测试
```shell
culr localhost/docker
返回结果：Hello World，I'm Docker
```
