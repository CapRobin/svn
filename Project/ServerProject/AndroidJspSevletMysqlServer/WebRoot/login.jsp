<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<TITLE>手机端与JavaWeb后台服务器交互_JspSevletMysqlServer</TITLE>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<STYLE type=text/css>
.ii {
	WIDTH: 100px;
	HEIGHT: 16px
}

BODY {
	BACKGROUND-COLOR: #f9edd2
}
</STYLE>

<SCRIPT language=javascript>
	function check() {
		if (document.form1.admin_user1.value == "") {
			alert("请输入帐号");
			document.form1.admin_user1.focus();
			return false;
		}
		if (document.form1.admin_pass1.value == "") {
			alert("请输入密码");
			document.form1.admin_pass1.focus();
			return false;
		} else {
			document.all.form1.submit();
		}
	}
	function Clock() {
		var date = new Date();
		this.year = date.getFullYear();
		this.month = date.getMonth() + 1;
		this.date = date.getDate();
		this.day = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")[date
				.getDay()];
		this.hour = date.getHours() < 10 ? "0" + date.getHours() : date
				.getHours();
		this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date
				.getMinutes();
		this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date
				.getSeconds();

		this.toString = function() {
			return "现在是:" + this.year + "年" + this.month + "月" + this.date
					+ "日 " + this.hour + ":" + this.minute + ":" + this.second
					+ " " + this.day;
		};

		this.toSimpleDate = function() {
			return this.year + "-" + this.month + "-" + this.date;
		};

		this.toDetailDate = function() {
			return this.year + "-" + this.month + "-" + this.date + " "
					+ this.hour + ":" + this.minute + ":" + this.second;
		};

		this.display = function(ele) {
			var clock = new Clock();
			ele.innerHTML = clock.toString();
			window.setTimeout(function() {
				clock.display(ele);
			}, 1000);
		};
	}
	//用来捕获用户的Enter事件
	function enter() {
		if (window.event.keyCode == 13) {
			document.all.form1.submit();
		}
	}
</SCRIPT>
<LINK href="<%=request.getContextPath()%>/admin/skin/css/style.css"
	rel="stylesheet">
</HEAD>

<BODY topMargin="150" onkeyup="enter()">
	<div align="center"
		style="font: '新宋体'; font-size: 34px; color: #CCCC66;">欢迎进入登陆界面</div>
	<TABLE width="100%" border=0>

		<TBODY>
			<TR>
				<TD align=middle>
					<FORM id=form1 name=form1 action="servlet/ServiceTest" method=post>
						<TABLE height=205 width=428
							background="<%=request.getContextPath()%>/admin/skin/images/frame/login.gif"
							border=0>
							<!--DWLayoutTable-->
							<TBODY>
								<TR>
									<TD width=422 height=50>&nbsp;</TD>
								</TR>
								<TR>
									<TD vAlign=bottom align=middle height=73>帐号：<INPUT
										class=ii id=admin_user1 name=username> <BR> <BR>密码：<INPUT
										class=ii id=admin_pass1 type=password name=password></TD>
								</TR>
								<TR>
									<TD align=middle height=74><INPUT
										onClick="return check();" type=button value=登陆 name=Submit></TD>
								</TR>
							</TBODY>
						</TABLE>
					</FORM>
				</TD>
			</TR>
		</TBODY>
	</TABLE>

	<table align="center" width="380">
		<tr>
			<td><input type="button" value="进入前台首页"
				onClick="javascript:window.open('index.jsp')" /></td>
			<td>
				<div id=clock style="color: #006633; font-size: 14px"></div> <SCRIPT
					type="text/javascript">
					var clock = new Clock();
					clock.display(document.getElementById("clock"));
				</SCRIPT>
			</td>
		</tr>

	</table>
</BODY>
</HTML>

