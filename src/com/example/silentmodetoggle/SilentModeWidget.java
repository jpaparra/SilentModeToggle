package com.example.silentmodetoggle;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class SilentModeWidget extends AppWidgetProvider {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction() == null) {
			context.startService(new Intent(context, ToggleService.class));
		} else {
			super.onReceive(context, intent);
		}
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		context.startService(new Intent(context, ToggleService.class));
	}
}
