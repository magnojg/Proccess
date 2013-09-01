package com.example.galleryn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class NewRegistry extends Activity {
	int ID = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro);
		
		((ImageView) findViewById(R.id.back_from_registry)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(); 
				intent.setClass(NewRegistry.this, MainActivity.class);
				
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
				startActivity(intent);
				finish();
			}
		});
		
		((Button) findViewById(R.id.save_registry)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = ((EditText) findViewById(R.id.registry_name)).getText().toString();
				String login = ((EditText) findViewById(R.id.registry_login)).getText().toString();
				String password = ((EditText) findViewById(R.id.registry_password)).getText().toString();
				
				UserDAO userDAO = UserDAO.getInstance(getApplicationContext());
				userDAO.salvar(new User(ID++, name, login, password));
				
				Intent intent = new Intent(); 
				intent.setClass(NewRegistry.this, MainActivity.class);
				
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
				startActivity(intent);
				finish();
			}
		});
	}
	
}
