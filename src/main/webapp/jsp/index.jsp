<%@ page language="java" import="DB.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ecnu三枝花</title>
<link rel="stylesheet" type="text/css" href="../css/index.css" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="../js/jquery.js" type="text/javascript"></script>

</head>
<body>

	<div id="wrapper" class = "wrapper_s">
		<div id="head">
			<div id ="s_fm" class="s_form">
				<a href="common.jsp" id="logo"style="float:left;" >
				<img class="logo_show" src="../image/elasticsearch_logo.png">
				</a>
				<form name="f" id="form" action="index.jsp" class="fm" onsubmit="javascript:F.call('ps/sug','pssubmit');">
				<%String content = request.getParameter("search"); %>
					<input type="text" class ="s_ipt" name = "search" id="search" value=<%=content %> maxwidth="100" sutocomplete="off" style="border:1px solid;">		
            	<img alt="搜索" id="search" src="../image/search2.png" style="vertical-align: bottom;" onclick="showData();">		
			</form>
			</div>
		</div>
	<div id="wrapper_wrapper" style="display:block;">

		<div id ="container" class="container_s">
			<% 
	HTTPTest item = new HTTPTest();
	System.out.println(content);
	List<Item> itemList =item.transform(content);
	for(Item l1:itemList)
{%>
			<div id="content_left">
				<div class="result c-container " id="1" srcid="1599" tpl="se_com_default" data-click="{&quot;rsv_bdr&quot;:&quot;0&quot;,&quot;p5&quot;:2}">
				
				<h3 class="t">
				<a  href=<%=l1.getUrl()%> target="_blank">
				<em><%=l1.getTitle()%></em></a>
				<div class="c-abstract">
				<span class=" newTimeFactor_before_abs m"><%=l1.getContent()%></span>
				</div>
				</h3>

		</div>
	
	</div>
	<%} %>	
	</div>
	</div>
	</div>
</body>
</html>