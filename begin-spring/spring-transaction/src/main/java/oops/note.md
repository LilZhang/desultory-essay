# annotation
配置中使用`@EnableTransactionManagement`
方法中使用`@Transaction`

## txAopXML
在 xml 配置文件中使用 `<tx:advice>` 来配置事务: 事务名，transactionManager，methodName
在 xml 配置文件中使用 `<aop:config>` 来配置该事务的切面

## trasaction template
编程式事务管理
适合一个方法中有部分代码需要事务化的方案

## platform transaction manager
编程式事务管理
类似于这样
```
dosth();
connection.commit();
connection.roolback();
connection.close();
```

## callback
事务的回调
