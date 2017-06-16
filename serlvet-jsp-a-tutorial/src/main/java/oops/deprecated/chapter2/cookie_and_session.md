### defference between cookie and session

1. cookie 储存在客户端, session 驻留在内存中

2. cookie 一般为字符串, session 可以存储对向实例
    以便于 session 数量太大时将其序列化
    所以 session 的对象要 Serializable

3. 服务器一般用 JSESSIONID 来判断是哪个 session
    有两种方式
    - 在cookie里存放JSESSIONID, 发送至服务器
    - 在URL内写入JSESSIONID=**传入

4. MaxInactiveInterval 是用户最后一次动作多久之后 session 失效(失效时间)

5. 如果失效时间(上述)设置为0则表示永久不失效
    但是不建议这样做，这样每个用户的 session 对象都被永久保留在内存中
    直到重布