package com.example.testapp;


import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.callback.Callback;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.testapp.Menu_activity01.RelaxedHostNameVerifier;
import org.json.*;

public class socketio_connect {
	String temp,temp2;
SocketIO socket;
	
	// Socket socket = null;
		 PrintWriter printwriter;
		  String messsage = null;
		  
		  
		  @SuppressLint("NewApi")
		public  String connectIo(final String scan){
		  try {

				SSLContext sc = SSLContext.getInstance("TLS");
	            sc.init(null, trustAllCerts, new SecureRandom());
	         socket.setDefaultSSLSocketFactory(sc);
	            HttpsURLConnection.setDefaultHostnameVerifier(new RelaxedHostNameVerifier());
				socket = new SocketIO("https://murmuring-fjord-5701.herokuapp.com:443/");
				
			//socket = new SocketIO("http://192.168.1.107:3000/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		socket.connect(new IOCallback() {
			
			@Override
			public void onMessage(JSONObject arg0, IOAcknowledge arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onMessage(String arg0, IOAcknowledge arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onError(SocketIOException arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDisconnect() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onConnect() {
				// TODO Auto-generated method stub
				//        
		
			
			}
		
				
			@Override
			public void on(String arg0, IOAcknowledge arg1, Object... arg2) {
				// TODO Auto-generated method stub
//				if ("update table".equals(arg0) && arg2.length > 0) {
//		            Log.d("SocketIO", "nn" + arg2[0]);
//		            temp = arg2[0].toString();
//		            // -> "hello"
//		
	//	        }
				if("socket id connection".equals(arg0) && arg2.length > 0){
				

			            temp = arg2[0].toString();
			            try {
							JSONObject json = new JSONObject(temp);
							temp = json.get("SocketId").toString();
//							 Log.d("SocketIO", "socketid" + temp);
//							 Log.d("SocketIO", "scan" + scan);
							//socket.emit("customer register code", "{\"Id\": \"ab380f35-f1c6-4609-b0ed-a2b0ef0e6818\" , \"SocketId\": \"Dui\"}");
							JSONObject obj = new JSONObject();
							obj.put("Id", scan);
							obj.put("SocketId", temp);
							socket.emit("customer register code", obj);  
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			            
				}
				
		
				
				
				
				if("customer information".equals(arg0) && arg2.length > 0){
					 Log.d("SocketIO", "customer info" + arg2[0]);
				temp = arg2[0].toString();
				
				}
			}
		});
		
	
		return temp;
		  }
		  
		  
		  private TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		            return new java.security.cert.X509Certificate[] {};
		        }

		        public void checkClientTrusted(X509Certificate[] chain,
		                                       String authType) throws CertificateException {
		        }

		        public void checkServerTrusted(X509Certificate[] chain,
		                                       String authType) throws CertificateException {
		        }
		    } };

		    public static class RelaxedHostNameVerifier implements HostnameVerifier {
		        public boolean verify(String hostname, SSLSession session) {
		            return true;
		        }
		    }
		    
			IOAcknowledge ack = new IOAcknowledge() {
				@Override
				public void ack(Object... args) {
				    if (args.length > 0) {
				        Log.d("SocketIO", "" + args[0]);
				    }
				    
				}
				};
}
