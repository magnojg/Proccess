package com.example.galleryn;

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GalleryActivity extends Activity {
	// private Gallery g;
	private ImageView imagem;
	// private int[] myImageIds = {
	// R.drawable.a,
	// R.drawable.b,
	// R.drawable.c,
	// R.drawable.d,
	// R.drawable.e
	// };

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int MEDIA_TYPE_IMAGE = 1;
	private Uri fileUri;

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

	private void LoadScreen() {
		setContentView(R.layout.gallery);

		// g = (Gallery) findViewById(R.id.action_settings);
		// g.setAdapter(new ImageAdapt(this));
		// g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		//
		// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		//   imagem.setImageResource(myImageIds[arg2]);
		//   Toast.makeText(getBaseContext(),"Figura " + (arg2 + 1) + " selecionada", Toast.LENGTH_SHORT).show();
		// }
		//
		// });

		String username = (String) getIntent().getExtras().get("username");
		((TextView) findViewById(R.id.username)).setText("   Olá " + username + "!");

		final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		((ImageView) findViewById(R.id.camera)).setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
					}

		});

		((ImageView) findViewById(R.id.back_gallery)).setOnClickListener(new ImageView.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(GalleryActivity.this, MainActivity.class);
						
						overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
						startActivity(intent);
						finish();
					}
		});
		
		((ImageView) findViewById(R.id.openGallery)).setOnClickListener(new ImageView.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setType("image/*");
						intent.setAction(Intent.ACTION_GET_CONTENT);
						startActivity(Intent.createChooser(intent, "Select Picture"));
					}
		});
	}

	private Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	@SuppressLint("SimpleDateFormat")
	private File getOutputMediaFile(int type) {
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
																					  "MyCameraApp/" + getIntent().getExtras().get("nameProcess"));

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("MyCameraApp", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File mediaFile;
		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
		} else {
			return null;
		}

		return mediaFile;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				imagem = (ImageView) findViewById(R.id.openGallery);
				Toast.makeText(this, "Image saved to:\n" + fileUri.getPath(), Toast.LENGTH_SHORT).show();

				Bitmap bitmap;
				try {
					bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), fileUri);
					imagem.setImageBitmap(bitmap);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if (resultCode == RESULT_CANCELED) {
			} else {
			}
		}
	}

}
