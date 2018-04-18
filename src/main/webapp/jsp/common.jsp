
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ecnu三支花</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" >
<script src="../js/exjson.js" type="text/javascript"></script>
</head>
<body>
<div class = "top">
	<div class= "title">
	<span><!-- span标签用组合文档中的行内元素 -->
		<a class ="bold" href = "#">苹果</a>&nbsp;
		<a class ="bold" href = "#">土豆</a>&nbsp;
		<a class ="bold" href = "#">番茄</a>&nbsp;
		<a class = "bold" href = "#">香蕉</a>&nbsp;
		<a href="#">登录</a>&nbsp;
		<a href = "#">设置</a>&nbsp;
		
	</span>
	</div>
</div>
<div class ="middle">
	  <div class="pic">
		<img alt="search" src="../image/elasticsearch.png" align="middle" style="display: inline-block;" />
	</div>
	<div class="search">
        <form action="index.jsp" methond ="post" style=" text-align:  center;">
            <label for="search"></label>
            <input class="input" type="text" name="search" id="search" >
            <img alt="搜索" id = "searchpic" src="../image/search.png" style="vertical-align: bottom;" type ="submit" onClick ="window.location.href=index.jsp?id=11">
        </form>
    </div>
</div>
</body>
</html>