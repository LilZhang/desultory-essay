### 图片反爬

1. 正常的访问
比如访问 www.baidu.com，获取到logo图片
该请求的请求头内的 Reference 为 www.baidu.com

2. 图片爬取的访问
直接访问图片地址
该请求的请求头内的 Reference 为空

3. 策略
可用检测 Reference 的 Filter 来防爬图片