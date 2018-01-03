package com.grepthor.weforyoufoundation.helper;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility 
{
	private Context context;
	
	public Utility(Context context)
	{
		this.context = context;
	}
	
	/**
	 * This is used to check email format
	 * 
	 * @author USER
	 * @param email
	 * @return
	 */
	public boolean checkEmail(String email) {
		boolean isValid = false;

		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;

		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) 
		{
			isValid = true;
		}
		return isValid;
	}
	
	/**
	 * This is used to check weather Internet is on or off
	 * 
	 * @author USER
	
	 * @return
	 */
	
	public boolean checkInternet() {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (connectivity != null) {
				NetworkInfo[] info = connectivity.getAllNetworkInfo();
				if (info != null)
					for (int i = 0; i < info.length; i++)
						// check if network is connected or device is in range
						if (info[i].getState() == NetworkInfo.State.CONNECTED) {
							return true;
						}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getCountryCode()
	{
		
		
		String countryCode = null;
		try {
			TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
			  countryCode = tm.getSimCountryIso();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return countryCode;
		
	}

	public String getDeviceId()
	{
		String imeistring="",imsistring="";
		TelephonyManager telephonyManager;

		try
		{
			int permissionCheck = ContextCompat.checkSelfPermission(context,
					Manifest.permission.READ_PHONE_STATE);

			telephonyManager  = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

		/*
		* getDeviceId() function Returns the unique device ID.
		* for example,the IMEI for GSM and the MEID or ESN for CDMA phones.
		*/
			imeistring = telephonyManager.getDeviceId();

		/*
		* getSubscriberId() function Returns the unique subscriber ID,
		* for example, the IMSI for a GSM phone.
		*/
			//imsistring = telephonyManager.getSubscriberId();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return imeistring;
	}

	public String convertStringToBase64(String toEncodeString) throws UnsupportedEncodingException
	{
		byte[] data = toEncodeString.getBytes("UTF-8");
		String base64 = Base64.encodeToString(data, Base64.DEFAULT);

		return base64;
	}

	public String convertBase64ToString(String toDecodeString) throws UnsupportedEncodingException
	{
		byte[] data = Base64.decode(toDecodeString, Base64.DEFAULT);
		String text = new String(data, "UTF-8");

		return text;
	}



	public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 101;
	public static final int MY_PERMISSIONS_REQUEST_CAMERA = 102;

	public static final int MY_PERMISSIONS_REQUEST_LOCATION_HARDWARE = 201;
	public static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 202;
	public static final int MY_PERMISSIONS_REQUEST_COARSE_LOCATION = 203;

	@TargetApi(Build.VERSION_CODES.M)
	public static boolean checkPermission(final Context context) {
		boolean flag = false;
		int currentAPIVersion = Build.VERSION.SDK_INT;

		//if os is MARSHMALLOW
		if (currentAPIVersion >= Build.VERSION_CODES.M) {
			//External Storage permission
			if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
				if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
					alertBuilder.setCancelable(true);
					alertBuilder.setTitle("Permission necessary");
					alertBuilder.setMessage("External storage permission is necessary");
					alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
						@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
						public void onClick(DialogInterface dialog, int which) {
							ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
						}
					});
					AlertDialog alert = alertBuilder.create();
					alert.show();
				} else {
					ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
				}
				flag = false;
			} else {
				flag = true;
			}

			//Camera permission
			if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
				if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA)) {
					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
					alertBuilder.setCancelable(true);
					alertBuilder.setTitle("Permission necessary");
					alertBuilder.setMessage("Camera Use to take photos.");
					alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
						@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
						public void onClick(DialogInterface dialog, int which) {
							ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
						}
					});
					AlertDialog alert = alertBuilder.create();
					alert.show();
				} else {
					ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
				}

				flag = false;
			} else {
				flag = true;
			}

			//location hardware
			if (ContextCompat.checkSelfPermission(context, Manifest.permission.LOCATION_HARDWARE) != PackageManager.PERMISSION_GRANTED) {
				if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.LOCATION_HARDWARE)) {
					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
					alertBuilder.setCancelable(true);
					alertBuilder.setTitle("Permission necessary");
					alertBuilder.setMessage("Location permission required for tagging the location.");
					alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
						@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
						public void onClick(DialogInterface dialog, int which) {
							ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.LOCATION_HARDWARE}, MY_PERMISSIONS_REQUEST_LOCATION_HARDWARE);
						}
					});
					AlertDialog alert = alertBuilder.create();
					alert.show();
				} else {
					ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.LOCATION_HARDWARE}, MY_PERMISSIONS_REQUEST_LOCATION_HARDWARE);
				}

				flag = false;
			} else {
				flag = true;
			}

			//fine location
			if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
				if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION)) {
					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
					alertBuilder.setCancelable(true);
					alertBuilder.setTitle("Permission necessary");
					alertBuilder.setMessage("Location permission required for tagging the location.");
					alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
						@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
						public void onClick(DialogInterface dialog, int which) {
							ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
						}
					});
					AlertDialog alert = alertBuilder.create();
					alert.show();
				} else {
					ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
				}

				flag = false;
			} else {
				flag = true;
			}

			//COARSE LOCATION
			if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
				if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_COARSE_LOCATION)) {
					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
					alertBuilder.setCancelable(true);
					alertBuilder.setTitle("Permission necessary");
					alertBuilder.setMessage("Location permission required for tagging the location.");
					alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
						@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
						public void onClick(DialogInterface dialog, int which) {
							ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_COARSE_LOCATION);
						}
					});
					AlertDialog alert = alertBuilder.create();
					alert.show();
				} else {
					ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_COARSE_LOCATION);
				}

				flag = false;
			} else {
				flag = true;
			}
		} else {
			flag = true;
		}
		return flag;
	}
}
