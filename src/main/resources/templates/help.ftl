<!DOCTYPE html>
<html lang="zh_cn">

<head>
    <meta charset="UTF-8">
    <title>LunarCN Client | Docs</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="description" content="Description">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <!-- 默认主题 -->
    <!--    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify@4/lib/themes/vue.css">-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/docsify-themeable@0/dist/css/theme-simple.css"
          title="light">
    <link rel="stylesheet alternative"
          href="https://cdn.jsdelivr.net/npm/docsify-themeable@0/dist/css/theme-simple-dark.css" title="dark">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify-darklight-theme@3/dist/docsify-themeable/style.min.css"
          type="text/css">
    <style>
        nav.app-nav li ul {
            min-width: 100px;
        }
    </style>
</head>

<body>
<!-- 定义加载时候的动作 -->
<div id="app">加载中...<br>
    <a href="https://github.com/CubeWhy/website/issues/new">反馈加载问题</a>
</div>
<script>
    window.$docsify = {
        // 项目名称
        name: 'LunarCN Docs',
        // 仓库地址，点击右上角的Github章鱼猫头像会跳转到此地址
        repo: 'https://github.com/cubewhy/website',
        // 侧边栏支持，默认加载的是项目根目录下的_sidebar.md文件
        loadSidebar: true,
        sidebarDisplayLevel: 1,
        alias: {
            '/.*/_sidebar.md': '/_sidebar.md'
        },
        // 导航栏支持，默认加载的是项目根目录下的_navbar.md文件
        loadNavbar: true,
        // 封面支持，默认加载的是项目根目录下的_coverpage.md文件
        coverpage: true,
        // 最大支持渲染的标题层级
        maxLevel: 4,
        // 自定义侧边栏后默认不会再生成目录，设置生成目录的最大层级（建议配置为2-4）
        subMaxLevel: 3,
        // 小屏设备下合并导航栏到侧边栏
        mergeNavbar: true,
        auto2top: true,
        executeScript: true,
        //Latex设置
        latex: {
            inlineMath: [['$', '$'], ['\\(', '\\)']],
            displayMath: [['$$', '$$']],
            customOptions: {}
        },
        pagination: {
            previousText: '上一章节',
            nextText: '下一章节',
        },
        /*搜索相关设置*/
        search: {
            paths: 'auto',// 注意：仅适用于 paths: 'auto' 模式
            // 支持本地化
            placeholder: {
                '/zh-cn/': '搜索',
                '/': 'Type to search'
            },
            noData: '找不到结果',
            depth: 4,
            hideOtherSidebarContent: false,
            namespace: 'Docsify-Guide',
        },

        count: {
            countable: true,
            position: 'top',
            margin: '10px',
            float: 'right',
            fontsize: '0.9em',
            color: 'rgb(90,90,90)',
            language: 'chinese',
            isExpected: true
        }
    }
</script>
<!-- MathJax -->
<script src="//cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
<script src="//cdn.jsdelivr.net/npm/docsify-latex@0"></script>
<!-- docsify的js依赖 -->
<script src="//cdn.jsdelivr.net/npm/docsify/lib/docsify.min.js"></script>
<!-- emoji表情支持 -->
<script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/emoji.min.js"></script>
<!-- 图片放大缩小支持 -->
<script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/zoom-image.min.js"></script>
<!-- 深色主题切换器 -->
<script
        src="//cdn.jsdelivr.net/npm/docsify-darklight-theme@3/dist/docsify-themeable/main.min.js"
        type="text/javascript">
</script>
<script
        src="//cdn.jsdelivr.net/npm/docsify-darklight-theme@3/dist/docsify-themeable/index.min.js"
        type="text/javascript">
</script>

<!-- 搜索功能支持 -->
<script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/search.min.js"></script>
<script src="//unpkg.com/docsify-copy-code"></script>
<!-- code render-->
<script src="//cdn.jsdelivr.net/npm/prismjs@latest/components/prism-bash.js"></script>
<script src="//cdn.jsdelivr.net/npm/prismjs@latest/components/prism-python.js"></script>
<script src="//cdn.jsdelivr.net/npm/docsify-pagination@latest/dist/docsify-pagination.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/docsify-copy-code"></script>
<script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/zoom-image.min.js"></script>
<!--字数统计-->
<script src="//unpkg.com/docsify-count/dist/countable.js"></script>

<!-- 访问量统计 -->
<script async src="//busuanzi.ibruce.info/busuanzi/2.3/busuanzi.pure.mini.js"></script>
</body>
</html>