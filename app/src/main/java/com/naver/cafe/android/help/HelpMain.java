package com.naver.cafe.android.help;

import android.os.Bundle;
import android.widget.TextView;

import com.naver.cafe.android.R;
import com.naver.cafe.android.ShareActivity;

public class HelpMain extends ShareActivity {

	public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    setContentView(R.layout.help_layout);
	    setBtn(R.layout.help_layout);
	    TextView titleTextView = (TextView) findViewById(R.id.detail);
	    titleTextView.setText(R.string.help_data);
	 }
}
