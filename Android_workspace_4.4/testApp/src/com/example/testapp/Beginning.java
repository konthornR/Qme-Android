package com.example.testapp;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;



import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Timer;
import net.sourceforge.zbar.Symbol;

import com.Qme.testapp.R;
import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;
import com.google.zxing.integration.android.IntentIntegrator;

import uk.co.senab.photoview.PhotoViewAttacher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.TimerTask;

public class Beginning extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {
/////////////////////////////////////
	TextView text,descriptionview;
	
	PhotoViewAttacher mAttacher;
	  public int currentimageindex=0;
//    Timer timer;
//    TimerTask task;
    ImageView slidingimage;
    ImageView expandimg;
    
    //array รูปต่างๆ
    private int[] IMAGE_IDS = {
            R.drawable.sushi, R.drawable.grilledfish, R.drawable.beitempura,
            R.drawable.misosoup
        };

	ImageButton imgbtn12;
	public Activity Activity;
	boolean startingintent= false;	
	String testString;
	TextView mTextField,m2TextField;
	Button testbtn;
	View linearlayout;
	 private final long startTime = 30 * 1000;
	 private final long interval = 1 * 1000;
	 private CountDownTimer countDownTimer;
	 long timeQrL;
	
	//sound
	   SoundPool soundPool;
	   int soundID;
	   boolean loaded = false;
	
	private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;
    private static final int NOTIFY_ME_ID=1337;
	/////////////////////////////////
    
    /**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beginning);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
//
//		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
		
		Toast.makeText(getApplicationContext(), "ohoh", Toast.LENGTH_LONG).show();
		
		
		////////
		
		  final Handler mHandler = new Handler();
		    // Create runnable for posting
		    final Runnable mUpdateResults = new Runnable() {
		        public void run() {
		            
//		            AnimateandSlideShow();
		            
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
//		    
//
//
//		/////
		    CutQrResult forcutad = new CutQrResult();
//		  
//		 ////
//		  ///
//		    
			NotificationCompat.Builder mBuilder =
			        new NotificationCompat.Builder(this)
			        .setSmallIcon(R.drawable.qme)
			        .setContentTitle("Welcome to Q-Me >3< ")
			        .setContentText("Queuing for a better queue");
			// Creates an explicit intent for an Activity in your app
			Intent resultIntent = new Intent(this, MainActivity.class);

			// The stack builder object will contain an artificial back stack for the
			// started Activity.
			// This ensures that navigating backward from the Activity leads out of
			// your application to the Home screen.
			TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
			// Adds the back stack for the Intent (but not the Intent itself)
			stackBuilder.addParentStack(MainActivity.class);
			// Adds the Intent that starts the Activity to the top of the stack
			stackBuilder.addNextIntent(resultIntent);
			PendingIntent resultPendingIntent =
			        stackBuilder.getPendingIntent(
			            0,
			            PendingIntent.FLAG_UPDATE_CURRENT
			        );
			mBuilder.setContentIntent(resultPendingIntent);
			final NotificationManager mNotificationManager =
			    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			// mId allows you to update the notification later on.
			mNotificationManager.notify(0, mBuilder.build());
			//////
		    
		   
			
			//testbtn = (Button) findViewById(R.id.button1);
		   imgbtn12 = (ImageButton) findViewById(R.id.imageButton123);
		   expandimg = (ImageView) findViewById(R.id.expand2);
		    mTextField = (TextView) findViewById(R.id.editText12);
////		  //  m2TextField = (TextView) findViewById(R.id.editText2);
		    slidingimage = (ImageView)findViewById(R.id.imageView22);
		    descriptionview = (TextView) findViewById(R.id.description12);
		  
		  //  slidingimage.setBackgroundColor(Color.rgb(247, 247, 10));

		  //  m2TextField.setEnabled(false);
		//    mTextField.setEnabled(false);
		 
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
			 soundID = soundPool.load(this, R.raw.sweep1, 1);
			/// notification 
			 final NotificationManager mgr=
				            (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
				        final Notification note=new Notification(R.drawable.qmeview01,
				                                                        "Android Example Status message!",
				                                                        System.currentTimeMillis());
				        // This pending intent will open after notification click
				        PendingIntent i=PendingIntent.getActivity(this, 0,
				                                                new Intent(this, NotifyMessage.class),
				                                                0);
				         
				        note.setLatestEventInfo(this, "Android Example Notification Title",
				                                "This is the android example notification message", i);
				         
				        //After uncomment this line you will see number of notification arrived
				        //note.number=2;
				        mgr.notify(NOTIFY_ME_ID, note);
						
				  
//		   imgbtn12.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
				
//				//Getintent();
//		   //launchScanner(v);
//				launchQRScanner(v);
//				
//				 LayoutInflater layoutInflater 
//			      = (LayoutInflater)getBaseContext()
//			       .getSystemService(LAYOUT_INFLATER_SERVICE);  
//			     View popupView = layoutInflater.inflate(R.layout.popupconfirm, null);  
//			              final PopupWindow popupWindow = new PopupWindow(
//			                popupView, 
//			                LayoutParams.WRAP_CONTENT,  
//			                      LayoutParams.WRAP_CONTENT);
//			              Button btnaccept = (Button)popupView.findViewById(R.id.accept);
//			           Button btncancel = (Button) popupView.findViewById(R.id.buttonCancle);
//			              btnaccept.setOnClickListener(new Button.OnClickListener(){
//
//				     
//			      @Override
//			      public void onClick(View v) {
//			       // TODO Auto-generated method stub
//			    	  
//			    	   // notification start
//				  mgr.notify(NOTIFY_ME_ID, note);
//				  
//			    	  	if(testString!=null){
//				CutQrResult c= new CutQrResult();
//				try {
//					
//					//// notify with advertising with 
//					
//					NotificationCompat.Builder mBuilder =
//			  		        new NotificationCompat.Builder(getApplicationContext())
//			  		        .setSmallIcon(R.drawable.qme)
//			  		        .setContentTitle("Q-Me announcement  \\(^-^)//")
//			  		        .setContentText(c.cutAdvertise(testString));
//			    	  mNotificationManager.notify(0, mBuilder.build());
//			    	  
//			    	////
//			    	  
//					double  timeQr = Double.parseDouble(c.cutWaitingtime(testString));
//					timeQrL = (long) (timeQr*1000);
//					
//				} catch (Exception e) {
//					// TODO: handle exception
//					timeQrL = (long) 0.0;
//				}
//				
//				MyCountDownTimer(timeQrL, interval);
//				descriptionview.setText("  You're requesting for table of "+c.cutTableNumber(testString)+".");
//			Toast.makeText(getApplicationContext(), "Welcome to Q-Me; Queuing application", Toast.LENGTH_LONG).show();
//			    	  	}
//			    	  	
//			       popupWindow.dismiss();
//			      }});
//			               
//			              btncancel.setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View v) {
//								// TODO Auto-generated method stub
//								popupWindow.dismiss();
//							}
//						});
//			              
//			    //    popupWindow.showAsDropDown(but, 10, -30);
//				popupWindow.showAtLocation(v,  Gravity.CENTER, 0, 0);
//				
//				
//			}
//		});

					
			
//							Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); // Vibrate for 500 milliseconds
//							 vib.vibrate(500);
		///////			
		//   testbtn.setOnClickListener(new OnClickListener() {
		//	
	//		@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.fuji.co.th/2009/TH/home/index.php"));
//				startActivity(browserIntent);
				
			}
		//});
		//   
	//	}
	

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.beginning, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_beginning,
					container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((Beginning) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}
	 public void Getintent(){
		    
	     IntentIntegrator scanIntegrator = new IntentIntegrator(this);
	       scanIntegrator.initiateScan();
	    }
	    
	    ////
	    public void launchScanner(View v) {
	        if (isCameraAvailable()) {
	            Intent intent = new Intent(this, ZBarScannerActivity.class);
	            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
	        } else {
	            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
	        }
	    }

	    public void launchQRScanner(View v) {
	        if (isCameraAvailable()) {
	            Intent intent = new Intent(this, ZBarScannerActivity.class);
	            intent.putExtra(ZBarConstants.SCAN_MODES, new int[]{Symbol.QRCODE});
	            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
	        } else {
	            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
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
	                  
	                } else if(resultCode == RESULT_CANCELED && data != null) {
	                    String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
	                    if(!TextUtils.isEmpty(error)) {
	                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
	                    }
	                }
	                break;
	        }
	    }
	   //นับถอยหลัง 
		public  void MyCountDownTimer(long startTime, final long interval) {
			
			 new CountDownTimer(startTime, interval) {
					
					@Override
					public void onTick(long millisUntilFinished) {
						// TODO Auto-generated method stub
						if(millisUntilFinished / 1000 >= 5&& millisUntilFinished / 1000<10){
							
							Toast.makeText(getApplicationContext(), "ใกล้ถึงคิวของคุณแล้วนะครับ", Toast.LENGTH_LONG).show();
							mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
						}
						else if(millisUntilFinished / 1000 >= 30&& millisUntilFinished / 1000<40){
							CutQrResult adcut = new CutQrResult();
							Toast.makeText(getApplicationContext(), adcut.cutAdvertise(testString), Toast.LENGTH_LONG).show();
							mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
						}
						
						else{
						mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
					}}
					
					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						
						// sound + vibration
						 AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
					      float actualVolume = (float) audioManager
					          .getStreamVolume(AudioManager.STREAM_MUSIC);
					      float maxVolume = (float) audioManager
					          .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
					      float volume = actualVolume / maxVolume;
					      // Is the sound loaded already?
					      if (loaded) {
					        soundPool.play(soundID, volume, volume, 1, 0, 1f);
					        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); // Vibrate for 500 milliseconds
							 vib.vibrate(500);
							 
							 ////
							////// // popup
							   LayoutInflater layoutInflater 
							      = (LayoutInflater)getBaseContext()
							       .getSystemService(LAYOUT_INFLATER_SERVICE);  
							     View popupView = layoutInflater.inflate(R.layout.popup, null);  
							              final PopupWindow popupWindow = new PopupWindow(
							                popupView, 
							                LayoutParams.WRAP_CONTENT,  
							                      LayoutParams.WRAP_CONTENT);
							              Button btnDismiss = (Button)popupView.findViewById(R.id.Start);
							              btnDismiss.setOnClickListener(new Button.OnClickListener(){

							      @Override
							      public void onClick(View v) {
							       // TODO Auto-generated method stub
							       popupWindow.dismiss();
							      }});
							                View v = new View(getApplicationContext());
						  popupWindow.showAtLocation(v,  Gravity.CENTER, 0, 0);
							 
				///////////////////////
							 
					    } 
					}
	    }.start();
			
	   
	    
			 }
		
		//// slide show picture
		
//		 private void AnimateandSlideShow() {
//		        
//		        
//		       
//		           slidingimage.setImageResource(IMAGE_IDS[currentimageindex%IMAGE_IDS.length]);
//		           
//		           currentimageindex++;
//		        
//		           Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.abc_fade_in);
//		          
//		        
//		          slidingimage.startAnimation(rotateimage);
//		          
		         // zooming image
		          // mAttacher = new PhotoViewAttacher(slidingimage);
//		          slidingimage.setOnClickListener(new OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						// TODO Auto-generated method stub
//						expandimg.setBackgroundColor(Color.rgb(100, 100, 50));
//						currentimageindex--;
//						expandimg.setImageResource(IMAGE_IDS[currentimageindex%IMAGE_IDS.length]);
//						expandimg.setVisibility(expandimg.VISIBLE);
//						currentimageindex++;
	//
//						mTextField.setVisibility(mTextField.INVISIBLE);
				
//						slidingimage.setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View v) {
//								// TODO Auto-generated method stub
//								
//								expandimg.setVisibility(expandimg.INVISIBLE);
//								imgbtn1.setVisibility(imgbtn1.VISIBLE);
//									mTextField.setVisibility(mTextField.VISIBLE);
//							
//									//testbtn.setVisibility(testbtn.VISIBLE);
//					//			m2TextField.setVisibility(m2TextField.VISIBLE);
//							}
//						});
//					}
//				});
		        
//		    }  
}
