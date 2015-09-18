package com.caowei922.smsfake;

import com.caowei922.smsfake.R;

import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText ed_number;
	EditText ed_body;
	Button bt_submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed_number = (EditText) findViewById(R.id.number);
		ed_body = (EditText) findViewById(R.id.body);
		bt_submit = (Button) findViewById(R.id.submit);
		bt_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String number = ed_number.getText().toString();
				String body = ed_body.getText().toString();
				if(!number.equals("") && !number.equals("")){
					send(number,body);
				}else{
					Toast.makeText(getApplicationContext(), "电话和内容均不能为空", 0).show();
				}
			}
		});
	}
	
	
	public void send(final String number, final String body) {
		// �?30秒钟, 想系统短信数据库中写�?条短�?
				new Thread(new Runnable() {
					@Override
					public void run() {
						SystemClock.sleep(10 * 1000);
						
						Uri uri = Uri.parse("content://sms/");	// 操作sms表的uri
						
						ContentValues values = new ContentValues();
						values.put("address", number);
						values.put("type", "1");
						values.put("body", body);
						getContentResolver().insert(uri, values);
//						Toast.makeText(com.itheima28.xiangqin.MainActivity.this.getApplicationContext() , "已发�?10秒后收到短信", 0).show();
					}
				}).start();
				Toast.makeText(getApplicationContext(), "已发�?10秒后收到短信", 0).show();
	}
}
