## 图书管理系统

### 系统介绍

基于 `MySQL` 的图书管理系统，界面采用 `Swing` 编写。具有4个身份，每个身份可以操作图书管理系统的对应功能：

- 学生：借阅书籍、续借书籍、归还书籍。
- 图书馆用户管理员：新建图书馆用户。
- 图书馆仓库管理员：增加图书、修改图书、删除图书、查阅书籍借阅记录、增加图书分类、修改图书分类名。
- 后台数据管理员：修改用户身份。

### MySQL 建库代码

请见 `sql\lmsdb.sql` 。

###  MySQL 服务器的配置文件设置方法

在根目录中创建 `config` 文件夹，在该文件夹下创建 `database_server_config.xml` ，文件编码为 `UTF-8`。

比如：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<database-server-config>
    <address>192.168.184.10:3306</address>
    <dbname>lmsdb</dbname>
    <dbusername>root</dbusername>
    <dbpassword>123456</dbpassword>
</database-server-config>
```

`address`：MySQL 服务器IP。

`dbname`：数据库名称。

`dbusername`：数据库用户名。

`dbpassword`：数据库密码。

