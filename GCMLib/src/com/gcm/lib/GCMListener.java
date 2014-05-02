package com.gcm.lib;


public interface GCMListener {

	public void onRegister(RegisterEnum registerEnum, String message);
	
	public void OnNotificationReceived(String message, String gcmType);
}
