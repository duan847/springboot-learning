## 安装Redis（docker）
```shell
sudo docker run -d --name redis --restart always -p 6379:6379 redis
```
| 参数   |      含义      | 
|----------|-------------|
| --name | 容器名称 |
| -p 6379:6379 | Redis连接地址，程序连接需要的端口号 |
| redis | 镜像名 |
