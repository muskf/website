<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Short Link configuration</title>
</head>
<body>
<div style="padding: 15px;">
    <fieldset class="layui-elem-field">
        <legend>新建短链接</legend>
        <div class="layui-field-box">
            <form class="layui-form" lay-filter="form-1" action="/dashboard/short-link/add">
                <div class="layui-input-group">
                    <div class="layui-input-prefix">名称</div>
                    <input name="name" class="layui-input">
                </div>

                <div class="layui-input-group">
                    <div class="layui-input-prefix">跳转目标</div>
                    <input name="target" class="layui-input">
                </div>
                <button class="layui-btn" lay-submit>提交</button>
            </form>
        </div>
    </fieldset>
</div>
</body>
</html>