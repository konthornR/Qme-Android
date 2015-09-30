package com.example.testapp;

import org.json.JSONException;
import org.json.JSONObject;

public class CutQrResult {
	public String Cutcurrenttime(String QrResult) {

		String t = QrResult.substring(QrResult.lastIndexOf("@") + 1);
		return (t);
	}

	public String cutAdvertise(String QrResult) {
		String t = QrResult.substring(0, QrResult.indexOf("!"));
		return t;
	}

	public String cutWaitingtime(String QrResult) {
		String t = QrResult.substring(QrResult.indexOf("!") + 1,
				QrResult.indexOf("@"));
		return t;
	}

	public String cutTableNumber(String QrResult) {
		String t = QrResult.substring(QrResult.indexOf("@") + 1,
				QrResult.lastIndexOf("@"));
		return t;
	}

	public String cutid(String QrResult) {
		int start = (QrResult.indexOf("Id")) + 5;
		int end = start + 36;
		String t = QrResult.substring(start, end);
		return t;
	}

	public static String cutName(String QrResult) throws JSONException {

		JSONObject j = new JSONObject(QrResult);
		
		if (j.has("Name")) {
			String name = j.getString("Name");
			String seat = j.getString("NumberOfSeats");
			
			// String rr = j.getJSONObject("LabelData").getString("slogan");
			return name + "\nจองสำหรับ " + seat + " ท่าน";
		}
		if (j.has("QueueNumber")) {
			String order = j.getString("QueueNumber");
			if(order.equals("0")){
				return ("ถึงคิวของคุณแล้วค่ะ");
			}
			else {
			return ("อีก " + order +" คิว");
		}
			}
		return null;
	}
	
	public static String cutQ(String QrResult) throws JSONException{
		JSONObject j = new JSONObject(QrResult);
	
		
			int queuelist = j.getInt("QueueNumber");
			// String rr = j.getJSONObject("LabelData").getString("slogan");
			return "อีก " + queuelist + " คิว";
		
	}

}
