<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>彩蛋</title>
    <style>
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #ff6f61, #de3e36);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            overflow: hidden;
            color: white;
        }
        .container {
            text-align: center;
        }
        .surprise {
            font-size: 2rem;
            background: rgba(0, 0, 0, 0.7);
            padding: 2rem;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5);
            transform: scale(0);
            opacity: 0;
            transition: transform 1s ease-out, opacity 1s ease-out;
        }
        .surprise.visible {
            transform: scale(1);
            opacity: 1;
        }
        @keyframes burst {
            0% {
                transform: scale(0);
                opacity: 0;
            }
            50% {
                transform: scale(1.2);
                opacity: 1;
            }
            100% {
                transform: scale(1);
                opacity: 1;
            }
        }
        .surprise.burst {
            animation: burst 1s ease-in-out forwards;
        }
        .copy-button {
            margin-top: 1rem;
            padding: 0.5rem 1rem;
            font-size: 1rem;
            color: #fff;
            background: #ff6f61;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s;
        }
        .copy-button:hover {
            background: #de3e36;
        }
        .highlight {
            font-size: 1.5rem;
            color: #ff6f61;
            font-weight: bold;
            background: #fff;
            padding: 0.2rem;
            border-radius: 5px;
            margin: 0.5rem 0;
            display: inline-block;
        }
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4);
            justify-content: center;
            align-items: center;
        }
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 400px;
            text-align: center;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.3);
        }
        .modal-content p {
            font-size: 1.2rem;
            color: #333;
        }
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        .back-button {
            margin-top: 1rem;
            padding: 0.5rem 1rem;
            font-size: 1rem;
            color: #fff;
            background: #ff6f61;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s;
        }
        .back-button:hover {
            background: #de3e36;
        }
        .confetti {
            position: absolute;
            width: 10px;
            height: 10px;
            background: #61daff;
            opacity: 0.7;
            animation: confetti 5s linear infinite;
        }
        @keyframes confetti {
            0% {
                transform: translateY(0) rotate(0deg);
            }
            100% {
                transform: translateY(100vh) rotate(720deg);
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div id="surprise" class="surprise">
            Lunarclient.top服务端回归
            <br><br>
            <span id="address" class="highlight">https://lunarclient.top/api</span>
            <br>
            <button class="copy-button" onclick="copyText()">复制地址</button>
        </div>
    </div>

    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <p>地址已复制！</p>
            <button class="back-button" onclick="goHome()">返回主页</button>
        </div>
    </div>

    <script>
        window.onload = () => {
            const surprise = document.getElementById('surprise');
            surprise.classList.add('visible');
            surprise.classList.add('burst');
        }

        function copyText() {
            const text = document.getElementById('address').innerText;
            navigator.clipboard.writeText(text).then(() => {
                showConfetti();
                showModal();
            }).catch(err => {
                console.error('复制失败: ', err);
            });
        }

        function showModal() {
            const modal = document.getElementById('myModal');
            modal.style.display = 'flex';
        }

        function closeModal() {
            const modal = document.getElementById('myModal');
            modal.style.display = 'none';
        }

        function goHome() {
            window.location.href = 'https://lunarclient.top';
        }

        function showConfetti() {
            for (let i = 0; i < 100; i++) {
                createConfetti();
            }
        }

        function createConfetti() {
            const confetti = document.createElement('div');
            confetti.classList.add('confetti');
            confetti.style.left = Math.random() * window.innerWidth + 'px';
            confetti.style.animationDuration = Math.random() * 3 + 2 + 's';
            document.body.appendChild(confetti);
            setTimeout(() => confetti.remove(), 5000);
        }
    </script>
</body>
</html>
