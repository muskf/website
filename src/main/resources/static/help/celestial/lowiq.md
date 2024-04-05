# LowIQ专区 (小白话文)

> 最后更新日期 2024/4/5, 若出现变动请提出issue

## 认识celestial/lunarcn的目录/文件

d如果你在使用Windows操作系统, `~`等价于`%userprofile%` (`$userprofile`)

### 目录

默认配置目录: `~/.cubewhy/lunarcn` (在下文用`$lccn`代替)

游戏安装目录(默认): `$lccn/game`

日志(logs)目录: `$lccn/logs`

加载器自动下载目录: `$lccn/loaders`

~~LCCN加载器模组目录: `$lccn/mods`~~

### 文件

~~旧版LunarCN配置文件: `$lccn/config.json`~~

~~LCCN加载器模组配置: `$lccn/loader.json`~~

Celestial配置文件: `$lccn/celestial.json`

上次的启动脚本: `$lccn/launch.sh` (Windows下为`$lccn/launch.bat`)

## 为什么无法连接到API

### API为LCCN API

LCCN API 已停止运营, 请查看条目[切换到moonsworth api](#切换到moonsworth-api)

### API为Moonsworth API

如果你之前配置过celestial代理, 或者你的网络无法连接到服务器, 请查看[配置/删除代理](#配置删除代理)

#### 排查网络问题

> 此处假设你使用Windows操作系统, 如果你在使用其他系统, 下面的内容仅供参考.

1. Win+R输入cmd并回车
2. 在命令提示符中输入`ping api.lunarclientprod.com`
3. 查看丢包情况

如果无丢包, 很可能是因为你开启了代理, 请移步[配置/删除代理](#配置删除代理)

如果有丢包(非100%丢), 可尝试多次打开启动器或者换一个网络

如果100%丢包, 你可以选择[配置代理](#配置删除代理)或者[更换DNS服务器](#更换DNS服务器)

## 切换到Moonsworth API

> 警告: 该API没有使用开源的LCCN Backend, 若你倾向开源, 请使用其他API.

地址: `https://api.lunarclientprod.com`

## 配置/删除代理

> 此处说的不是系统代理, celestial不走系统代理, 我们不提供代理程序

1. 使用任意一个文本编辑器打开`~/.cubewhy/lunarcn/proxy.json`
2. 修改proxy项, 填写你的代理, 记得带协议, 例如`socks://127.0.0.1:8080`
3. 修改state项, 修改为true
4. 保存文件

## 更换DNS服务器

> 假设你在使用Windows操作系统, 其他系统的修改方式请自行百度

等待贡献, 我不用Windows, 可参考[这篇文章](https://cloud.tencent.com/developer/article/2091973)

Windows 11原生支持DOH, 请求是加密的, 可以试试.
