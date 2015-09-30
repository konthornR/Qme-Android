package com.example.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.Qme.testapp.R;

public class wakeupAct extends Activity {

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wakeup);
  LinearLayout l1 = (LinearLayout) findViewById(R.id.layoutwakeup);
       l1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG).show();
			finish();
		}
	});
    
       
       LinearLayout l2 = (LinearLayout) findViewById(R.id.layoutwakeup2);
       l2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//Toast.makeText(getApplicationContext(), "test2", Toast.LENGTH_LONG).show();
			finish();
		}
	});

      
    }
    }