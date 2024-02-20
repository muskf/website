## 镜像源

## 镜像源列表

> 如果你想将你搭建的镜像源添加到此处, 请[开pr](/website/openpr)

1. mirror.mcip.link (由15搭建)

## 搭建加速源

推荐[AnyMirror](https://github.com/cubewhy/AnyMirror), 具体搭建方法请见下文

### 搭建AnyMirror

1 [构建AnyMirror](#构建anymirror)

#### 构建AnyMirror

1. 下载OpenJDK
2. Clone 项目
3. 运行`gradlew build`

#### 开始运行

在`build/libs`找到工件, 然后复制到一个安全的地方

输入`java -jar AnyMirror.jar` 开始运行(请替换为实际的名称)

#### 后续步骤

你可能需要购买CDN来加速访问, 若要为你的网站启用TLS, 请在构建时编辑 application.properties 来增加证书