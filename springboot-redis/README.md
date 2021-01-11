## 安装Redis（docker）
```shell
sudo docker run -d --name redis --restart always -p 6379:6379 redis --requirepass "123456"
```
| 参数   |      含义      | 
|----------|-------------|
| --name redis | 容器名称 |
| -p 6379:6379 | 映射到主机的端口 |
| redis:5.0.7 | 镜像名 |
| --requirepass | Redis 连接密码 |
