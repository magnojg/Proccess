package com.example.galleryn;

import android.app.Activity;
import android.content.Intent;
//import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity implements Runnable {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		Handler handler = new Handler();
		handler.postDelayed(this, 3000);
		// MediaPlayer musica = MediaPlayer.create(this, R.drawable.vinheta);
		// musica.start();
	}

	@Override
	public void run() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}
}
