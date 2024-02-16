## 镜像源

为中国玩家提供Lunar下载镜像源

这里缓存了textures, 使用方法见下文

## 使用方法

将响应中的`https://textures.lunarclientcdn.com/` 换为
`https://www.lunarclient.top/mirror/textures/`

## 工作原理

> 由于每个文件和sha1的绑定是唯一的, 所以不用考虑文件变化问题

在请求时服务端会判断本地是否有该文件, 若没有, 就会进行下载, 然后发送响应

若有, 将会直接响应文件

## 搭建加速源

> 我们欢迎你来搭建加速源来分担主镜像源负担

WIP