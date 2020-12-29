## mybatis学习

了解过mybatis的同学都知道...

来自官方文档的定义: MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

相较于hibernate，我们一般都喜欢与之做个比较，我们常叫mybatis框架为半orm框架,因为我们在使用hibernate查询关联对象的时候，可以直接获取，而mybatis需要手动编写sql实现，
但是不得不承认mybatis使用起来确实简单。但是今天我们不说这些具体的使用，我们看看框架里面的一些知识，
想要具体学习的同学可以参考官网: https://mybatis.org/mybatis-3/zh/index.html

有过一点mybatis框架使用的同学都知道,mybatis有自己的配置文件，以及xml映射文件，
类型转换处理，事务管理器， 懒加载等等一些知识。那么在框架里面，他是如何扫描
这些文件使用的呢？
