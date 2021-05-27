# 关于`Quickstart Java Scaffolds`

`Quickstart`脚手架套件是一套简单的、基于Spring Boot的开箱即用套件，以满足基于数据库存储的WEB服务实现为目的。

## 相关模块

- 基础库
  - [`quick-lib-common` 通用基础工具类](quick-lib-common/README.md)
  - [`quick-lib-restlet` 通用增删查改实现套件](quick-lib-restlet/README.md)
  - [`quick-lib-logging` 日志库](quick-lib-logging/README.md)
- 用户鉴权认证相关
  - [`quick-auth-base` 基础用户认证实现](quick-auth-base/README.md)
  - [`quick-auth-oauth2` 单点登录实现](quick-auth-oauth2/README.md)
  - [`quick-auth-builtin` 内置用户数据存储实现](quick-auth-builtin/README.md)
- 内置基础服务
  - [`quick-builtin-version` 内置版本接口服务](quick-builtin-version/README.md)
  - [`quick-builtin-geo` 内置地址接口服务](quick-builtin-geo/README.md)
  - [`quick-builtin-upload` 内置文件上传服务](quick-builtin-upload/README.md)

## 套件基于以下第三方库

- Spring Boot 2.4.4

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <version>2.4.4</version>
  </dependency>
  ```

- MyBatis Plus

  ```xml
  <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus</artifactId>
      <version>3.4.2</version>
  </dependency>
  <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus-extension</artifactId>
      <version>3.4.2</version>
  </dependency>
  ```

- Aspect-J

  ```xml
  <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.9.6</version>
      <scope>compile</scope>
  </dependency>
  ```

- Hutools

  ```xml
  <dependency>
      <groupId>cn.hutool</groupId>
      <artifactId>hutool-all</artifactId>
      <version>5.6.4</version>
  </dependency>
  ```

- Shiro

  ```xml
  <dependency>
      <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-core</artifactId>
      <version>1.4.0</version>
  </dependency>
  <dependency>
      <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-spring</artifactId>
      <version>1.4.0</version>
  </dependency>
  <dependency>
      <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-web</artifactId>
      <version>1.4.0</version>
  </dependency>
  <dependency>
      <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-ehcache</artifactId>
      <version>1.4.0</version>
      <scope>provided</scope>
  </dependency>
  <dependency>
      <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-quartz</artifactId>
      <version>1.4.0</version>
      <scope>provided</scope>
  </dependency>
  ```

- Guava

  ```xml
  <dependency>
      <groupId>com.google.guava</groupId>
      <artifactId>guava</artifactId>
      <version>30.1.1-jre</version>
  </dependency>
  ```

- Zxing

  ```xml
  <dependency>
      <groupId>com.google.zxing</groupId>
      <artifactId>core</artifactId>
      <version>3.4.1</version>
  </dependency>
  <dependency>
      <groupId>com.google.zxing</groupId>
      <artifactId>javase</artifactId>
      <version>3.4.1</version>
  </dependency>
  ```

- Java JWT

  ```xml
  <dependency>
      <groupId>com.auth0</groupId>
      <artifactId>java-jwt</artifactId>
      <version>3.12.1</version>
  </dependency>
  ```

- Kaptcha

  ```xml
  <dependency>
      <groupId>com.github.penggle</groupId>
      <artifactId>kaptcha</artifactId>
      <version>2.3.2</version>
  </dependency>
  ```

  

- 
