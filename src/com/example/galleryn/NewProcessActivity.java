package com.example.galleryn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class NewProcessActivity extends Activity {
	private String username;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LoadScreen();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	private void LoadScreen(){
		setContentView(R.layout.newprocess);
		
		((Button) findViewById(R.id.go_p2_process)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int i = 0;
				String login = ((EditText) findViewById(R.id.login_access)).getText().toString();
				String password = ((EditText) findViewById(R.id.password_access)).getText().toString(); 
				UserDAO userDAO = UserDAO.getInstance(getApplicationContext());
				Boolean ok = false;
				
				for (i = 0; i < userDAO.recuperarTodos().size(); i++) {
					if (login.equals(userDAO.recuperarTodos().get(i).getLogin()) && password.equals(userDAO.recuperarTodos().get(i).getPassword())){
							ok = true;
							username = userDAO.recuperarTodos().get(i).getName();
							break;
					}
				}
				
				if (ok){
					LoadScreen2();
				}else{
					Toast.makeText(getApplicationContext(), "Login e/ou Senha errado(s).", Toast.LENGTH_SHORT).show();
					
					((EditText) findViewById(R.id.login_access)).getText().clear();
					((EditText) findViewById(R.id.password_access)).getText().clear();
					
					((EditText) findViewById(R.id.login_access)).requestFocus();
				}
				
			}
			
		});
		
		((ImageView) findViewById(R.id.back_from_process)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
                intent.setClass(NewProcessActivity.this, MainActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent);
				finish();
			}
		});
		
	}

	private void LoadScreen2() {
		setContentView(R.layout.newprocess2);
		
		((ImageView) findViewById(R.id.back_from_process2)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
                intent.setClass(NewProcessActivity.this, MainActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent);
				finish();
			}
		});
		
		((Button) findViewById(R.id.create_process)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String processName = ((EditText) findViewById(R.id.nameText)).getText().toString();
				
				Intent intent = new Intent();
                intent.setClass(NewProcessActivity.this, GalleryActivity.class);
                
                intent.putExtra("nameProcess", processName);
                intent.putExtra("username", username);
                
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent);
				finish();
			}
		});
	}

}
