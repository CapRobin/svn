<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>第一个Jsp练习</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<h3>
			练习一：在页面中输出5的阶乘
		</h3>
		<%
			int intValue = 1;
			for (int i = 1; i < 6; i++) {
				intValue = i * intValue;
			}
		%>
		<h4>
			5的阶乘为：<%=intValue%>
		</h4>

		练习二：在Jsp页面中输出字符“*”号组成的金字塔
		<br>
		<%
			String str = "";
			for (int i = 0; i < 30; i++) {
				for (int j = 30; j > i; j--) {
					str += "&nbsp;";
				}
				for (int j = 0; j < i; j++) {
					str += "*&nbsp;";
				}
				str += "<br>";
			}
		%>
		<%=str%>
	</body>
</html>
