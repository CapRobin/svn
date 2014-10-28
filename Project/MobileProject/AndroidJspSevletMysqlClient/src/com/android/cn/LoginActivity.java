package com.android.cn;

import org.apache.http.client.methods.HttpPost;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Button cancelBtn, loginBtn;
	private EditText userEditText, pwdEditText;
	private  String getReString = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		cancelBtn = (Button) findViewById(R.id.cancel);
		loginBtn = (Button) findViewById(R.id.login);

		userEditText = (EditText) findViewById(R.id.userName);
		pwdEditText = (EditText) findViewById(R.id.userPwd);

		cancelBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		loginBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
//				HttpPost request = new HttpPost("http://www.baidu.com");
				if (validate()) {
					if (login()) {
//						Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//						startActivity(intent);
						Toast.makeText(LoginActivity.this, getReString, 5).show();
					} else {
						Toast.makeText(LoginActivity.this, getReString, 5).show();
					}
				}
			}
		});
	}

	private boolean login() {
		String username = userEditText.getText().toString();
		String pwd = pwdEditText.getText().toString();
		String result = query(username, pwd);
		// ��ʼ��
		// if (result != null && result.equals("1")) {
		// return true;
		// } else {
		// return false;
		// }
		// �޸İ�
//		if (result != null && result.equals("��¼�ɹ�")) {
//			return true;
//		} else {
//			return false;
//		}
		if (result != null) {
			getReString = result;
			return true;
		}else {
			getReString = result;
			return false;
		}
		// ���ʸֲķ����
//		if (result != null) {
//			return true;
//			getReString = result;
//		} else {
//			return false;
//		}
	}

	private boolean validate() {
		String username = userEditText.getText().toString();
		if (username.equals("") && username == null) {
			showDialog("�û������Ǳ����");
			return false;
		}
		String pwd = pwdEditText.getText().toString();
		if (pwd.equals("")) {
			showDialog("�û������Ǳ����");
			return false;
		}
		return true;
	}

	private void showDialog(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg).setCancelable(false).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	private String query(String username, String password) {
		String queryString = "username=" + username + "&password=" + password;
		
		//��������JavaWeb����ƽ̨(AndroidJspSevletMysqlServer)
//		String url = HttpUtil.BASE_URL + "servlet/ServiceTest?" + queryString;
//		http://192.168.1.103:8080/SteelWebServer/servlet/DoLogin?username=yfr&password=123456
		//�������Ӹֲ���Ϣƽ̨(SteelWebServer)
		String url = "http://192.168.1.126:8080/SteelWebServer/servlet/DoLogin?"+queryString;
//		String url = "http://192.168.1.103:8080/UserLogin/LoginServlet?"+queryString;
		return HttpUtil.queryStringForGet(url);
	}
}