# quick-java-devel

本项目为`QUICK`脚手架的开发环境。 `quick-XXX`为各个脚手架模块项目，通过`git submodule`的方式引入；`demo`为简单演示应用项目。

[![Maven Package](https://github.com/extvos/quickstart/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/extvos/quickstart/actions/workflows/maven-publish.yml)

## 开始

1. 获取项目代码
```shell
$ git clone https://github.com/extvos/quickstart.git
```

2. 更新模块代码
```shell
$ git submodule init
$ git submodule update
$ git submodule foreach git checkout develop
```

## 模块列表

- `plus.extvos.common` 通用库
    - `quick-lib-common` 通用公共库
    - `quick-lib-restlet` Restful 增删查改库
    - `quick-lib-logging` 日志库
    - `quick-lib-mqtt` MQTT消息处理封装
- `plus.extvos.auth` 鉴权认证库
    - `quick-auth-base` 鉴权认证基础（Shiro）
    - `quick-auth-oauth2` OAuth2认证鉴权支持
    - `quich-auth-builtin` 本地认证鉴权支持
- `plus.extvos.builtin` 内置服务
    - `quick-builtin-version` 内置版本信息服务
    - `quick-builtin-geo` 内置地理信息服务
    - `quick-builtin-upload` 内置文件上传服务
    - `quick-builtin-async` 异步接口任务处理
    - `quick-builtin-quartz` 定时任务处理
- `demo` 一个样例工程

## 约定

本项目包括关联的模块项目，默认工作分支为`develop`,无`master`分支。版本开发完成后，合并到`release`分支。
