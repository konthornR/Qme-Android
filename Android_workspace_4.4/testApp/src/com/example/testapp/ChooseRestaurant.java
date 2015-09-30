package com.example.testapp;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.Qme.testapp.R;

public class ChooseRestaurant extends Activity {
	ImageView fuji,mk;
	ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_restaurant);
		// อ้างอิงไปยัง DrawerLayout ที่เป็น root element ของ layout file
				mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_choose);
				// อ้างอิงไปยัง ListView ซึ่งเป็นตัว drawer ที่จะเลื่อนออกมา
				mDrawerList = (ListView) findViewById(R.id.left_drawer_choose);

				// กำหนด Adapter ให้กับ ListView
				mDrawerList.setAdapter(new ArrayAdapter<String>(this,
						R.layout.list_item, maintain_data.listdrawer()));
				// กำหนด click listener ให้กับ ListView
				// เพื่อระบุการทำงานเมื่อไอเท็มในลิสต์ถูกคลิกเลือก
				mDrawerList.setOnItemClickListener(new DrawerItemClickListener03());

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
		fuji = (ImageView) findViewById(R.id.fujiButt);
		fuji.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				String temp = null;
				Intent myIntent = new Intent("Menus_activity01");
				myIntent.putExtra("extra","fuji");
				startActivityForResult(myIntent, 0);
			}
		});
		mk = (ImageView) findViewById(R.id.MkButt);
		mk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				String temp = null;
				Intent myIntent = new Intent("Second_menu");
				myIntent.putExtra("extra", "mk");
				startActivityForResult(myIntent, 0);
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_restaurant, menu);
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
	public class DrawerItemClickListener03 implements
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
}
