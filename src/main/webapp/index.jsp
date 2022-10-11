<%--
  Created by IntelliJ IDEA.
  User: tq
  Date: 2022/9/26
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>盘分析</title>
    <link rel="stylesheet" type="text/css" href="./CSS/indexCss+.css">
</head>
<body>
<div class="content">
    <nav>
        <h1>
            股盘分析
        </h1>
    </nav>
    <main>
        <ul class="main">
            <form action="coreQuery" method="get" class="main">
                <li class="left"><input type="text" name="param1"/></li>
                <li class="center">${param11}</li>
                <li class="right"><button type="submit">提交链接</button>
                <a href="${url1}">转入36kr实时信息</a>
                    <a href="https://so.eastmoney.com/web/s?keyword=${param1}" target="_blank">东方财富网的股票实时信息</a>
                </li>
            </form>
        </ul>
        <hr>
        <ul class="main">
            <form action="coreQuery" method="get" class="main">
                <li class="left"><input type="text" name="param2"/></li>
                <li class="center"></li>
                <li class="right"><button type="submit">提交链接</button></li>
            </form>
        </ul>
        <hr>
        <ul class="main">
            <form action="coreQuery" method="get" class="main">
                <li class="left"><input type="text" name="param3"/></li>
                <li class="center"></li>
                <li class="right"><button type="submit">提交链接</button> </li>
            </form>
        </ul>
        <hr>
    </main>
    <footer>
        分析完毕
    </footer>
</div>
</body>
</html>
