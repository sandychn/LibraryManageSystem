## 图书管理系统

###  MySQL 服务器的配置文件设置方法

在根目录中创建 `config` 文件夹，在该文件夹下创建 `database_server_config.xml` ，文件编码最好为 `UTF-8`。

例子：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<database-server-config>
    <address>192.168.184.10:3306</address>
    <dbname>lmsdb</dbname>
    <dbusername>root</dbusername>
    <dbpassword>123456</dbpassword>
</database-server-config>
```

`address`：MySQL服务器IP。

`dbname`：数据库名称。

`dbusername`：数据库用户名。

`dbpassword`：数据库密码。