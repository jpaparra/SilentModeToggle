package com.example.silentmodetoggle;

import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.RemoteViews;

public class ToggleService extends IntentService {

	public ToggleService() {
		super("SilentModeWidget$ToggleService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		ComponentName cn = new ComponentName(this, SilentModeWidget.class);
		AppWidgetManager mgr = AppWidgetManager.getInstance(this);
		mgr.updateAppWidget(cn, buildUpdate(this));
	}

	private RemoteViews buildUpdate(Context context) {
		RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.silentmodewidget);
		AudioManager audioManager = (AudioManager)context.getSystemService(Activity.AUDIO_SERVICE);
		if(audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
			updateViews.setImageViewResource(R.id.phoneState, R.drawable.phone_on);
			audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		} else {
			updateViews.setImageViewResource(R.id.phoneState, R.drawable.phone_silent);
			audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		}
		Intent i = new Intent(this, SilentModeWidget.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
		updateViews.setOnClickPendingIntent(R.id.phoneState, pi);
		return updateViews;
	}

}
