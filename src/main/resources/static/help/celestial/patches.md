# 针对原版API的更新

> 上次修改时间: 2024/3/10, 内容为 sentry参数包含非法字符

## Sentry参数包含非法字符

> 修复时间: 2024/3/10
> 
> 包含该修复的版本: 2.3+

### 参数

```
-Dichor.filteredGenesisSentries=.*lcqt.*|.*Failed to bootstrap fabric-loader.*
```

### 哪里不合法

`*`是非法字符
