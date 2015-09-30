package com.example.testapp;

import android.R.color;
import android.R.layout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.util.Log;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard.Key;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.Qme.testapp.R;
import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;
import com.example.testapp.socketio_connect.RelaxedHostNameVerifier;

import net.sourceforge.zbar.Symbol;
import android.os.Vibrator;
import android.view.ViewGroup.LayoutParams;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.os.Handler;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.acl.LastOwnerException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONException;
import org.json.JSONObject;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MainActivity extends ActionBarActivity {

	private boolean mIsInForegroundMode;
	// /////
	// list รายการต่างๆ
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	PrintWriter printwriter;
	String messsage = null;
	// ///
	int count = 0;
	int androidCodeversion;
	TextView text, descriptionview,header,descriptionview2;
	String casecount1 = "1";
	String g = "kk";

	String temp, temp2, temp3,p;
	PhotoViewAttacher mAttacher;
	public int currentimageindex = 0;
	// Timer timer;
	// TimerTask task;
	ImageView slidingimage,waiting;
	//ImageView expandimg;
	SocketIO socket;
	boolean blockdoublepress= false;
	boolean cancelQueue;
	// array รูปต่างๆ
	private int[] IMAGE_IDS = { R.drawable.sushi, R.drawable.grilledfish,
			R.drawable.beitempura, R.drawable.misosoup };

	private String[] IMAGE_url = { "http://duiclub.5gbfree.com/promo/fuji_promo.jpg",
			"http://duiclub.5gbfree.com/promo/fuji_promo2.jpg",
			"http://duiclub.5gbfree.com/promo/fuji_promo3.jpg",
			"http://duiclub.5gbfree.com/promo/ktc_promo.jpg" };

//	private String[] IMAGE_url = { "https://upload.wikimedia.org/wikipedia/commons/1/1e/Tom's_Restaurant,_NYC.jpg",
//			"https://bitesizedtravel.files.wordpress.com/2013/04/img_8931-cut.jpg",
//			"https://upload.wikimedia.org/wikipedia/commons/1/1e/Tom's_Restaurant,_NYC.jpg",
//			"https://bitesizedtravel.files.wordpress.com/2013/04/img_8931-cut.jpg" };

	
	//ImageButton imgbtn1;
	ImageView imgbtn1;
	public Activity Activity;
	boolean startingintent = false;
	String testString;
	
	Button testbtn;
	View linearlayout;
	private final long startTime = 30 * 1000;
	private final long interval = 1 * 1000;
	private CountDownTimer countDownTimer;
	long timeQrL;

	// sound
	SoundPool soundPool;
	int soundID;
	boolean loaded = false;

	private static final int ZBAR_SCANNER_REQUEST = 0;
	private static final int ZBAR_QR_SCANNER_REQUEST = 1;
	private static final int NOTIFY_ME_ID = 1337;
	ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onPause() {
	    super.onPause();
	    mIsInForegroundMode = false;
	}

	@Override
	protected void onResume() {
	    super.onResume();
	    mIsInForegroundMode = true;
	}

	// Some function.
	public boolean isInForeground() {
	    return mIsInForegroundMode;
	}
	
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
cancelQueue = false;
		
		Typeface tf = Typeface.createFromAsset(this.getAssets(),
	            "fonts/Franklin Gothic Book Regular.ttf");
		//
		// new DownloadImageTask((ImageView) findViewById(R.id.imageView2))
		// .execute("http://theopentutorials.com/totwp331/wp-content/uploads/totlogo.png");
		//
		//
		//
		// new DownloadImageTask((ImageView) findViewById(R.id.imageView2))
		// .execute("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464");
		//

//		CutQrResult cutting = new CutQrResult();
//		// final socketio_connect soc = new socketio_connect();
//		// g = soc.connectIo(testString);
//		//
//		// อ้างอิงไปยัง DrawerLayout ที่เป็น root element ของ layout file
//		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//		// อ้างอิงไปยัง ListView ซึ่งเป็นตัว drawer ที่จะเลื่อนออกมา
//		mDrawerList = (ListView) findViewById(R.id.left_drawer);
//
//		// กำหนด Adapter ให้กับ ListView
//		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
//				R.layout.list_item, maintain_data.listdrawer()));
//		// กำหนด click listener ให้กับ ListView
//		// เพื่อระบุการทำงานเมื่อไอเท็มในลิสต์ถูกคลิกเลือก
//		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
//
//		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
//				R.drawable.ic_drawer, R.string.title_section1,
//				R.string.title_section2) {
//			/** Called when drawer is closed */
//			public void onDrawerClosed(View view) {
//				getActionBar().setTitle("Q-Me");
//				invalidateOptionsMenu();
//			}
//
//			/** Called when a drawer is opened */
//			public void onDrawerOpened(View drawerView) {
//				getActionBar().setTitle("Q-Me's Choice");
//				invalidateOptionsMenu();
//			}
//
//		};
//
//		// Setting DrawerToggle on DrawerLayout
//		mDrawerLayout.setDrawerListener(mDrawerToggle);
//		getActionBar().setDisplayHomeAsUpEnabled(true);
//		getActionBar().setHomeButtonEnabled(true);

		final Handler mHandler = new Handler();
		// Create runnable for posting
		final Runnable mUpdateResults = new Runnable() {

			public void run() {
				
				if(mIsInForegroundMode==true){
				AnimateandSlideShow();
				}
			}

		};

		int delay = 2000; // delay for 1 sec.

		int period = 5500; // repeat every 4 sec.

		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {

				mHandler.post(mUpdateResults);

			}
			

		}, delay, period);

		
		
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.logoofdui)
				.setContentTitle("Welcome to Q-Me >3< ")
				.setContentText("Queuing for a better queue");
		
		
		final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(0, mBuilder.build());
		

		waiting = (ImageView) findViewById(R.id.imageView1);
		imgbtn1 = (ImageView) findViewById(R.id.imageButton1);
	//	expandimg = (ImageView) findViewById(R.id.expand);

		header = (TextView) findViewById(R.id.textView1);

		header.setTypeface(tf,Typeface.BOLD);
		
		
		slidingimage = (ImageView) findViewById(R.id.imageView2);
		descriptionview = (TextView) findViewById(R.id.description1);
		descriptionview2 = (TextView) findViewById(R.id.description2);

		descriptionview.setTypeface(tf,Typeface.BOLD);
		descriptionview2.setTypeface(tf,Typeface.BOLD);
		//descriptionview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

		waiting.setVisibility(View.INVISIBLE);
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// Load the sound
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {

			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				loaded = true;
			}
		});
		soundID = soundPool.load(this, R.raw.queue, 1);
		// / notification
		final NotificationManager mgr = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);
		final Notification note = new Notification(R.drawable.logoofdui,
				"Android Example Status message!", System.currentTimeMillis());
		// This pending intent will open after notification click
		PendingIntent i = PendingIntent.getActivity(this, 0, new Intent(this,
				NotifyMessage.class), 0);
	
	
		note.setLatestEventInfo(this, "Welcome to Q-Me \\(^o^)//",
				" Queuing with a better queue", i);

		// After uncomment this line you will see number of notification arrived
		// note.number=2;
		// mgr.notify(NOTIFY_ME_ID, note);
		

		imgbtn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Getintent();
				// launchScanner(v);
			
				if(blockdoublepress == true){
					//launchQRScanner(v);
					
					LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
							.getSystemService(LAYOUT_INFLATER_SERVICE);
					View popupView = layoutInflater.inflate(R.layout.popup_dbclick,
							null);
					final PopupWindow popupWindow2 = new PopupWindow(popupView,
							LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				popupWindow2.showAtLocation(v, Gravity.CENTER, 0, 0);
					
					Button btnacceptDbclick = (Button) popupView.findViewById(R.id.acceptDbClick);
					Button btncancelDbclick = (Button) popupView.findViewById(R.id.CancleDbClick);
					btnacceptDbclick.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Toast.makeText(getApplicationContext(), "Cancel previous queue", Toast.LENGTH_LONG).show();
							waiting.setVisibility(View.INVISIBLE);
							popupWindow2.dismiss();
							cancelQueue = true;
						}
					});
					
					btncancelDbclick.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							popupWindow2.dismiss();
						}
					});
				}
				
				
				
				if(blockdoublepress==false){
					launchQRScanner(v);
					imgbtn1.setEnabled(false);
					LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
							.getSystemService(LAYOUT_INFLATER_SERVICE);
					View popupView = layoutInflater.inflate(R.layout.popupconfirm,
							null);
					final PopupWindow popupWindow = new PopupWindow(popupView,
							LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					Button btnaccept = (Button) popupView.findViewById(R.id.accept);
					Button btncancel = (Button) popupView
							.findViewById(R.id.buttonCancle);

					btnaccept.setOnClickListener(new Button.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							imgbtn1.setEnabled(true);
							if(testString!= null){
								waiting.setVisibility(View.VISIBLE);
							// notification start
							mgr.notify(NOTIFY_ME_ID, note);
							// //////////////////////////////////////////////////////////
					
							try {
								Intent intent = new Intent(getApplicationContext(), MainActivity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
								
								JSONObject obj = new JSONObject(testString);
								String announce = obj.get("CompanyId").toString();
								NotificationCompat.Builder mBuilder1 = new NotificationCompat.Builder(
										getApplicationContext())
										.setSmallIcon(R.drawable.logoofdui)
										.setContentTitle("Q-Me announcement  \\(^-^)//")
										.setContentText(announce)
										.setContentIntent(pendingIntent);
								
								
								mNotificationManager.notify(0, mBuilder1.build());
								
									} 
							
							
							catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							new socketioasyn().execute(testString);
							blockdoublepress = true;
							

							// String queue = connectIo(testString);

							// ///////////////////////////////////////////////////////////////////
							if (testString != null) {
								casecount1 = "2";
								// if(testString!=null){
								CutQrResult c = new CutQrResult();
								try {

									// // notify with advertising with

//									NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
//											getApplicationContext())
//											.setSmallIcon(R.drawable.qme)
//											.setContentTitle(
//													"Q-Me announcement  \\(^-^)//")
//											.setContentText(
//													c.cutAdvertise(testString));
//									mNotificationManager.notify(0, mBuilder.build());

								
									double timeQr = Double.parseDouble(c
											.cutWaitingtime(testString));
									timeQrL = (long) (timeQr * 1000);
									Toast.makeText(getApplicationContext(),
											" " + timeQrL, Toast.LENGTH_LONG)
											.show();
								} catch (Exception e) {
									// TODO: handle exception
									timeQrL = (long) 0.0;

								}
								if (casecount1 == "1") {
									MyCountDownTimer(timeQrL, interval);

									descriptionview
											.setText("  You're requesting for table of "
													+ c.cutTableNumber(testString)
													+ ".");
									Toast.makeText(getApplicationContext(),
											"Welcome to Q-Me; Queuing application",
											Toast.LENGTH_LONG).show();
								}

								if (casecount1 == "2") {
									MyCountDownTimer(50000, 100000);
								}
							}
									blockdoublepress = true;
							popupWindow.dismiss();
						}
							else {
								popupWindow.dismiss();
								Toast.makeText(getApplication(), "Please scan the Qrcode at the store", Toast.LENGTH_LONG).show();
								descriptionview.setText("Touch to Scan Queue at the store");
								descriptionview2.setText("กดและสแกน QR ที่หน้าร้าน");
							}
						
						}
						
							
						
					});

					btncancel.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							imgbtn1.setEnabled(true);
							waiting.setVisibility(View.INVISIBLE);
							popupWindow.dismiss();
							blockdoublepress = false;
							Toast.makeText(getApplicationContext(), "คุณได้ยกเลิกการต่อคิว", Toast.LENGTH_LONG).show();
							descriptionview.setText("Touch to Scan Queue at the store");
							descriptionview2.setText("กดและสแกน QR ที่หน้าร้าน");
						}
					});

					// popupWindow.showAsDropDown(but, 10, -30);
					popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

				}
				
		
				
			
			}
			
		
		});

		// Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// // Vibrate for 500 milliseconds
		// vib.vibrate(500);
		// /////
		// testbtn.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// Intent browserIntent = new Intent(Intent.ACTION_VIEW,
		// Uri.parse("http://www.fuji.co.th/2009/TH/home/index.php"));
		// startActivity(browserIntent);
		//
		// }
		// });
		//
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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

	public void Getintent() {

		IntentIntegrator scanIntegrator = new IntentIntegrator(this);
		scanIntegrator.initiateScan();

	}


	// //
	public void launchScanner(View v) {
		if (isCameraAvailable()) {
			Intent intent = new Intent(this, ZBarScannerActivity.class);
			startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
		} else {
			Toast.makeText(this, "Rear Facing Camera Unavailable",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void launchQRScanner(View v) {
		if (isCameraAvailable()) {
			Intent intent = new Intent(this, ZBarScannerActivity.class);
			intent.putExtra(ZBarConstants.SCAN_MODES,
					new int[] { Symbol.QRCODE });
			startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
		} else {
			Toast.makeText(this, "Rear Facing Camera Unavailable",
					Toast.LENGTH_SHORT).show();
		}
	}

	public boolean isCameraAvailable() {
		PackageManager pm = getPackageManager();
		return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case ZBAR_SCANNER_REQUEST:
		case ZBAR_QR_SCANNER_REQUEST:
			if (resultCode == RESULT_OK) {
				testString = data.getStringExtra(ZBarConstants.SCAN_RESULT);
				Toast.makeText(getApplicationContext(), "Q-Me is processing",
						Toast.LENGTH_LONG).show();
				descriptionview.setText("Qme กำลังประมวลผลคำสั่งของคุณ");
				descriptionview2.setText("โปรดรอการยืนยันคิว");
			} else if (resultCode == RESULT_CANCELED && data != null) {
				
				String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
				if (!TextUtils.isEmpty(error)) {
					Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
				}
			}
			break;
		}
	}
	// นับถอยหลัง
	public void MyCountDownTimer(long startTime, final long interval) {

		new CountDownTimer(startTime, interval) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				if (casecount1 == "1") {
					if (millisUntilFinished / 1000 >= 5
							&& millisUntilFinished / 1000 < 10) {

						Toast.makeText(getApplicationContext(),
								"ใกล้ถึงคิวของคุณแล้วนะครับ", Toast.LENGTH_LONG)
								.show();
						// mTextField.setText("seconds remaining: " +
						// millisUntilFinished / 1000);
					} else if (millisUntilFinished / 1000 >= 30
							&& millisUntilFinished / 1000 < 40) {
						CutQrResult adcut = new CutQrResult();
						Toast.makeText(getApplicationContext(),
								adcut.cutAdvertise(testString),
								Toast.LENGTH_LONG).show();
						// mTextField.setText("seconds remaining: " +
						// millisUntilFinished / 1000);
					}

					else {
						// mTextField.setText("seconds remaining: " +
						// millisUntilFinished / 1000);
					}
				}

				if (casecount1 == "1") {

					if (millisUntilFinished / 1000 >= 5
							&& millisUntilFinished / 1000 < 10) {
						// socketio_connect soc = new socketio_connect();
						// g = soc.connectIo(testString);

						Toast.makeText(getApplicationContext(),
								"ใกล้ถึงคิวของคุณแล้วนะครับ", Toast.LENGTH_LONG)
								.show();
						// mTextField.setText("seconds remaining: " +g);
						// g = soc.connectIo(testString);
					}
					// else if(millisUntilFinished / 1000 >= 30&&
					// millisUntilFinished / 1000<40){
					// CutQrResult adcut = new CutQrResult();
					// Toast.makeText(getApplicationContext(),
					// adcut.cutAdvertise(testString),
					// Toast.LENGTH_LONG).show();
					// mTextField.setText("seconds remaining: " +
					// millisUntilFinished / 1000);
					// }

					else {
						// mTextField.setText("seconds remaining: " +
						// millisUntilFinished / 1000);
					}

				}

			}

			@Override
			public void onFinish() {
				
			}
		}.start();

	}

	// // slide show picture
	int call = 2;

	private void AnimateandSlideShow() {
		
		new DownloadImageTask((ImageView) findViewById(R.id.imageView2))
				.execute(IMAGE_url[count]);
	if(cancelQueue == false){	
		new socketioasyn().execute(testString);



		if (temp3 == null) {
			if (temp2 != null) {
				try {
					descriptionview.setText(CutQrResult.cutName(temp2));
					descriptionview2.setText(CutQrResult.cutQ(temp2));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (temp3 != null) {
			try {
				descriptionview2.setText(CutQrResult.cutName(temp3));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
			 p = temp3.substring(temp3.length() - 3, temp3.length() - 1);
			if (p.trim().toString().equalsIgnoreCase(":1") && call == 2) {

				LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
						.getSystemService(LAYOUT_INFLATER_SERVICE);
				View popupView = layoutInflater.inflate(R.layout.popup, null);
				final PopupWindow popupWindow = new PopupWindow(popupView,
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				Button btnDismissAccept = (Button) popupView.findViewById(R.id.Start);
				Button btnDismisscancel = (Button) popupView.findViewById(R.id.DismissQueue);
				btnDismissAccept.setOnClickListener(new Button.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// g = soc.connectIo(testString);
						// Toast.makeText(getApplicationContext(), g,
						// Toast.LENGTH_LONG).show();
						popupWindow.dismiss();
						blockdoublepress = false;
						testString = null;
						Toast.makeText(getApplicationContext(), "Please go back to the shop.\nโปรดกลับไปที่ร้านค้า", Toast.LENGTH_LONG).show();
					}
				});
				
				
				btnDismisscancel.setOnClickListener(new Button.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
//						blockdoublepress = false;
//						testString = null;
						popupWindow.dismiss();
					//	Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_LONG).show();
						cancelQueue = true;
						
					}
					
				});
				
				
				View v = new View(getApplicationContext());
				popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

				call++;
			}
		}

		

		count++;
		if (count >= IMAGE_url.length) {
			count = 0;
			// ///////////////////////// wake up the screen
			// ////////<<<<<<<<<<<<<<<<<<<<<<<<<<<<,

		}
}
	else{
		Toast.makeText(getApplicationContext(), "Your queue has been cancel\n คิวของคุณได้ถูกยกเลิกแล้ว", Toast.LENGTH_LONG).show();
		
		descriptionview.setText("Touch to Scan Queue at the store");
		descriptionview2.setText("กดและสแกน QR ที่หน้าร้าน");
		call =2;
		testString = null;
		blockdoublepress = false;
		temp2 = null;
		temp3 = null;
		temp = null;
		cancelQueue = false;
//	count = 0;
//	casecount1 = "1";
	//p = null;

	}
		
	}

	// ////////
	public class DrawerItemClickListener implements
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

			if (position == 1) {
				Intent browserIntent = new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("http://www.fuji.co.th/2009/TH/home/index.php"));
				startActivity(browserIntent);
			}
			if (position == 2) {

				// startActivity(new Intent("Menus_activity01"));
				Intent myIntent = new Intent("ChooseRestaurant");
				
				myIntent.putExtra("extra", temp + " ");
				startActivityForResult(myIntent, 0);
			}

			if (position == 3) {
				Intent myIntent = new Intent("Promotions");
				myIntent.putExtra("extra", temp + " ");
				startActivityForResult(myIntent, 0);
			}
			// ปิด drawer
			// (ถ้าไม่สั่งปิดจะแสดงค้างอยู่อย่างนั้นจนกว่าผู้ใช้จะปิดเอง)
			mDrawerLayout.closeDrawer(mDrawerList);
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

	// Socket socket = null;

	@SuppressLint("NewApi")
	public String connectIo(final String scan) {
//		try {
//
//			SSLContext sc = SSLContext.getInstance("TLS");
//			sc.init(null, trustAllCerts, new SecureRandom());
//			socket.setDefaultSSLSocketFactory(sc);
//			HttpsURLConnection
//					.setDefaultHostnameVerifier(new RelaxedHostNameVerifier());
//			socket = new SocketIO(
//					"https://murmuring-fjord-5701.herokuapp.com:443/"
//				//	"http://qmeapp.com:80/"
//					);
//
//			// socket = new SocketIO("http://192.168.1.107:3000/");
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (KeyManagementException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

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
				System.gc();

				if ("call queue".equals(arg0) && arg2.length > 0) {
					System.gc();
					Log.d("SocketIO", "nn" + arg2[0]);

					temp3 = arg2[0].toString();
					// descriptionview.setText(temp3);

					// -> "hello"

				}
				if ("socket id connection".equals(arg0) && arg2.length > 0) {

					temp = arg2[0].toString();
					try {
						JSONObject json = new JSONObject(temp);
						temp = json.get("SocketId").toString();
						// Log.d("SocketIO", "socketid" + temp);
						// Log.d("SocketIO", "scan" + scan);
						// socket.emit("customer register code",
						// "{\"Id\": \"ab380f35-f1c6-4609-b0ed-a2b0ef0e6818\" , \"SocketId\": \"Dui\"}");
						JSONObject obj = new JSONObject();
						obj.put("Id", scan);
						obj.put("SocketId", temp);
						socket.emit("customer register code", obj);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				if ("customer information".equals(arg0) && arg2.length > 0) {
					Log.d("SocketIO", "customer info" + arg2[0]);
					temp2 = arg2[0].toString();

				}
			}
		});
		return temp3;

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

	IOAcknowledge ack = new IOAcknowledge() {
		@Override
		public void ack(Object... args) {
			if (args.length > 0) {
				Log.d("SocketIO", "" + args[0]);
			}

		}
	};

	private class socketioasyn extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(final String... params) {
			// TODO Auto-generated method stub
			


			try {

		

				SSLContext sc = SSLContext.getInstance("TLS");
				sc.init(null, trustAllCerts, new SecureRandom());
				socket.setDefaultSSLSocketFactory(sc);
				HttpsURLConnection
						.setDefaultHostnameVerifier(new RelaxedHostNameVerifier());
				socket = new SocketIO(
						//"https://murmuring-fjord-5701.herokuapp.com:443/"
						"http://qmeapp.com:80/"
						);

				// socket = new SocketIO("http://192.168.1.107:3000/");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
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
					if (cancelQueue) {
						
						JSONObject obj;
						try {
							obj = new JSONObject(params[0]);
							String Qr = obj.getString("Id").toString();
						JSONObject objCancel = new JSONObject();
						objCancel.put("Id", Qr);
						socket.emit("request cancel queue",objCancel);
						testString = null;
						Qr= null;
						temp2=null;
						temp3 = null;
						
						
						
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				
					}
					//request cancel queue
					if ("cancel queue confirm".equals(arg0) && arg2.length > 0) {
						Log.d("SocketIO", "cancel queue" + arg2[0]);
						
						//Toast.makeText(getApplicationContext(), "Your Queue has been cancel", Toast.LENGTH_LONG).show();

					}
					if ("test connection back".equals(arg0) && arg2.length > 0) {
						Log.d("SocketIO", "customer info" + arg2[0]);
						temp2 = arg2[0].toString();

					}
					if ("customer information".equals(arg0) && arg2.length > 0) {
						Log.d("SocketIO", "customer info" + arg2[0]);
						temp2 = arg2[0].toString();
						//descriptionview.setText("come here");
					}

					if ("call queue".equals(arg0) && arg2.length > 0) {
						
						Log.d("SocketIO", "nn" + arg2[0]);

						temp3 = arg2[0].toString();
						 //descriptionview.setText(temp3);
						 
							String p = temp3.substring(temp3.length() - 3, temp3.length() - 1);
							if (p.trim().toString().equalsIgnoreCase(":1") && call == 2) {
						 PowerManager pm = (PowerManager) getApplicationContext()
									.getSystemService(Context.POWER_SERVICE);
							boolean isScreenOn = pm.isScreenOn();
						 if (isScreenOn == false) {
								WakeLock wakeLock = pm
										.newWakeLock(
												(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
														| PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP),
												"TAG");
								wakeLock.acquire();
								startActivity(new Intent("wakeupAct"));
								
						 }
								AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
								float actualVolume = (float) audioManager
										.getStreamVolume(AudioManager.STREAM_MUSIC);
								float maxVolume = (float) audioManager
										.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
								float volume = actualVolume / maxVolume;
								// Is the sound loaded already?
								if (loaded) {
									soundPool.play(soundID, volume, volume, 1, 0, 1f);
									Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); // Vibrate
																											// for
																											// 500
																											// milliseconds
									vib.vibrate(500);
								}
							
							}
						// -> "hello"

					}
					if ("socket id connection".equals(arg0) && arg2.length > 0) {

						temp = arg2[0].toString();
					//descriptionview.setText(temp);
						try {
//							JsonObject objconv = new JsonParser().parse(temp.toString()).getAsJsonObject();
							JSONObject objconv = new JSONObject(temp);
							String socketId = objconv.get("SocketId").toString();
							JSONObject obj = new JSONObject(params[0]);
							String Qr = obj.getString("Id").toString();
							String CompanyId = obj.get("CompanyId").toString();
							//descriptionview.setText("---"+Qr);
						
						//	descriptionview.setText(po);
							// Log.d("SocketIO", "socketid" + temp);
							// Log.d("SocketIO", "scan" + scan);
							// socket.emit("customer register code",
							//String f = ("{\"Id\": \"ab380f35-f1c6-4609-b0ed-a2b0ef0e6818\" , \"SocketId\": \"Dui\"}");
							//JSONObject obj = new JSONObject();
							obj.put("Id",Qr );
							obj.put("SocketId",socketId);
							obj.put("CompanyId",CompanyId );
//							obj.put("Id",params[0] );
//							obj.put("SocketId",socketId);
//							obj.put("CompanyId","1" );
				//			String f = ("{\"Id\": \""+Qr+"\" , \"SocketId\": \""+socketId+"\",\"CompanyId\":\""+CompanyId+"\"}");
							socket.emit("customer register code",obj);
							//socket.emit("test connection","ff" );
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					
				}
			});
			return temp3;

		}

		protected void onPostExecute(Bitmap result) {
			temp3 = temp3;
		}

	}

}
