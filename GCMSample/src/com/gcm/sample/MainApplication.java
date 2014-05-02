package com.gcm.sample;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;

import com.gcm.lib.GCM;
import com.gcm.lib.GCMListener;
import com.gcm.lib.RegisterEnum;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class MainApplication extends Application implements GCMListener {

	/**
	 * SENDER ID - you can get it from https://cloud.google.com/console and
	 * register your project and set proejct ID here.
	 */
	private String SENDER_ID = "";
	private GCM gcm;

	@Override
	public void onCreate() {
		super.onCreate();
		if (TextUtils.isEmpty(SENDER_ID)) {
			Toast.makeText(getApplicationContext(), "Set SENDER_ID", Toast.LENGTH_LONG).show();
		}
		gcm = new GCM(getApplicationContext(), SENDER_ID, this);
		gcm.register();
	}

	@Override
	public void onRegister(RegisterEnum registerEnum, String message) {
		switch (registerEnum) {
		case SUCCESS:
			Toast.makeText(getApplicationContext(), "Register ID: " + message, Toast.LENGTH_LONG).show();
			break;
		case FAIL:
			Toast.makeText(getApplicationContext(), "Registration Fail: " + message, Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}

	@Override
	public void OnNotificationReceived(String message, String gcmType) {
		if (gcmType.equalsIgnoreCase(GoogleCloudMessaging.MESSAGE_TYPE_DELETED)) {
			Toast.makeText(getApplicationContext(), "Message Deleted: " + message, Toast.LENGTH_LONG).show();
		} else if (gcmType.equalsIgnoreCase(GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR)) {
			Toast.makeText(getApplicationContext(), "Message Send Error: " + message, Toast.LENGTH_LONG).show();
		} else if (gcmType.equalsIgnoreCase(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE)) {
			Toast.makeText(getApplicationContext(), "Message:" + message, Toast.LENGTH_LONG).show();
		}
	}

}
