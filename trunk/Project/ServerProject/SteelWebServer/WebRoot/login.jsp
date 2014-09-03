<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script language=javascript>
	function check() {
		if (document.formid.zhid.value == "") {
			alert("请输入账号");
			document.formid.zhid.focus();
			return false;
		}

		if (document.formid.mmid.value == "") {
			alert("请输入密码")
			document.formid.mmid.focus();
			return false;
		} else {
			document.all.formid.submit();
		}
	}
</script>
</head>

<body bgcolor="#8B795E">
	<!-- 	<div align="center" -->
	<!-- 		style="font: bold; font-size: 50px; color: #C7C7C7;">欢迎进入宁夏汇埠钢材信息平台</div> -->
	<form action="servlet/DoLogin" id=formid name="formname" method="post">
		<table border="0" width="80%" align="center">
			<tbody>
				<tr align="center">
					<td height="150px" align="center"
						style="font: bold; font-size: 40px; color: #8B2323">欢迎进入宁夏汇埠钢材信息平台</td>
				</tr>
				<tr>
					<td valign="bottom" align=middle height="75">账号：<input
						class=zh id=zhid name=username> <br> <br> 密码：<input
						class=mm id=mmid name="password" type="password"></td>
				</tr>
				<tr>
					<td align="middle" height="75"><input
						onclick="return check();" type="button" width="500px"
						align="middle"
						value="&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;&nbsp;"
						name="Submit"></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>
