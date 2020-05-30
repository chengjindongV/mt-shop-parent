mt-shop-parent-----公共Pranet接口 pom
-----mt-shop-basics----分布式基础设施 pom
---------mt-shop-basics-alibaba-nacos—注册中心 8080 jar
---------mt-shop-basics-alibaba-nacos—分布式配置中心 8080  
---------mt-shop-basics-alibaba-seata 分布式事务解决方案 8730
---------mt-shop-basics-alibaba-canal mysql与redis一致性的问题
---------mt-shop-basics-springcloud-gateway—统一请求入口 80
---------mt-shop-basics-xuxueli-xxljob—分布式任务调度平台
---------mt-shop-basics-codingapi-zipKin  —分布式调用链系统


-----mt-shop-service-api提供公共接口 没有实现业务代码
------------ mt-shop-service-api-base api相关base继承类
------------ mt-shop-service-api-weixin 微信服务接口
------------ mt-shop-service-api-member会员服务接口
------------ mt-shop-service-api-sso  sso服务接口
------------ mt-shop-service-api-item商品服务接口
------------ mt-shop-service-api-search 搜索服务接口
------------ mt-shop-service-api-pay聚合支付平台
------------ mt-shop-service-api-order订单服务接口
------------ mt-shop-service-api-spike 秒杀服务接口
------------ mt-shop-service-api-sms 消息服务平台

服务接口中包含内存内容: 实体类层、接口层 

-----mt-shop-service-impl公共接口的实现
------------ mt-shop-service-weixin 微信服务接口实 现 9090 9091 9092
------------ mt-shop-service-member会员服务接口实现  7070 7071
------------ mt-shop-service-api-sso  sso服务接口实现 6060 
------------ mt-shop-service-tem商品服务接口实现  5050
------------ mt-shop-service-search 搜索服务接口实现 3030 
------------ mt-shop-service-pay聚合支付平台接口实现 2020 
------------ mt-shop-service-order订单服务接口实现 1010 
------------ mt-shop-service-spike 秒杀服务接口 4040
------------ mt-shop-service-sms 消息服务平台 9810


