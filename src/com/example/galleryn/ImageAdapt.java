package com.example.galleryn;

import com.example.galleryn.R;

import android.content.Context;
import android.view.*;
import android.widget.*;

public class ImageAdapt extends BaseAdapter {

	private Context myContext;
	private int[] myImageIds = {
			R.drawable.a,
			R.drawable.b,
			R.drawable.c,
			R.drawable.d,
			R.drawable.e
	};

	public ImageAdapt(Context c) {
		this.myContext = c;
	}

	public int getCount() {
		return this.myImageIds.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView i = new ImageView(this.myContext);
		i.setImageResource(this.myImageIds[position]);
		i.setScaleType(ImageView.ScaleType.FIT_XY);
		i.setLayoutParams(new Gallery.LayoutParams(150, 150));
		
		return i;
	}

	public float getScale(boolean focused, int offset) {
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}
}
