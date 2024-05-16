# LowIQ专区 (小白话文)

> 最后更新日期 2024/5/16, 若出现变动请提出issue

## 已失效内容

1. 正版登录
2. LunarQT
3. LunarCN Loader

## 索引(常见错误)

> 不认识外国文? 请查看如何[切换语言](#切换语言)!

1. [Java版本错误](#错误的java版本)
2. [API无法连接](#为什么无法连接到api)
3. [配置代理](#配置删除代理)
4. [无法自动下载mod loader](#手动补全mod-loader)

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

## Java报错

> 请务必使用JDK17 (不要用JRE)

### Linux上特有的headless JRE

请不要使用headless jre, 你需要为它补额外的library

lunarcn推荐使用[azul zulu17](https://azul.com)进行运行

### 错误的Java版本

> 此处特指Windows用户

如果出现虚拟机错误, 请删除多余的Java(包括jre), 只留一个JDK17

## 手动补全Mod Loader

> LCCN Loader已弃用

## 补全Weave Loader

1. 打开[Weave-Loader/releases](https://github.com/Weave-MC/Weave-Loader/releases)
2. 下载最新版本(jar)
3. 将文件名改为`weave.jar` (Windows用户需要在系统中开启[显示扩展名](#显示扩展名))
4. 移动到`$lccn/loaders`中

## 切换语言

> 在新版本中celestial已提供自动识别系统语言功能, 但是经常识别错误, 若有兴趣修复可提交pr

1. 转到Settings (点击三次next)
2. 找到Language
3. 调整为中文

### 我不小心调整成了韩文, 都是锟斤拷语言怎么办

> 该问题仅存在于Windows

请查看[重置启动器配置](celestial/config#恢复默认配置) 或者记位置改回去

## 显示扩展名

> 仅Windows用户

我不用Windows, 请查看[Microsoft Support](https://support.microsoft.com/zh-cn/windows/windows-%E4%B8%AD%E7%9A%84%E5%B8%B8%E8%A7%81%E6%96%87%E4%BB%B6%E6%89%A9%E5%B1%95%E5%90%8D-da4a4430-8e76-89c5-59f7-1cdbbc75cb01)

### Windows 8及以上

1. 打开文件资源管理器(快捷键Win+E)
2. 单击“视图”选项卡
3. 选择`文件扩展名`
4. 点击确认

## Windows 7

1. 在『开始』菜单搜索中,键入文件夹选项
2. 在“文件夹选项”窗口中,单击“视图”选项卡
3. 取消选择`隐藏已知文件类型的扩展名`
4. 点击确认