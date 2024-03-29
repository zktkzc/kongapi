# API 开放平台 - 后端

> by tkzc00

## 技术选型

- JDK 8
- Spring Boot
- MyBatis
- MySQL
- MyBatisPlus
- Redis
- Knife4j
- Lombok
- Gson
- Hutool
- Spring AOP
- Spring Cloud Gateway
- Dubbo
- Nacos

## 项目功能概述

- 使用 Redis 缓存数据
- 使用 Knife4j 来生成接口文档
- 用户管理
- 用户数据的增删改查
- 用户登录
- 用户注册
- 接口信息的增删改查
- 使用 Hutool 来调用接口
- 根据 AccessKey 和 SecretKey 来进行接口调用的校验
- 统计接口调用次数
- 使用 Spring Cloud Gateway 网关来统一进行接口调用次数的统计
- 使用网关进行权限校验
- 使用网关进行流量染色
- 使用网关进行访问控制（黑白名单）
- 使用网关进行请求转发
- 使用网关进行全局请求、响应拦截
- 使用网关进行统一日志记录
- 使用 Dubbo 和 Nacos 来实现服务注册、发现、调用