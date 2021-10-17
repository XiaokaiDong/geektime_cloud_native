# 极客时间云原生训练营第二次作业

> 作业内容
>
> - 构建本地镜像。
> - 编写 Dockerfile 将练习 2.2 编写的 httpserver 容器化（请思考有哪些最佳实践可以引入到 Dockerfile 中来）。
> - 将镜像推送至 Docker 官方镜像仓库。
> - 通过 Docker 命令本地启动 httpserver。
> - 通过 nsenter 进入容器查看 IP 配置。

> 作业需编写并提交 Dockerfile 及源代码。

## 作业内容

见目录one中的Dockerfile

build命令：


```
docker build -t dxktt/myhttp:1.0 .
docker push dxktt/myhttp:1.0 
```