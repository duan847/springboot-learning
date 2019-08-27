## 安装mysql（docker环境）
```shell
sudo docker run -d -p 3306:3306 --name mysql --restart always -v $PWD/docker/mysql/conf:/etc/mysql/conf.d -v $PWD/docker/mysql/logs:/logs -v $PWD/docker/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 mysql --default-authentication-plugin=mysql_native_password

## mysql使用最新版的8.X镜像，客户端可能连接不上。
要不更新客户端。
要不修改密码方式，在docker run 后加参数：--default-authentication-plugin=mysql_native_password

```
| 参数   |      含义      | 
|----------|-------------|
| --name | 容器名称 |
| -p 3306:3306 | MySQL连接地址，程序连接需要的端口号 |
| -v $PWD/conf:/etc/mysql/conf.d | 配置文件本地存储 |
| -v $PWD/logs:/logs | 日志本地存储 |
| -v $PWD/data:/var/lib/mysql | 数据本地存储 |
| -e MYSQL_ROOT_PASSWORD=123456 | root用户的密码 |
| mysql | 镜像名 |

异常情况：  

正常情况下是可以连接到mysql服务端的（使用程序、客户端或命令行），如果连不上：  
1. `docker ps --filter name=mysql` 没有mysql的容器
2. `docker logs mysql` 查看日志
3. 如果有以下内容，可能是由于存在`/var/lib/mysql/`文件夹
    `2019-04-27T08:49:36.861549Z 0 [ERROR] [MY-010457] [Server] --initialize specified but the data directory has files in it. Aborting.
    2019-04-27T08:49:36.861559Z 0 [ERROR] [MY-013236] [Server] Newly created data directory /var/lib/mysql/ is unusable. You can safely remove it`
4. `rm -rf /var/lib/mysql/` 删除文件夹
5. `docker restart mysql` 重新启动
