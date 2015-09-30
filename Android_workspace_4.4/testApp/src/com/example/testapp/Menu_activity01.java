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
import java.util.Stack;

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

import com.Qme.testapp.R;
import com.example.testapp.MainActivity.DrawerItemClickListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller.Session;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract.Document;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Menu_activity01 extends ActionBarActivity {
	
	TextView Descriptionmenu;
	Button fav;

	int urlNumb;
	int count = 0;
	ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	// private Socket socket;

	// Socket socket = null;
	PrintWriter printwriter;
	String messsage = null;
	Integer[] imageIDs = { R.drawable.sushi, R.drawable.misosoup,
			R.drawable.beitempura, R.drawable.grilledfish };

	private String[] IMAGE_url = { "http://duiclub.5gbfree.com/picturefood/sushi.jpg",
			"http://duiclub.5gbfree.com/picturefood/miso.jpg",
			"http://duiclub.5gbfree.com/picturefood/tempura.jpg",
			"http://duiclub.5gbfree.com/picturefood/sashimi.jpg" };
//	private String[] IMAGE_url_mk = { "http://duiclub.5gbfree.com/picturefood/duck.jpg",
//			"http://duiclub.5gbfree.com/picturefood/nomjeeb.jpg"
//			 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		 super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_activity01);
		//
		
		getActionBar().setTitle("Q-Me:Menu");
		// final String[] temp = {};
		final Stack temp = new Stack<String>();
		//
		
		
		
		// อ้างอิงไปยัง DrawerLayout ที่เป็น root element ของ layout file
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout01);
		// อ้างอิงไปยัง ListView ซึ่งเป็นตัว drawer ที่จะเลื่อนออกมา
		mDrawerList = (ListView) findViewById(R.id.left_drawer01);

		// กำหนด Adapter ให้กับ ListView
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.list_item, maintain_data.listdrawer()));
		// กำหนด click listener ให้กับ ListView
		// เพื่อระบุการทำงานเมื่อไอเท็มในลิสต์ถูกคลิกเลือก
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener01());

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.title_section1,
				R.string.title_section2) {
			/** Called when drawer is closed */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle("Q-Me");
				invalidateOptionsMenu();
			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Q-Me's Choice");
				invalidateOptionsMenu();
			}

		};

		// Setting DrawerToggle on DrawerLayout
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		//
		// // new Thread(new ClientThread()).start();
		// //
		Descriptionmenu = (TextView) findViewById(R.id.description2);
		fav = (Button) findViewById(R.id.favorite);
		fav.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// temp[count] = IMAGE_url[urlNumb];
				temp.push(IMAGE_url[urlNumb]);
				count = temp.size();
				Toast.makeText(getApplicationContext(),
						"This menu is added to your favorite dish " + count,
						Toast.LENGTH_LONG).show();

			}
		});
		//

		// Note that Gallery view is deprecated in Android 4.1---
		Gallery gallery = (Gallery) findViewById(R.id.gallery1);

		gallery.setAdapter(new ImageAdapter(this));
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if (position == 0) {
					Descriptionmenu.setText("Sushi set\n2,000 Yen");
				}
				if (position == 1) {
					Descriptionmenu.setText("Miso soup\n1,000 Yen");
				}
				if (position == 2) {
					Descriptionmenu.setText("Tempura set\n500 Yen");
				}
				// display the images selected
				ImageView imageView = (ImageView) findViewById(R.id.imagefood);
			
					new DownloadImageTask(imageView).execute(IMAGE_url[position]);
				
				urlNumb = position;
			}
		});
	}

	public class ImageAdapter extends BaseAdapter {
		private Context context;
		private int itemBackground;

		public ImageAdapter(Context c) {
			context = c;
			// sets a grey background; wraps around the images
			TypedArray a = obtainStyledAttributes(R.styleable.MyGallery);
			itemBackground = a.getResourceId(
					R.styleable.MyGallery_android_galleryItemBackground, 0);
			a.recycle();
		}

		// returns the number of images
		public int getCount() {
			return imageIDs.length;
		}

		// returns the ID of an item
		public Object getItem(int position) {
			return position;
		}

		// returns the ID of an item
		public long getItemId(int position) {
			return position;
		}

		// returns an ImageView view
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(context);
			// imageView.setImageResource(imageIDs[position]);
		
				new DownloadImageTask(imageView).execute(IMAGE_url[position]);
				imageView.setLayoutParams(new Gallery.LayoutParams(300, 300));
				imageView.setBackgroundResource(itemBackground);
				
				
			return imageView;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}

		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public class DrawerItemClickListener01 implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			// TODO Auto-generated method stub
			// ((TextView)
			// findViewById(R.id.textView)).setText(mPlanetTitles[position]);
			// Toast.makeText(getApplicationContext(), "test",
			// Toast.LENGTH_LONG).show();
			// ((TextView)
			// findViewById(R.id.textView1)).setText(mPlanetTitles[position]);

			if (position == 0) {
				finishAndRemoveTask();

				// Intent intent = new
				// Intent(getApplicationContext(),MainActivity.class);
				// startActivity(intent);
			}
			if (position == 1) {
				Intent browserIntent = new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("http://www.fuji.co.th/2009/TH/home/index.php"));
				startActivity(browserIntent);
			}
			if (position == 2) {
				finish();
				Intent myIntent = new Intent("ChooseRestaurant");
				myIntent.putExtra("extra", "");
				startActivityForResult(myIntent, 0);
			}
			if (position == 3) {
				finish();
				// startActivity(new Intent("Menus_activity01"));
				Intent myIntent = new Intent("Promotions");
				myIntent.putExtra("extra", "");
				startActivityForResult(myIntent, 0);
			}
			// ปิด drawer
			// (ถ้าไม่สั่งปิดจะแสดงค้างอยู่อย่างนั้นจนกว่าผู้ใช้จะปิดเอง)
			mDrawerLayout.closeDrawer(mDrawerList);
		}

	}

	private TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return new java.security.cert.X509Certificate[] {};
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}
	} };

	public static class RelaxedHostNameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}
	}

}
