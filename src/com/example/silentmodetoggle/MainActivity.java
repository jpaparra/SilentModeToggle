package com.example.silentmodetoggle;

import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private AudioManager audioManager;
	private boolean phoneIsSilent;
	
	private static final String TAG = "SilentModeApp";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		checkIfPhoneIsSilent();
		buttonClickListener();
		
		Log.d(TAG, "This is a test");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void buttonClickListener() {
		Button toggleButton = (Button) findViewById(R.id.toggleButton);
		toggleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(phoneIsSilent) {
					// Change back to normal mode
					audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
					phoneIsSilent = false;
				} else {
					// Change to silent mode
					audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
					phoneIsSilent = true;
				}
				
				// Now toggle the UI again
				toggleUI();
			}
		});
	}

	private void checkIfPhoneIsSilent() {
		int ringerMode = audioManager.getRingerMode();
		if(ringerMode == AudioManager.RINGER_MODE_SILENT) {
			phoneIsSilent = true;
		} else {
			phoneIsSilent = false;
		}
	}

	private void toggleUI() {
		ImageView imageView = (ImageView) findViewById(R.id.phone_icon);
		Drawable newPhoneImage;
		
		if(phoneIsSilent) {
			newPhoneImage = getResources().getDrawable(R.drawable.phone_silent);
		} else {
			newPhoneImage = getResources().getDrawable(R.drawable.phone_on);
		}
		imageView.setImageDrawable(newPhoneImage);
	}

	@Override
	public void onResume() {
		super.onResume();
		checkIfPhoneIsSilent();
		toggleUI();
	}

}
