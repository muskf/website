<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="赞助LunarCN项目,LunarCN 客户端"/>
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <title>Donate us | LunarCN Official</title>
    <script>
        function openTab(evt, name) {
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(name).style.display = "block";
            evt.currentTarget.className += " active";
        }
    </script>
    <style>
        body {
            font-family: Arial;
        }

        /* Style the tab */
        .tab {
            overflow: hidden;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
        }

        /* Style the buttons inside the tab */
        .tab button {
            background-color: inherit;
            float: left;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            transition: 0.3s;
            font-size: 17px;
        }

        /* Change background color of buttons on hover */
        .tab button:hover {
            background-color: #ddd;
        }

        /* Create an active/current tablink class */
        .tab button.active {
            background-color: #ccc;
        }

        /* Style the tab content */
        .tabcontent {
            display: none;
            padding: 6px 12px;
            border: 1px solid #ccc;
            border-top: none;
        }

        .pay {
            width: 200px;
            height: 200px;
        }
    </style>
</head>
<body>
<h1 align="center">赞助我们</h1>
<p>Only Chinese yuan is accepted. If you really want to support us, you can give us a star on GitHub.</p>
<hr>
<h2 align="center">赞助开发者</h2>
<p>LunarCN一直都是免费开源的,未提供过任何收费服务,请赞助来支持dev</p>
<p>微信容易被封, 建议使用支付宝赞助</p>
<div class="tab">
    <button class="tablinks" onclick="openTab(document, 'ZFB')">支付宝</button>
    <button class="tablinks" onclick="openTab(document, 'WX')">微信</button>
</div>

<div id="WX" class="tabcontent">
    <h3>微信</h3>
    <img src="/images/pay-wx.png" alt="donate with wechat" class="pay">
</div>

<div id="ZFB" class="tabcontent">
    <h3>支付宝</h3>
    <img src="/images/pay-zfb.jpg" alt="donate with zfb" class="pay">
</div>

<h2 align="center">领取赞助奖励(仅赞助dev)</h2>
<p>添加QQ 1076124626或在<a href="https://discord.lunarclient.top">LunarCN Discord</a>中开票, 然后发送你的支付记录,
    看到之后会拉你进群/添加身份组</p>
<script>
    openTab(document, 'ZFB'); // open the default tab
</script>
</body>
</html>