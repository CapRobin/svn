<%@ page language="java" contentType="text/html" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�û���¼</title>
    <link rel="stylesheet" type="text/css" href="images/styles.css">
    <script type="text/javascript">
    	function login(form){
        	if(form.username.value == ""){
        		alert("�û�����Ϊ�գ�");
        		return false;
        	}
        	if(form.password.value == ""){
        		alert("���벻��Ϊ�գ�");
        		return false;
        	}
    	}
    </script>
  </head>
  
  <body>
	  <div align="center">
		  	<div class="div1">
		  		<div class="top">�û�ע��</div>
		  		<div class="bottom">
					<div class="div2">
				  		<ul>
				  			<li><a href="reg.jsp">�û�ע��</a></li>
				  			<li><a href="login.jsp">�û���¼</a></li>
				  			<li><a href="message.jsp">��ǰ�û�</a></li>
				  			<li><a href="UserExitServlet">�û��˳�</a></li>
				  		</ul>
				  	</div>
				  	 <div class="div3"> 
					    <form action="LoginServlet" method="post" onSubmit="return login(this);">
						    <table align="center" width="300" border="0" class="tb1">
						    	<tr>
						    		<td align="right">�û�����</td>
						    		<td>
						    			<input type="text" name="username">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">�� �룺</td>
						    		<td>
						    			<input type="password" name="password">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td colspan="2" align="center" height="50">
						    			<input type="submit" value="�� ¼">
						    			<input type="reset" value="�� ��">
						    		</td>
						    	</tr>
						    </table>
						</form>
				  	 </div>
				</div>
		  	</div>
	  </div>
  </body>
</html>
